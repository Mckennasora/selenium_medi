package com.yyh.selenium_medi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.selenium_medi.dao.InputEntityDao;
import com.yyh.selenium_medi.entity.InputEntity;
import com.yyh.selenium_medi.service.InputEntityService;
import org.springframework.stereotype.Service;

/**
 * (InputEntity)表服务实现类
 *
 * @author makejava
 * @since 2023-11-15 17:34:41
 */
@Service("inputEntityService")
public class InputEntityServiceImpl extends ServiceImpl<InputEntityDao, InputEntity> implements InputEntityService {

}

