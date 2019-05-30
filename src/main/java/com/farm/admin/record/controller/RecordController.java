package com.farm.admin.record.controller;

import com.farm.admin.export.RecordExportService;
import com.farm.admin.record.AssessRecordVO;
import com.farm.admin.record.RecordParams;
import com.farm.admin.record.dto.RecordDTO;
import com.farm.admin.record.service.RecordService;
import com.farm.base.BaseException;
import com.farm.base.common.JsonResult;
import com.farm.base.common.JsonResultUtils;
import com.farm.base.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 ** @Version 1.0.0
 */
@RestController
@RequestMapping("api/record")
public class RecordController {
    private static final Logger LOGGER= LoggerFactory.getLogger(RecordController.class);
    @Autowired
    private RecordService recordService;
    @Autowired
    private RecordExportService exportService;

    @GetMapping("page")
    public JsonResult pagingRecord(RecordParams params) throws BaseException {
        LOGGER.info("请求开始报告 : 农事记录列表");
        Page page = this.recordService.pagingRecord(params);
        return JsonResultUtils.getResultByPage(page);
    }

    @GetMapping("next")
    public JsonResult getNoEvaluationRecord(RecordParams params) {
        LOGGER.info("请求开始报告 : 下一个未评估记录");
        RecordDTO result = this.recordService.getNoEvaluationRecord(params);
        return JsonResult.ok(result);
    }

    @PostMapping("assess")
    public JsonResult assessRecord(@RequestBody AssessRecordVO params) throws BaseException {
        LOGGER.info("请求开始报告 : 评估记录");
        this.recordService.assessRecord(params);
        return JsonResult.ok();
    }


    @PutMapping("updateAssess")
    public JsonResult updateAssess(@RequestBody AssessRecordVO params) throws BaseException {
        LOGGER.info("请求开始报告 : 更新评估记录");
        this.recordService.updateAssess(params);
        return JsonResult.ok();
    }


    @GetMapping("exportRecord")
    public void exportRecord(RecordParams params, HttpServletResponse response) throws IOException {
        LOGGER.info("请求开始报告 : 导出农事记录");
        this.exportService.exportRecord(params,response);
    }

}
