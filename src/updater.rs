use anyhow::{anyhow, Result};
use reqwest::Client;
use serde_json::Value;
use std::env;
use std::fs;
use std::process::Command;

const GITHUB_API_BASE: &str = "https://api.github.com";
const REPO_OWNER: &str = "HerbertGao";
const REPO_NAME: &str = "laohuangli_bot";

pub struct Updater {
    client: Client,
}

impl Updater {
    pub fn new() -> Result<Self> {
        let client = Client::builder()
            .user_agent("laohuangli_bot_updater/1.0")
            .build()?;
        Ok(Self { client })
    }

    /// 获取当前版本
    pub fn get_current_version() -> Result<String> {
        let output = Command::new(env::current_exe()?)
            .arg("--version")
            .output()?;
        
        let version_str = String::from_utf8_lossy(&output.stdout);
        let version = version_str
            .trim()
            .split_whitespace()
            .last()
            .ok_or_else(|| anyhow!("无法解析当前版本"))?;
        
        Ok(version.to_string())
    }

    /// 获取GitHub上的最新版本
    pub async fn get_latest_version(&self) -> Result<String> {
        let url = format!("{}/repos/{}/{}/releases/latest", GITHUB_API_BASE, REPO_OWNER, REPO_NAME);
        
        let response = self.client.get(&url).send().await?;
        
        if !response.status().is_success() {
            return Err(anyhow!("获取最新版本失败: {}", response.status()));
        }
        
        let release_data: Value = response.json().await?;
        let tag_name = release_data["tag_name"]
            .as_str()
            .ok_or_else(|| anyhow!("无法获取tag_name"))?;
        
        Ok(tag_name.to_string())
    }

    /// 比较版本号
    pub fn is_newer_version(current: &str, latest: &str) -> bool {
        // 移除版本号前缀，支持 v1.0.0.1 和 1.0.0.1 格式
        let current_clean = current.trim_start_matches('v');
        let latest_clean = latest.trim_start_matches('v');
        
        let current_parts: Vec<u32> = current_clean
            .split('.')
            .filter_map(|s| s.parse().ok())
            .collect();
        
        let latest_parts: Vec<u32> = latest_clean
            .split('.')
            .filter_map(|s| s.parse().ok())
            .collect();
        
        // 补齐版本号部分
        let max_len = current_parts.len().max(latest_parts.len());
        let mut current_parts = current_parts;
        let mut latest_parts = latest_parts;
        
        while current_parts.len() < max_len {
            current_parts.push(0);
        }
        while latest_parts.len() < max_len {
            latest_parts.push(0);
        }
        
        // 比较版本号
        for (current, latest) in current_parts.iter().zip(latest_parts.iter()) {
            if latest > current {
                return true;
            } else if latest < current {
                return false;
            }
        }
        
        false
    }

    /// 获取适合当前平台的下载URL
    pub async fn get_download_url(&self, version: &str) -> Result<String> {
        let url = format!("{}/repos/{}/{}/releases/tags/{}", GITHUB_API_BASE, REPO_OWNER, REPO_NAME, version);
        
        let response = self.client.get(&url).send().await?;
        
        if !response.status().is_success() {
            return Err(anyhow!("获取发布信息失败: {}", response.status()));
        }
        
        let release_data: Value = response.json().await?;
        let assets = release_data["assets"]
            .as_array()
            .ok_or_else(|| anyhow!("无法获取assets"))?;
        
        // 检测当前平台
        let target_asset = self.get_target_asset_name()?;
        
        for asset in assets {
            let name = asset["name"]
                .as_str()
                .ok_or_else(|| anyhow!("无法获取asset名称"))?;
            
            if name.contains(&target_asset) {
                return Ok(asset["browser_download_url"]
                    .as_str()
                    .ok_or_else(|| anyhow!("无法获取下载URL"))?
                    .to_string());
            }
        }
        
        Err(anyhow!("未找到适合当前平台的下载文件"))
    }

    /// 获取目标平台对应的文件名
    fn get_target_asset_name(&self) -> Result<String> {
        let target = env::consts::OS;
        let arch = env::consts::ARCH;
        
        match (target, arch) {
            ("macos", "aarch64") => Ok("macos_arm64".to_string()),
            ("linux", "x86_64") => Ok("linux_x86_64".to_string()),
            ("linux", "aarch64") => Ok("linux_arm64".to_string()),
            _ => Err(anyhow!("不支持的平台: {} {}", target, arch)),
        }
    }

    /// 下载并更新程序
    pub async fn download_and_update(&self, download_url: &str) -> Result<()> {
        println!("正在下载最新版本...");
        
        let response = self.client.get(download_url).send().await?;
        
        if !response.status().is_success() {
            return Err(anyhow!("下载失败: {}", response.status()));
        }
        
        let bytes = response.bytes().await?;
        
        // 获取当前可执行文件路径
        let current_exe = env::current_exe()?;
        let temp_path = current_exe.with_extension("tmp");
        
        // 写入临时文件
        fs::write(&temp_path, &bytes)?;
        
        // 设置执行权限
        #[cfg(unix)]
        {
            use std::os::unix::fs::PermissionsExt;
            let mut perms = fs::metadata(&temp_path)?.permissions();
            perms.set_mode(0o755);
            fs::set_permissions(&temp_path, perms)?;
        }
        
        // 备份原文件
        let backup_path = current_exe.with_extension("bak");
        if backup_path.exists() {
            fs::remove_file(&backup_path)?;
        }
        fs::rename(&current_exe, &backup_path)?;
        
        // 替换为新文件
        fs::rename(&temp_path, &current_exe)?;
        
        println!("更新完成！");
        println!("原文件已备份为: {}", backup_path.display());
        println!("新文件路径: {}", current_exe.display());
        
        Ok(())
    }

    /// 执行完整的更新流程
    pub async fn update(&self) -> Result<()> {
        println!("检查更新...");
        
        let current_version = Self::get_current_version()?;
        println!("当前版本: {}", current_version);
        
        let latest_version = self.get_latest_version().await?;
        // 显示时去掉v前缀，让显示更协调
        let display_version = latest_version.trim_start_matches('v');
        println!("最新版本: {}", display_version);
        
        if !Self::is_newer_version(&current_version, &latest_version) {
            println!("当前已是最新版本！");
            return Ok(());
        }
        
        println!("发现新版本: {}", display_version);
        println!("是否要更新？(y/N): ");
        
        // 简单的用户确认
        let mut input = String::new();
        std::io::stdin().read_line(&mut input)?;
        
        if input.trim().to_lowercase() != "y" {
            println!("取消更新");
            return Ok(());
        }
        
        let download_url = self.get_download_url(&latest_version).await?;
        self.download_and_update(&download_url).await?;
        
        Ok(())
    }
}
