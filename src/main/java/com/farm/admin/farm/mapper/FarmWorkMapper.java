package com.farm.admin.farm.mapper;

import com.farm.base.farm.FarmWork;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FarmWorkMapper {

    /**
     * 获取所有农事环节
     * @return
     */
    List<FarmWork> listFarmWork();

    FarmWork findById(Long id);

}