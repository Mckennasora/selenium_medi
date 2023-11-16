package com.yyh.selenium_medi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyh.selenium_medi.entity.InputEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * (InputEntity)表数据库访问层
 *
 * @author makejava
 * @since 2023-11-15 17:34:40
 */
@Repository
public interface InputEntityDao extends BaseMapper<InputEntity> {

}

