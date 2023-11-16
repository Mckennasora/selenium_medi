package com.yyh.selenium_medi.testMySQL;

import com.yyh.selenium_medi.entity.InputEntity;
import com.yyh.selenium_medi.service.InputEntityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TestStore {

    @Autowired
    InputEntityService inputEntityService;

    @Test
    void insert(){
        InputEntity inputEntity = new InputEntity(311, "功能牵引网架及配件", "JK_12138", 12233);
        inputEntityService.save(inputEntity);
    }
}
