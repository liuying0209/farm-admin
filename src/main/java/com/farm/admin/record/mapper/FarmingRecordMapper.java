package com.farm.admin.record.mapper;

import com.farm.admin.record.RecordParams;
import com.farm.admin.record.dto.RecordDTO;
import com.farm.base.common.Page;
import com.farm.base.record.FarmingRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FarmingRecordMapper {

    int delete(Long id);

    int insert(FarmingRecord record);

    FarmingRecord findById(Long id);

    int update(FarmingRecord record);

    /**
     * 分页查询
     */
    List<RecordDTO> pagingRecord(Page<RecordParams> page);

    RecordDTO getNoEvaluationRecord(RecordParams params);

    List<RecordDTO> listAllRecord(RecordParams params);

    int countRecord(Page<RecordParams> page);


    /**
     * 计算当前品种作物农事环节得分 扣除评估
     * @param plotCropId
     * @return
     */
    int countScoreByPlotCropId(Long plotCropId);

}