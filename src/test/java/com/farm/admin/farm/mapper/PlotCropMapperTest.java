package com.farm.admin.farm.mapper;

import com.farm.admin.farm.PlotCropParams;
import com.farm.base.common.Page;
import com.farm.base.farm.PlotCrop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 ** @Version 1.0.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PlotCropMapperTest {


    @Autowired
    private PlotCropMapper plotCropMapper;

    @Test
    public void test(){

        PlotCropParams params=new PlotCropParams();
        params.setPageNo(1);
        params.setPageSize(10);
        params.setFarmId(1L);
        params.setPlotId(1L);
        params.setStatus(0);


        Page<PlotCropParams> page = new Page<>(params.getPageNo(), params.getPageSize(), params);

        int i = this.plotCropMapper.countPlotCrop(page);

        List<PlotCrop> list = this.plotCropMapper.pagingPlotCrop(page);

        System.out.println(list.toString());

    }

}