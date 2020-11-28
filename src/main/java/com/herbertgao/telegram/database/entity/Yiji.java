package com.herbertgao.telegram.database.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Yiji implements Serializable {
    /**
    * ID
    */
    private Integer id;

    /**
    * 分类
    */
    private String catalog;

    /**
    * 宜
    */
    private String yi;

    /**
    * 忌
    */
    private String ji;

    private static final long serialVersionUID = 1L;
}