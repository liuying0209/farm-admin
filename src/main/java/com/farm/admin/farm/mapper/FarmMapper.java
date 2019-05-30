package com.farm.admin.farm.mapper;

import com.farm.admin.farm.FarmParams;
import com.farm.base.common.Page;
import com.farm.base.farm.Farm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FarmMapper {


    int delete(Long id);

    int insert(Farm record);


    Farm findById(Long id);

    int update(Farm record);

    List<Map<String,Object>> pagingFarm(Page<FarmParams> params);

    List<Map<String,Object>> listFarm();

    int countFarm(FarmParams params);

}