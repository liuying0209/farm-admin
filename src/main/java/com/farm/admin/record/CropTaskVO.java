package com.farm.admin.record;

import java.io.Serializable;

/**
 * 给作物添加任务页面参数类
 ** @Version 1.0.0
 */
public class CropTaskVO implements Serializable {

    private static final long serialVersionUID = -4186068692445125538L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 作物id
     */
    private Long cropId;

    /**
     * 农事环节id
     */
    private Long farmingId;

    /**
     * 具体农事环节名称
     */
    private String farmingName;

    /**
     * 排序编号
     */
    private Integer number;

    /**
     * 预计得分
     */
    private Integer score;

    public CropTaskVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFarmingName() {
        return farmingName;
    }

    public void setFarmingName(String farmingName) {
        this.farmingName = farmingName;
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


}
