package com.farm.admin.farm.service.impl;

import com.farm.admin.admin.mapper.UserMapper;
import com.farm.admin.farm.FarmParams;
import com.farm.admin.farm.FarmVO;
import com.farm.admin.farm.MultilevelLinkageMenuVO;
import com.farm.admin.farm.mapper.*;
import com.farm.admin.farm.service.FarmService;
import com.farm.base.BaseException;
import com.farm.base.common.Page;
import com.farm.base.common.enums.CategoryEnum;
import com.farm.base.common.enums.PlotTypeEnum;
import com.farm.base.farm.*;
import com.farm.base.user.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 ** @Date: 2019-04-27 00:04
 */
@Service
public class FarmServiceImpl implements FarmService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FarmServiceImpl.class);

    private FarmMapper farmMapper;
    private CropMapper cropMapper;
    private FarmWorkMapper farmWorkMapper;
    private PlotMapper plotMapper;
    private PlotCropMapper plotCropMapper;
    private UserMapper userMapper;

    @Autowired
    @SuppressWarnings("all")
    public FarmServiceImpl(FarmMapper farmMapper, CropMapper cropMapper, FarmWorkMapper farmWorkMapper,
                           PlotMapper plotMapper, PlotCropMapper plotCropMapper, UserMapper userMapper) {
        this.farmMapper = farmMapper;
        this.cropMapper = cropMapper;
        this.farmWorkMapper = farmWorkMapper;
        this.plotMapper = plotMapper;
        this.plotCropMapper = plotCropMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void saveFarm(FarmVO params) throws BaseException {
        String name = params.getName();
        Integer area = params.getArea();
        String creatorId = params.getCreatorId();
        if (StringUtils.isBlank(name) || StringUtils.isBlank(creatorId) || area == null) {
            LOGGER.error("必要参数为空 name:{}, area : {}, creatorId:{}", name, area, creatorId);
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        Farm farm = new Farm();
        farm.setArea(area);
        farm.setCreatorId(creatorId);
        farm.setName(name);
        farm.setBrooderCount(params.getBrooderCount());
        farm.setCoolCount(params.getCoolCount());
        this.farmMapper.insert(farm);

    }

    @Override
    public void updateFarm(FarmVO params) throws BaseException {
        Long id = params.getId();
        if (id == null) {
            LOGGER.error("必要参数 id 为空");
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        String name = params.getName();
        Integer area = params.getArea();
        String creatorId = params.getCreatorId();

        Farm farm = new Farm();
        farm.setId(id);
        farm.setArea(area);
        farm.setCreatorId(creatorId);
        farm.setName(name);
        this.farmMapper.update(farm);
    }

    @Override
    public Farm getDetail(Long farmId) throws BaseException {
        if (farmId == null) {
            LOGGER.error("必要参数 farmId 为空");
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        Farm farm = this.farmMapper.findById(farmId);
        return farm;
    }

    @Override
    public void delete(Long farmId) throws BaseException {
        if (farmId == null) {
            LOGGER.error("必要参数 farmId 为空");
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        this.farmMapper.delete(farmId);

    }

    @Override
    public Page pagingFarm(FarmParams params) throws BaseException {
        if (params.getPageNo() == null || params.getPageSize() == null) {
            LOGGER.error("分页条件为空 pageNo :{}, pageSize :{}", params.getPageNo(), params.getPageSize());
            throw new BaseException(BaseException.ERROR_CODE_ILLEGAL_ARGUMENTS);
        }
        Page<FarmParams> page = new Page<>(params.getPageNo(), params.getPageSize(), params);
        int totalRecord = this.farmMapper.countFarm(params);
        LOGGER.info("查询总数为 ：{}", totalRecord);
        page.setTotalCount(totalRecord);
        if (totalRecord > 0) {
            List<Map<String, Object>> list = this.farmMapper.pagingFarm(page);
            page.setData(list);
        }
        return page;
    }

    @Override
    public List<Crop> listCrop() {
        return this.cropMapper.listAll();
    }

    @Override
    public List<Map<String, Object>> listFarmWork() {
        List<FarmWork> farmWorks = this.farmWorkMapper.listFarmWork();

        List<Map<String, Object>> arrayList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(farmWorks)) {

            for (FarmWork farmWork : farmWorks) {
                Long id = farmWork.getId();
                Integer category = farmWork.getCategory();
                String name = farmWork.getName();
                Long parentId = farmWork.getParentId();

                String parent = parentId == 0 ? "父类" : "子项";

                CategoryEnum categoryEnum = CategoryEnum.valueOf(category);

                String detail = name + "-" + parent + "-" + categoryEnum.getMsg();

                Map<String, Object> map = new HashMap<>();
                map.put("id", id);
                map.put("detail", detail);

                arrayList.add(map);

            }

        }


        return arrayList;
    }

    @Override
    public List<Map<String, Object>> multilevelLinkageMenu(MultilevelLinkageMenuVO params) {

        Long cropId = params.getCropId();
        Long farmId = params.getFarmId();
        Long plotId = params.getPlotId();

        List<Map<String, Object>> resultList = new ArrayList<>();

        if (cropId != null) {
            //表示直接查询 品种
            List<PlotCrop> list = this.plotCropMapper.findByPlotIdAndCropId(plotId, cropId);
            if (CollectionUtils.isNotEmpty(list)) {
                resultList = list.stream().map(plotCrop -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", plotCrop.getId());
                    map.put("name", plotCrop.getCropVariety());
                    return map;

                }).collect(Collectors.toList());
            }
            return resultList;
        }

        if (plotId != null) {
            //表示直接查询农作物
            resultList = this.plotCropMapper.findByPlotId(plotId);
            return resultList;
        }

        if (farmId != null) {
            //表示查询地块
            List<Plot> list = this.plotMapper.findByFarmId(farmId);
            if (CollectionUtils.isNotEmpty(list)) {
                resultList = list.stream().map(plot -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", plot.getId());
                    PlotTypeEnum type = plot.getType();
                    String msg = type.getMsg();
                    map.put("name", plot.getName() + "(" + msg + ")");
                    return map;

                }).collect(Collectors.toList());
            }
            return resultList;
        }

        List<Map<String, Object>> maps = this.farmMapper.listFarm();

        resultList.addAll(maps);

        return resultList;
    }

    @Override
    public List<Map<String, Object>> listAllUser() {
        List<Map<String, Object>> maps = this.userMapper.listAll();
        return maps;
    }
}
