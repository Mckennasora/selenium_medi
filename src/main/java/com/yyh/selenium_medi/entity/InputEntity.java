package com.yyh.selenium_medi.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.yyh.selenium_medi.utils.NumStrConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (InputEntity)表实体类
 *
 * @author makejava
 * @since 2023-11-15 17:34:40
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputEntity extends Model<InputEntity> {

    @ExcelProperty(value = "id", converter = NumStrConverter.class)
    private Integer id;

    @ExcelProperty(value = "deviceName")
    private String deviceName;

    @ExcelProperty(value = "model")
    private String model;

    @ExcelProperty(value = "price", converter = NumStrConverter.class)
    private Integer price;

}

