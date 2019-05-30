package com.farm.admin.farm.controller;

import com.farm.admin.farm.FarmParams;
import com.farm.admin.farm.FarmVO;
import com.farm.admin.farm.MultilevelLinkageMenuVO;
import com.farm.admin.farm.service.FarmService;
import com.farm.base.BaseException;
import com.farm.base.common.JsonResult;
import com.farm.base.common.JsonResultUtils;
import com.farm.base.common.Page;
import com.farm.base.farm.Crop;
import com.farm.base.farm.Farm;
import com.farm.base.farm.FarmWork;
import com.farm.base.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 ** @Date: 2019-04-26 23:59
 */
@RestController
@RequestMapping("api/farm")
public class FarmController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FarmController.class);

    @Autowired
    private FarmService farmService;

    @GetMapping("page")
    public JsonResult pagingFarm(FarmParams params) throws BaseException {
        LOGGER.info("请求开始报告: 农场列表");
        Page page = this.farmService.pagingFarm(params);
        return JsonResultUtils.getResultByPage(page);
    }

    @PostMapping("add")
    public JsonResult save(@RequestBody FarmVO params) throws BaseException {
        LOGGER.info("请求开始报告: 添加农场");
        this.farmService.saveFarm(params);
        return JsonResult.ok();
    }


    @PutMapping("update")
    public JsonResult update(@RequestBody FarmVO params) throws BaseException {
        LOGGER.info("请求开始报告: 更新农场");
        this.farmService.updateFarm(params);
        return JsonResult.ok();
    }

    @DeleteMapping("{farmId}")
    public JsonResult delete(@PathVariable("farmId") Long farmId) throws BaseException {
        LOGGER.info("请求开始报告: 删除农场");
        this.farmService.delete(farmId);
        return JsonResult.ok();
    }

    @GetMapping("{farmId}")
    public JsonResult getDetailFarm(@PathVariable("farmId") Long farmId) throws BaseException {
        LOGGER.info("请求开始报告: 农场详情");
        Farm farm = this.farmService.getDetail(farmId);
        return JsonResult.ok(farm);

    }

    @GetMapping("crop/list")
    public JsonResult listCrop()  {
        LOGGER.info("请求开始报告: 所有农作物集合");
        List<Crop> cropList = this.farmService.listCrop();
        return JsonResult.ok(cropList);
    }


    @GetMapping("work/list")
    public JsonResult listFarmWork()  {
        LOGGER.info("请求开始报告: 所有农事环节集合");
        List<Map<String,Object>> farmWorks = this.farmService.listFarmWork();
        return JsonResult.ok(farmWorks);
    }


    @GetMapping("linkage/menu")
    public JsonResult linkageMenu(MultilevelLinkageMenuVO params)  {
        LOGGER.info("请求开始报告: 联级菜单接口");
        List<Map<String, Object>> maps = this.farmService.multilevelLinkageMenu(params);
        return JsonResult.ok(maps);
    }

    @GetMapping("user")
    public JsonResult listAllUser()  {
        LOGGER.info("请求开始报告: 所有客户列表");
        List<Map<String, Object>> maps = this.farmService.listAllUser();
        return JsonResult.ok(maps);
    }

}
