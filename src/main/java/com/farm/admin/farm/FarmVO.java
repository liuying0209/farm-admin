package com.farm.admin.farm;

import java.io.Serializable;

/**
 * 创建农场参数类
 ** @Date: 2019-04-27 00:05
 */
public class FarmVO implements Serializable {
    private static final long serialVersionUID = 5556793170121033095L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 农场名称
     */
    private String name;

    /**
     * 农场面积
     */
    private Integer area;

    /**
     * 单位   0-亩 1-平方米
     */
    private Integer unit;

    /**
     * 农场总的分
     */
    private Integer score;


    /**
     * 暖棚数量
     */
    private Integer  brooderCount;

    /**
     * 冷棚数量
     */
    private Integer coolCount;

    /**
     * 创建人id
     */
    private String creatorId;

    public FarmVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getBrooderCount() {
        return brooderCount;
    }

    public void setBrooderCount(Integer brooderCount) {
        this.brooderCount = brooderCount;
    }

    public Integer getCoolCount() {
        return coolCount;
    }

    public void setCoolCount(Integer coolCount) {
        this.coolCount = coolCount;
    }
}
