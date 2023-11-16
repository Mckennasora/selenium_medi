package com.yyh.selenium_medi.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.yyh.selenium_medi.entity.InputEntity;
import com.yyh.selenium_medi.service.InputEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class InputListener implements ReadListener<InputEntity> {

    private InputEntityService inputEntityService;

    // 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 100;
    // 缓存的数据
    private List<InputEntity> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    public InputListener(InputEntityService inputEntityService) {
        this.inputEntityService = inputEntityService;
    }

    // 这个每一条数据解析都会来调用
    @Override
    public void invoke(InputEntity data, AnalysisContext context) {

        String model = data.getModel();
        if (model != null) {
            String s = model.replaceAll("）|（|，|、|\n|[\u4e00-\u9fa5]", "");
            String s1 = s.replaceFirst("^/", "");
            String s2 = s1.replaceFirst("^\\s+", "");
            data.setModel(s2);
        }

        cachedDataList.add(data);

        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    // 所有数据解析完成了 都会来调用
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    // 加上存储数据库
    private void saveData() {
        inputEntityService.saveBatch(cachedDataList);
    }
}
