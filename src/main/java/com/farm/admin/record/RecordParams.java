package com.farm.admin.record;

import java.io.Serializable;

/**
 * 记录多条件查询参数类
 ** @Version 1.0.0
 */
public class RecordParams implements Serializable {
    private static final long serialVersionUID = -2702955515940971093L;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页大小
     */
    private Integer pageSize;

    private Long farmId;

    private Long plotId;

    private Long cropId;

    private Long plotCropId;

    /**
     * 时间 2019
     */
    private String date;

    public RecordParams() {
    }

    public Long getFarmId() {
        return farmId;
    }

    public void setFarmId(Long farmId) {
        this.farmId = farmId;
    }

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public Long getPlotCropId() {
        return plotCropId;
    }

    public void setPlotCropId(Long plotCropId) {
        this.plotCropId = plotCropId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
