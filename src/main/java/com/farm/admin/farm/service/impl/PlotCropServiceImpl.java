package com.farm.admin.farm.service.impl;

import com.farm.admin.farm.PlotCropParams;
import com.farm.admin.farm.mapper.PlotCropMapper;
import com.farm.admin.farm.service.PlotCropService;
import com.farm.base.BaseException;
import com.farm.base.common.Page;
import com.farm.base.farm.PlotCrop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 ** @Version 1.0.0
 */
@Service
public class PlotCropServiceImpl implements PlotCropService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PlotCropServiceImpl.class);

    @Autowired
    @SuppressWarnings("all")
    private PlotCropMapper plotCropMapper;

    @Override
    public Page pagingPlotCrop(PlotCropParams params) throws BaseException {
        if (params.getPageNo() == null || params.getPageSize() == null) {
            LOGGER.error("分页条件为空 pageNo :{}, pageSize :{}", params.getPageNo(), params.getPageSize());
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        Page<PlotCropParams> page = new Page<>(params.getPageNo(), params.getPageSize(), params);

        int totalCount = this.plotCropMapper.countPlotCrop(page);
        LOGGER.info("总数是 :{}", totalCount);
        page.setTotalCount(totalCount);
        if (totalCount > 0) {
            List<PlotCrop> list = this.plotCropMapper.pagingPlotCrop(page);
            page.setData(list);

        }
        return page;
    }

    @Override
    public List<PlotCrop> listAll(PlotCropParams params) {
        List<PlotCrop> list = this.plotCropMapper.listAll(params);
        return list;
    }
}
