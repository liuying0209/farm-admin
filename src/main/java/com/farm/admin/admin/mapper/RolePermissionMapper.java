package com.farm.admin.admin.mapper;

import com.farm.admin.admin.RolePermission;
import org.apache.ibatis.annotations.*;

import java.util.Set;


@Mapper
public interface RolePermissionMapper {

    @Insert("INSERT INTO crm_role_permission( user_id, dept_id, permission_id) VALUES(#{userId}, #{deptId}, #{permissionId})")
    @Options(useGeneratedKeys = true)
    int insert(RolePermission rolePermission);

    @Select("SELECT p.NAME AS name FROM crm_role_permission rp, crm_permission p WHERE  p.id = rp.permission_id AND rp.dept_id = #{deptId}")
    Set<String> findPermissionByDeptId(@Param("deptId") String deptId);

    @Select("SELECT p.NAME AS name FROM crm_role_permission rp, crm_permission p WHERE  p.id = rp.permission_id AND rp.user_id = #{userId}")
    Set<String> findPermissionByUserId(@Param("userId") Long userId);

    @Select("SELECT p.ID AS name FROM crm_role_permission rp, crm_permission p WHERE  p.id = rp.permission_id AND rp.dept_id = #{deptId}")
    Set<String> findPermissionIdByDeptId(@Param("deptId") String deptId);

    @Select("SELECT p.ID AS name FROM crm_role_permission rp, crm_permission p WHERE  p.id = rp.permission_id AND rp.user_id = #{userId}")
    Set<String> findPermissionIdByUserId(@Param("userId") Long userId);

    @Select("SELECT NAME FROM crm_permission")
    Set<String> findAll();

    @Delete("DELETE from crm_role_permission where user_id = #{userId}")
    int deleteByUserId(@Param("userId") Long userId);

    @Delete("DELETE from crm_role_permission where dept_id = #{deptId}")
    int deleteByDeptId(@Param("deptId") String deptId);

    int deletePermissionByIdsAndUserIds(@Param("permissions") String[] permissions, @Param("userIds") Long[] userIds);
    
    RolePermission findByUserIdAndRoleName(@Param("userId") Long userId, @Param("roleName") String roleName);

    @Select("SELECT count(*) FROM crm_role_permission rp WHERE rp.permission_id = #{permissionsId} AND rp.user_id = #{userId}")
    int countByUserIdAndPermissionId(@Param("userId") Long userId, @Param("permissionsId") Long permissionsId);

    @Select("SELECT count(*) FROM crm_role_permission rp WHERE rp.permission_id = #{permissionsId} AND rp.dept_id = #{deptId}")
    int countByDeptIdAndPermissionId(@Param("deptId") String deptId, @Param("permissionsId") Long permissionsId);
}
