package com.farm.admin.record.controller;

import com.farm.admin.record.CropTaskParams;
import com.farm.admin.record.CropTaskVO;
import com.farm.admin.record.dto.CropTaskDTO;
import com.farm.admin.record.service.CropTaskService;
import com.farm.base.BaseException;
import com.farm.base.common.JsonResult;
import com.farm.base.common.JsonResultUtils;
import com.farm.base.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 ** @Version 1.0.0
 */
@RequestMapping("api/task")
@RestController
public class CropTaskController {
    private static final Logger LOGGER= LoggerFactory.getLogger(CropTaskController.class);

    @Autowired
    private CropTaskService cropTaskService;

    @GetMapping("page")
    public JsonResult pagingCropTask(CropTaskParams params) throws BaseException {
        LOGGER.info("请求开始报告 : 作物任务列表");
        Page page = this.cropTaskService.pagingCropTask(params);
        return JsonResultUtils.getResultByPage(page);
    }

    @PostMapping("add")
    public JsonResult save(@RequestBody CropTaskVO params) throws BaseException {
        LOGGER.info("请求开始报告 : 添加作物任务");
        this.cropTaskService.saveCropTask(params);
        return JsonResult.ok();
    }


    @PutMapping("update")
    public JsonResult update(@RequestBody CropTaskVO params) throws BaseException {
        LOGGER.info("请求开始报告 : 更新作物任务");
        this.cropTaskService.updateCropTask(params);
        return JsonResult.ok();
    }

    @DeleteMapping("{taskId}")
    public JsonResult delete(@PathVariable("taskId") Long taskId) throws BaseException {
        LOGGER.info("请求开始报告 : 删除作物任务");
        this.cropTaskService.deleteCropTask(taskId);
        return JsonResult.ok();
    }

    @GetMapping("{taskId}")
    public JsonResult getDetailCropTask(@PathVariable("taskId") Long taskId) throws BaseException {
        LOGGER.info("请求开始报告 : 作物任务详情");
        CropTaskDTO detail = this.cropTaskService.getDetail(taskId);
        return JsonResult.ok(detail);

    }

}
