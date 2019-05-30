package com.farm.admin.record.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.farm.admin.admin.Admin;
import com.farm.admin.farm.mapper.FarmWorkMapper;
import com.farm.admin.record.CropTaskParams;
import com.farm.admin.record.CropTaskVO;
import com.farm.admin.record.dto.CropTaskDTO;
import com.farm.admin.record.mapper.CropTaskMapper;
import com.farm.admin.record.service.CropTaskService;
import com.farm.admin.util.AdminUtil;
import com.farm.base.BaseException;
import com.farm.base.common.Page;
import com.farm.base.exception.FarmException;
import com.farm.base.farm.FarmWork;
import com.farm.base.record.CropTask;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 ** @Version 1.0.0
 */
@Service
public class CropTaskServiceImpl implements CropTaskService {
    private final static Logger LOGGER = LoggerFactory.getLogger(CropTaskServiceImpl.class);

    private CropTaskMapper cropTaskMapper;
    private FarmWorkMapper farmWorkMapper;

    @Autowired
    @SuppressWarnings("all")
    public CropTaskServiceImpl(CropTaskMapper cropTaskMapper, FarmWorkMapper farmWorkMapper) {
        this.cropTaskMapper = cropTaskMapper;
        this.farmWorkMapper = farmWorkMapper;
    }

    @Override
    public void saveCropTask(CropTaskVO params) throws BaseException {
        Long cropId = params.getCropId();
        Long farmingId = params.getFarmingId();
        String farmingName = params.getFarmingName();
        Integer number = params.getNumber();
        Integer score = params.getScore();

        Admin admin = AdminUtil.getAdmin();
        Long adminId = admin.getId();

        if (cropId == null || farmingId == null || StringUtils.isBlank(farmingName) ||
                number == null || score == null) {

            LOGGER.error("必要参数为空 {}", JSONObject.toJSONString(params));
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);

        }
        FarmWork farmWork = this.farmWorkMapper.findById(farmingId);

        if(farmWork==null){
            LOGGER.error("获取农事环节信息失败 farmingId :{}",farmingId);
            throw new FarmException(FarmException.FARM_WORK_INFO_ERROR);
        }

        CropTask cropTask = new CropTask();
        cropTask.setCropId(cropId);
        cropTask.setFarmingName(farmingName);
        cropTask.setFarmingId(farmingId);
        cropTask.setNumber(number);
        cropTask.setScore(score);
        cropTask.setAdminId(adminId);
        cropTask.setType(farmWork.getType());
        cropTask.setCategory(farmWork.getCategory());
        cropTask.setParentId(farmWork.getParentId());
        cropTaskMapper.insert(cropTask);
    }

    @Override
    public void updateCropTask(CropTaskVO params) throws BaseException {
        Long id = params.getId();
        Long cropId = params.getCropId();
        Long farmingId = params.getFarmingId();
        String farmingName = params.getFarmingName();
        Integer number = params.getNumber();
        Integer score = params.getScore();

        if (id == null) {
            LOGGER.error("必要参数 任务编号 id 为空");
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }

        CropTask cropTask = new CropTask();
        cropTask.setId(id);
        cropTask.setCropId(cropId);
        cropTask.setFarmingName(farmingName);
        cropTask.setFarmingId(farmingId);
        cropTask.setNumber(number);
        cropTask.setScore(score);
        this.cropTaskMapper.update(cropTask);

    }

    @Override
    public void deleteCropTask(Long taskId) throws BaseException {

        if (taskId == null) {
            LOGGER.error("必要参数 任务编号 id 为空");
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        this.cropTaskMapper.delete(taskId);

    }

    @Override
    public CropTaskDTO getDetail(Long taskId) throws BaseException {
        if (taskId == null) {
            LOGGER.error("必要参数 任务编号 id 为空");
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        CropTaskDTO cropTaskDTO = this.cropTaskMapper.findById(taskId);
        return cropTaskDTO;

    }

    @Override
    public Page pagingCropTask(CropTaskParams params) throws BaseException {
        if (params.getPageNo() == null || params.getPageSize() == null) {
            LOGGER.error("分页条件为空 pageNo :{}, pageSize :{}", params.getPageNo(), params.getPageSize());
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        Page<CropTaskParams> page = new Page<>(params.getPageNo(), params.getPageSize(), params);
        int totalRecord = this.cropTaskMapper.countCropTask(params);
        LOGGER.info("查询到的总数是 :{}", totalRecord);
        List<Map<String,Object>> arrayList = new ArrayList<>();
        page.setTotalCount(totalRecord);
        if (totalRecord > 0) {
            List<CropTaskDTO> cropTaskDTOS = this.cropTaskMapper.pagingCropTask(page);
            page.setData(cropTaskDTOS);
        }
        return page;
    }
}
