package com.herbertgao.telegram.bot.laohuangli.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@TableName(value = "laohuangli.log")
public class Log implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 日期
     */
    @TableField(value = "`date`")
    private LocalDate date;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_DATE = "date";

    public static final String COL_CONTENT = "content";
}