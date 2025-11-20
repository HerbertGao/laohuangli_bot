use rand::{Rng, SeedableRng};
use rand::rngs::StdRng;

/// 创建基于种子的安全随机数生成器
/// 
/// 与Java版本保持一致：将MD5哈希值（32字节）的前8字节转换为u64种子
/// 然后创建StdRng随机数生成器
/// 
/// # 参数
/// * `seed` - 种子字节数组（通常是MD5哈希值）
/// 
/// # 返回
/// 基于种子初始化的StdRng随机数生成器
pub fn create_secure_random(seed: &[u8]) -> StdRng {
    // 将字节数组转换为u64种子（与Java版本保持一致，只使用前8字节）
    let mut seed_value: u64 = 0;
    for i in 0..seed.len().min(8) {
        seed_value = (seed_value << 8) | (seed[i] as u64);
    }
    StdRng::seed_from_u64(seed_value)
}

/// 从种子字节数组生成随机整数
/// 
/// 这是更现代化的封装，直接返回随机整数，无需手动操作字节
/// 
/// # 参数
/// * `seed` - 种子字节数组（通常是MD5哈希值）
/// 
/// # 返回
/// 基于种子生成的随机整数
pub fn next_int_from_seed(seed: &[u8]) -> i32 {
    let mut rng = create_secure_random(seed);
    // 在 Rust 2024 edition 中，gen 是保留关键字，使用 random_range 替代 gen_range
    // 生成整个 i32 范围的随机数
    rng.random_range(i32::MIN..=i32::MAX)
}
