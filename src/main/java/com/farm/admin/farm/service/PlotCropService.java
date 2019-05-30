package com.farm.admin.farm.service;

import com.farm.admin.farm.PlotCropParams;
import com.farm.base.BaseException;
import com.farm.base.common.Page;
import com.farm.base.farm.PlotCrop;

import java.util.List;

/**
 ** @Version 1.0.0
 */
public interface PlotCropService  {

    /**
     * 查询品种作物列表
     */

    Page pagingPlotCrop(PlotCropParams params) throws BaseException;

    /**
     * 查询所有
     * @param params
     * @return
     */
    List<PlotCrop> listAll(PlotCropParams params);
}
