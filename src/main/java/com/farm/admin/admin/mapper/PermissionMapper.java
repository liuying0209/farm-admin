package com.farm.admin.admin.mapper;

import com.farm.admin.admin.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;


@Mapper
public interface PermissionMapper {


    Set<Map<String, Object>> findAllByAdmin();

    Set<Map<String, Object>> findAllByUserId(@Param("userId") Long userId);

    Set<Map<String, Object>> findAllPermission();

    Set<Map<String, Object>> findHasByDeptId(@Param("deptId") String deptId);

    Set<Map<String, Object>> findNoHasByPerId(@Param("permissions") String[] permissions);

    Set<Map<String, Object>> findHasByUserId(Map<String, Object> params);

    Set<Map<String, Object>> findNoHasByUserId(Map<String, Object> params);

    Set<Map<String, Object>> getUserPermissionByParent(Map<String, Object> params);

    Permission findById(Long id);

    int delete(Long id);

    int insert(Permission record);

    int updateByParams(Permission record);

    List<Map<String, Object>> listByPaging(Map<String, Object> params);

    int countListByPaging(Map<String, Object> params);
}
