package com.farm.admin.farm.service;

import com.farm.admin.farm.FarmParams;
import com.farm.admin.farm.FarmVO;
import com.farm.admin.farm.MultilevelLinkageMenuVO;
import com.farm.base.BaseException;
import com.farm.base.common.Page;
import com.farm.base.farm.Crop;
import com.farm.base.farm.Farm;
import com.farm.base.farm.FarmWork;
import com.farm.base.user.User;

import java.util.List;
import java.util.Map;

/**
 ** @Date: 2019-04-27 00:00
 */
public interface FarmService {
    /**
     * 添加
     */
    void saveFarm(FarmVO params) throws BaseException;

    /**
     * 修改
     */
    void updateFarm(FarmVO params) throws BaseException;

    /**
     * 详情
     */
    Farm getDetail(Long farmId) throws BaseException;

    /**
     * 删除
     */
    void delete(Long farmId) throws BaseException;
    /**
     * 列表
     */

    Page pagingFarm(FarmParams paramse) throws BaseException;

    /**
     * 查询所有农作物
     */

    List<Crop> listCrop();
    /**
     * 查询所有 农事环节
     */

    List<Map<String,Object>> listFarmWork();

    /**
     * 获取三级联动 地块 作物，品种
     */

    List<Map<String ,Object>> multilevelLinkageMenu(MultilevelLinkageMenuVO params) ;

    /**
     * 所有客户集合
     */
    List<Map<String, Object>> listAllUser();

}
