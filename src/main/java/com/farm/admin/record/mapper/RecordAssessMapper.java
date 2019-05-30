package com.farm.admin.record.mapper;

import com.farm.base.record.RecordAssess;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordAssessMapper {


    int delete(Long id);

    int insert(RecordAssess record);


    RecordAssess findById(Long id);

    RecordAssess findByRecordId(Long recordId);


    int update(RecordAssess record);
}