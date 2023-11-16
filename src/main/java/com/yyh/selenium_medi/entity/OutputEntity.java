package com.yyh.selenium_medi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (OutputEntity)表实体类
 *
 * @author makejava
 * @since 2023-11-15 17:35:16
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputEntity extends Model<OutputEntity> {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer inputId;

    private String deviceName;

    private String model;

    private String manufacturer;

    private String suppliers;

    private Integer price;

    private Integer quantity;

    private String sourceWebsite;

    private String sourceUrl;

    private String buyer;

    private String date;
}

