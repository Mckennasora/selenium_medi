package com.yyh.selenium_medi.testExcel;


import com.alibaba.excel.EasyExcel;
import com.yyh.selenium_medi.entity.InputEntity;
import com.yyh.selenium_medi.service.InputEntityService;
import com.yyh.selenium_medi.utils.InputListener;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TestExcel {
    @Autowired
    private InputEntityService inputEntityService;

    @Test
    void inputData(){
        String fileName = "D:\\yyhfile\\Xproject\\YCLproject\\yunzhian\\selenium_medi\\花都设备.xlsx";
// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, InputEntity.class, new InputListener(inputEntityService)).sheet().doRead();
    }
}
