package com.farm.admin.record;

import java.io.Serializable;

/**
 *
 * 评估记录页面参数类
 ** @Version 1.0.0
 */
public class AssessRecordVO implements Serializable {

    private Long recordId;

    private Integer deductMarks;

    /**
     * 扣分类型 0-存疑图片 1-严重作弊行为
     */
    private  Integer deductMarksType;

    private String reason;

    public AssessRecordVO() {
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Integer getDeductMarks() {
        return deductMarks;
    }

    public void setDeductMarks(Integer deductMarks) {
        this.deductMarks = deductMarks;
    }

    public Integer getDeductMarksType() {
        return deductMarksType;
    }

    public void setDeductMarksType(Integer deductMarksType) {
        this.deductMarksType = deductMarksType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
