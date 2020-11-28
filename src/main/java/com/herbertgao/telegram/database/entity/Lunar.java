package com.herbertgao.telegram.database.entity;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Lunar implements Serializable {
    /**
    * 公历时间
    */
    private LocalDate gregoriandatetime;

    /**
    * 农历时间
    */
    private String lunardatetime;

    /**
    * 显示农历的日名称
    */
    private String lunarshow;

    /**
    * 1是节日
    */
    private Short isjiejia;

    /**
    * 农历节日
    */
    private String ljie;

    /**
    * 公历节日
    */
    private String gjie;

    /**
    * 宜：
    */
    private String yi;

    /**
    * 忌：
    */
    private String ji;

    /**
    * 诸神位置
    */
    private String shenwei;

    /**
    * 胎神位置
    */
    private String taishen;

    /**
    * 冲煞
    */
    private String chong;

    /**
    * 岁煞
    */
    private String suisha;

    /**
    * 甲子五行
    */
    private String wuxingjiazi;

    /**
    * 纳音五行年
    */
    private String wuxingnayear;

    /**
    * 纳音五行月
    */
    private String wuxingnamonth;

    /**
    * 纳音五行日
    */
    private String wuxingnaday;

    /**
    * 月相
    */
    private String moonname;

    /**
    * 二十八星宿
    */
    private String xingeast;

    /**
    * 星座
    */
    private String xingwest;

    /**
    * 彭祖百忌
    */
    private String pengzu;

    /**
    * 十二神-执位
    */
    private String jianshen;

    /**
    * 天干地支年
    */
    private String tiangandizhiyear;

    /**
    * 天干地支月
    */
    private String tiangandizhimonth;

    /**
    * 天干地支日
    */
    private String tiangandizhiday;

    /**
    * 农历月代名词
    */
    private String lmonthname;

    /**
    * 生肖
    */
    private String lyear;

    /**
    * 农历月：腊月，正月二月
    */
    private String lmonth;

    /**
    * 农历日：初一
    */
    private String lday;

    /**
    * 节气
    */
    private String solartermname;

    private static final long serialVersionUID = 1L;
}