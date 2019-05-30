package com.farm.admin.record.mapper;

import com.farm.admin.record.CropTaskParams;
import com.farm.admin.record.dto.CropTaskDTO;
import com.farm.base.common.Page;
import com.farm.base.record.CropTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 ** @Version 1.0.0
 */
@Mapper
public interface CropTaskMapper {

    int insert(CropTask cropTask);

    int update(CropTask cropTask);

    CropTaskDTO findById(Long id);

    int delete(Long id);

    List<CropTaskDTO> pagingCropTask(Page<CropTaskParams> params);

    int countCropTask(CropTaskParams params);
}
