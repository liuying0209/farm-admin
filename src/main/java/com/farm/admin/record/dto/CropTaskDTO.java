package com.farm.admin.record.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 农作物任务DTO
 *
 ** @Version 1.0.0
 */
public class CropTaskDTO implements Serializable {


    private static final long serialVersionUID = -9055455336902461791L;
    /**
     * 主键
     */
    private Long taskId;

    /**
     * 作物id
     */
    private Long cropId;

    /**
     * 农事环节id
     */
    private Long farmingId;
    /**
     * 农事环节父节点id
     */
    private Long parentId;

    /**
     * 具体农事环节名称
     */
    private String farmingName;
    /**
     * 作物名称
     */

    private String cropName;
    /**
     * 排序编号
     */
    private Integer number;

    /**
     * 预计得分
     */
    private Integer score;


    private Date updateTime;


    public CropTaskDTO() {
    }


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public Long getFarmingId() {
        return farmingId;
    }

    public void setFarmingId(Long farmingId) {
        this.farmingId = farmingId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getFarmingName() {
        return farmingName;
    }

    public void setFarmingName(String farmingName) {
        this.farmingName = farmingName;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CropTaskDTO{" +
                "taskId=" + taskId +
                ", cropId=" + cropId +
                ", farmingId=" + farmingId +
                ", parentId=" + parentId +
                ", farmingName='" + farmingName + '\'' +
                ", cropName='" + cropName + '\'' +
                ", number=" + number +
                ", score=" + score +
                ", updateTime=" + updateTime +
                '}';
    }


    public String getDesc() {

        return parentId == 0 ? "父类" : "子项";
    }
}
