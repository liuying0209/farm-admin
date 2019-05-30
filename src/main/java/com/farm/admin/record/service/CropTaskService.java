package com.farm.admin.record.service;

import com.farm.admin.record.CropTaskParams;
import com.farm.admin.record.CropTaskVO;
import com.farm.admin.record.dto.CropTaskDTO;
import com.farm.base.BaseException;
import com.farm.base.common.Page;

/**
 ** @Version 1.0.0
 */
public interface CropTaskService {

    /**
     * 添加
     */
    void saveCropTask(CropTaskVO params) throws BaseException;

    /**
     * 修改
     */

    void updateCropTask(CropTaskVO params) throws BaseException;

    /**
     * 删除
     *
     */
    void deleteCropTask(Long taskId) throws BaseException;
    /**
     * 查询详情
     */
    CropTaskDTO getDetail(Long taskId) throws BaseException;

    /**
     * 分页查询
     */

    Page pagingCropTask(CropTaskParams params) throws BaseException;
}
