package com.farm.admin.record;

import java.io.Serializable;

/**
 ** @Version 1.0.0
 */
public class CropTaskParams implements Serializable {


    private static final long serialVersionUID = 7823881516964277180L;
    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 作物id
     */
    private Long cropId;




    public CropTaskParams() {
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

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

}
