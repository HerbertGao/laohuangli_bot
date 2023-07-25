package com.herbertgao.telegram.bot.laohuangli.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

@Data
@TableName(value = "laohuangli.yiji")
public class Yiji implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类
     */
    @TableField(value = "`catalog`")
    private String catalog;

    /**
     * 宜
     */
    @TableField(value = "yi")
    private String yi;

    /**
     * 忌
     */
    @TableField(value = "ji")
    private String ji;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CATALOG = "catalog";

    public static final String COL_YI = "yi";

    public static final String COL_JI = "ji";
}