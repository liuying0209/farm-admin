package com.farm.admin.record.dto;

import com.farm.base.common.enums.DeductMarksTypeEnum;
import com.farm.base.common.enums.FarmingRecordStatusEnum;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 品种作物记录农事环节DTO
 *
 ** @Version 1.0.0
 */

public class RecordDTO implements Serializable {
    private static final long serialVersionUID = 442951516298487940L;

    /**
     * '记录编号'
     */
    private Long recordId;

    /**
     * 农场名称
     */
    private String farmName;
    /**
     * 地块
     */
    private String plotName;
    /**
     * 作物名称
     */
    private String cropName;
    /**
     * 品种名称  批次
     */
    private String cropVariety;

    /**
     * 农事环节
     */
    private String farmWorkName;
    /**
     * 农事时间
     */
    private Date time;

    /**
     * 记录时间
     */
    private Date updateTime;
    /**
     * '系统计算得分'
     */
    private Integer score;

    /**
     * 是否评估标识 0-否 1-是
     */
    private Integer assessFlag;
    /**
     * 评估得分
     */
    private Integer deductMarks = 0;
    /**
     * '系统扣分说明'
     */
    private String remark;
    /**
     * '人工说明'
     */
    private String reason;
    /**
     * 扣分类型 0-存疑图片 1-严重作弊行为 2-无 3-加分
     */
    private DeductMarksTypeEnum deductMarksType;
    /**
     * 状态 0-未开始 1-未完成 2-已完成
     */
    private FarmingRecordStatusEnum status;

    /**
     * 图片
     */
    private List<String> images;

    /**
     * 详细信息
     */
    private String detail;

    public RecordDTO() {
    }

    public Integer getAssessFlag() {
        return assessFlag;
    }

    public void setAssessFlag(Integer assessFlag) {
        this.assessFlag = assessFlag;
    }

    public DeductMarksTypeEnum getDeductMarksType() {
        return deductMarksType;
    }

    public void setDeductMarksType(DeductMarksTypeEnum deductMarksType) {
        this.deductMarksType = deductMarksType;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropVariety() {
        return cropVariety;
    }

    public void setCropVariety(String cropVariety) {
        this.cropVariety = cropVariety;
    }

    public String getFarmWorkName() {
        return farmWorkName;
    }

    public void setFarmWorkName(String farmWorkName) {
        this.farmWorkName = farmWorkName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getDeductMarks() {
        return deductMarks;
    }

    public void setDeductMarks(Integer deductMarks) {
        this.deductMarks = deductMarks;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public FarmingRecordStatusEnum getStatus() {
        return status;
    }

    public void setStatus(FarmingRecordStatusEnum status) {
        this.status = status;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    @Override
    public String toString() {
        return "RecordDTO{" +
                "recordId=" + recordId +
                ", farmName='" + farmName + '\'' +
                ", plotName='" + plotName + '\'' +
                ", cropName='" + cropName + '\'' +
                ", cropVariety='" + cropVariety + '\'' +
                ", farmWorkName='" + farmWorkName + '\'' +
                ", time='" + time + '\'' +
                ", updateTime=" + updateTime +
                ", score=" + score +
                ", assessFlag=" + assessFlag +
                ", deductMarks=" + deductMarks +
                ", remark='" + remark + '\'' +
                ", reason='" + reason + '\'' +
                ", deductMarksType=" + deductMarksType +
                ", status=" + status +
                ", images=" + images +
                ", detail='" + detail + '\'' +
                '}';
    }
}
