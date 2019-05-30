package com.farm.admin.farm;

import java.io.Serializable;

/**
 * 多级联动菜单传递参数类
 ** @Version 1.0.0
 */
public class MultilevelLinkageMenuVO implements Serializable {
    private static final long serialVersionUID = 2812438274390658054L;

    /**
     * 农场id
     */
    private Long farmId;
    /**
     * 地块id
     */
    private Long plotId;

    /**
     * 作物id
     */
    private Long cropId;


    public MultilevelLinkageMenuVO() {
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
}
