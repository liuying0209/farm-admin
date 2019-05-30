package com.farm.admin.record.service;

import com.farm.admin.record.RecordParams;
import com.farm.base.BaseException;
import com.farm.base.common.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 ** @Version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {


    @Autowired
    private RecordService recordService;

    @Test
    public void test() throws BaseException {

        RecordParams recordParams = new RecordParams();
        recordParams.setPageNo(1);
        recordParams.setPageSize(10);
        recordParams.setFarmId(4L);
        recordParams.setPlotId(5L);
        recordParams.setCropId(1L);
        recordParams.setPlotCropId(4L);

        Page page = this.recordService.pagingRecord(recordParams);
        System.out.println(page.toString());


    }
}