package com.farm.admin.farm.controller;

import com.farm.admin.export.RecordExportService;
import com.farm.admin.farm.PlotCropParams;
import com.farm.admin.farm.service.PlotCropService;
import com.farm.admin.record.RecordParams;
import com.farm.base.BaseException;
import com.farm.base.common.JsonResult;
import com.farm.base.common.JsonResultUtils;
import com.farm.base.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 ** @Version 1.0.0
 */
@RequestMapping("api/crop")
@RestController
public class PlotCropController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlotCropController.class);
    @Autowired
    private PlotCropService plotCropService;
    @Autowired
    private RecordExportService exportService;

    @GetMapping("page")
    public JsonResult pagingPlotCrop(PlotCropParams params) throws BaseException {
        LOGGER.info("求情开始报告 : 品种作物列表");
        Page page = this.plotCropService.pagingPlotCrop(params);
        return JsonResultUtils.getResultByPage(page);

    }


    @GetMapping("export")
    public void export(PlotCropParams params, HttpServletResponse response) throws IOException {
        LOGGER.info("请求开始报告 : 导出品种作物信息");
        this.exportService.exportPlotCrop(params,response);
    }


}
