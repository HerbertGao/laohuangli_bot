package com.herbertgao.telegram.database.entity;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Log implements Serializable {
    /**
    * ID
    */
    private Integer id;

    /**
    * 日期
    */
    private LocalDate date;

    /**
    * 内容
    */
    private String content;

    private static final long serialVersionUID = 1L;
}