package com.farm.admin.farm;

import java.io.Serializable;

/**
 * 多条件查询参数类
 ** @Date: 2019-04-27 00:21
 */
public class FarmParams implements Serializable {

    private static final long serialVersionUID = 1144629192205134462L;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 农场名称
     */
    private String farmName;

    public FarmParams() {
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

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }
}
