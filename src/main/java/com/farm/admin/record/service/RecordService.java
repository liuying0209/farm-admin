package com.farm.admin.record.service;

import com.farm.admin.admin.AdminException;
import com.farm.admin.record.AssessRecordVO;
import com.farm.admin.record.RecordParams;
import com.farm.admin.record.dto.RecordDTO;
import com.farm.base.BaseException;
import com.farm.base.common.Page;
import com.farm.base.exception.FarmException;
import com.farm.base.exception.RecordException;
import com.farm.base.record.RecordAssess;

import java.util.List;

/**
 ** @Version 1.0.0
 */
public interface RecordService {

    /**
     * 分页
     * @param params
     * @return
     * @throws BaseException
     */
    Page pagingRecord(RecordParams params) throws BaseException;


    /**
     * 查询所有
     * @param params
     * @return
     */
    List<RecordDTO> listAllRecord(RecordParams params);
    /**
     * 获取下一个 未评估记录
      */

    RecordDTO getNoEvaluationRecord(RecordParams params);

    /**
     * 评估
     */

    void assessRecord(AssessRecordVO params) throws RecordException, AdminException, FarmException;

    /**
     * 更新评估
     */

    void updateAssess(AssessRecordVO params) throws RecordException, FarmException;

}
