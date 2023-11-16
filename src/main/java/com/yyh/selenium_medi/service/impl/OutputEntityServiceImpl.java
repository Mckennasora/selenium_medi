package com.yyh.selenium_medi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.selenium_medi.dao.OutputEntityDao;
import com.yyh.selenium_medi.entity.OutputEntity;
import com.yyh.selenium_medi.service.OutputEntityService;
import org.springframework.stereotype.Service;

/**
 * (OutputEntity)表服务实现类
 *
 * @author makejava
 * @since 2023-11-15 17:35:16
 */
@Service("outputEntityService")
public class OutputEntityServiceImpl extends ServiceImpl<OutputEntityDao, OutputEntity> implements OutputEntityService {

}

