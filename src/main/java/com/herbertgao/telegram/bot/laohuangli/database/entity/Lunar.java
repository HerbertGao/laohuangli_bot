package com.herbertgao.telegram.bot.laohuangli.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@TableName(value = "laohuangli.lunar")
public class Lunar implements Serializable {
    /**
     * 公历时间
     */
    @TableId(value = "GregorianDateTime", type = IdType.AUTO)
    private LocalDate gregoriandatetime;

    /**
     * 农历时间
     */
    @TableField(value = "LunarDateTime")
    private String lunardatetime;

    /**
     * 显示农历的日名称
     */
    @TableField(value = "LunarShow")
    private String lunarshow;

    /**
     * 1是节日
     */
    @TableField(value = "IsJieJia")
    private Integer isjiejia;

    /**
     * 农历节日
     */
    @TableField(value = "LJie")
    private String ljie;

    /**
     * 公历节日
     */
    @TableField(value = "GJie")
    private String gjie;

    /**
     * 宜：
     */
    @TableField(value = "Yi")
    private String yi;

    /**
     * 忌：
     */
    @TableField(value = "Ji")
    private String ji;

    /**
     * 诸神位置
     */
    @TableField(value = "ShenWei")
    private String shenwei;

    /**
     * 胎神位置
     */
    @TableField(value = "Taishen")
    private String taishen;

    /**
     * 冲煞
     */
    @TableField(value = "Chong")
    private String chong;

    /**
     * 岁煞
     */
    @TableField(value = "SuiSha")
    private String suisha;

    /**
     * 甲子五行
     */
    @TableField(value = "WuxingJiazi")
    private String wuxingjiazi;

    /**
     * 纳音五行年
     */
    @TableField(value = "WuxingNaYear")
    private String wuxingnayear;

    /**
     * 纳音五行月
     */
    @TableField(value = "WuxingNaMonth")
    private String wuxingnamonth;

    /**
     * 纳音五行日
     */
    @TableField(value = "WuxingNaDay")
    private String wuxingnaday;

    /**
     * 月相
     */
    @TableField(value = "MoonName")
    private String moonname;

    /**
     * 二十八星宿
     */
    @TableField(value = "XingEast")
    private String xingeast;

    /**
     * 星座
     */
    @TableField(value = "XingWest")
    private String xingwest;

    /**
     * 彭祖百忌
     */
    @TableField(value = "PengZu")
    private String pengzu;

    /**
     * 十二神-执位
     */
    @TableField(value = "JianShen")
    private String jianshen;

    /**
     * 天干地支年
     */
    @TableField(value = "TianGanDiZhiYear")
    private String tiangandizhiyear;

    /**
     * 天干地支月
     */
    @TableField(value = "TianGanDiZhiMonth")
    private String tiangandizhimonth;

    /**
     * 天干地支日
     */
    @TableField(value = "TianGanDiZhiDay")
    private String tiangandizhiday;

    /**
     * 农历月代名词
     */
    @TableField(value = "LMonthName")
    private String lmonthname;

    /**
     * 生肖
     */
    @TableField(value = "LYear")
    private String lyear;

    /**
     * 农历月：腊月，正月二月
     */
    @TableField(value = "LMonth")
    private String lmonth;

    /**
     * 农历日：初一
     */
    @TableField(value = "LDay")
    private String lday;

    /**
     * 节气
     */
    @TableField(value = "SolarTermName")
    private String solartermname;

    private static final long serialVersionUID = 1L;

    public static final String COL_GREGORIANDATETIME = "GregorianDateTime";

    public static final String COL_LUNARDATETIME = "LunarDateTime";

    public static final String COL_LUNARSHOW = "LunarShow";

    public static final String COL_ISJIEJIA = "IsJieJia";

    public static final String COL_LJIE = "LJie";

    public static final String COL_GJIE = "GJie";

    public static final String COL_YI = "Yi";

    public static final String COL_JI = "Ji";

    public static final String COL_SHENWEI = "ShenWei";

    public static final String COL_TAISHEN = "Taishen";

    public static final String COL_CHONG = "Chong";

    public static final String COL_SUISHA = "SuiSha";

    public static final String COL_WUXINGJIAZI = "WuxingJiazi";

    public static final String COL_WUXINGNAYEAR = "WuxingNaYear";

    public static final String COL_WUXINGNAMONTH = "WuxingNaMonth";

    public static final String COL_WUXINGNADAY = "WuxingNaDay";

    public static final String COL_MOONNAME = "MoonName";

    public static final String COL_XINGEAST = "XingEast";

    public static final String COL_XINGWEST = "XingWest";

    public static final String COL_PENGZU = "PengZu";

    public static final String COL_JIANSHEN = "JianShen";

    public static final String COL_TIANGANDIZHIYEAR = "TianGanDiZhiYear";

    public static final String COL_TIANGANDIZHIMONTH = "TianGanDiZhiMonth";

    public static final String COL_TIANGANDIZHIDAY = "TianGanDiZhiDay";

    public static final String COL_LMONTHNAME = "LMonthName";

    public static final String COL_LYEAR = "LYear";

    public static final String COL_LMONTH = "LMonth";

    public static final String COL_LDAY = "LDay";

    public static final String COL_SOLARTERMNAME = "SolarTermName";
}