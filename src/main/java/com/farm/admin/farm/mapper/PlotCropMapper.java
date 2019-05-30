package com.farm.admin.farm.mapper;

import com.farm.admin.farm.PlotCropParams;
import com.farm.base.common.Page;
import com.farm.base.farm.PlotCrop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PlotCropMapper {

    int delete(Long id);

    int insert(PlotCrop record);

    PlotCrop findById(Long id);

    List<Map<String,Object>> findByPlotId(Long plotId);

    /**
     * 根据 地块id和 作物id
     * @return
     */
    List<PlotCrop> findByPlotIdAndCropId(@Param("plotId") Long plotId, @Param("cropId") Long cropId);

    int update(PlotCrop record);

    List<PlotCrop> pagingPlotCrop(Page<PlotCropParams> page);

    List<PlotCrop> listAll(PlotCropParams page);

    int countPlotCrop(Page<PlotCropParams> page);


    //获取农场id
    long getFarmId(Long plotCropId);


    /**
     * 计算农场所有品种作物得分
     */
    int countFarmScore(Long farmId);

    /**
     * 计算农场作物个数
     */
    int countPlotCropByFarmId(Long farmId);

}