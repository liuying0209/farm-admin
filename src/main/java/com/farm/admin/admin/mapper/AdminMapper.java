package com.farm.admin.admin.mapper;


import com.farm.admin.admin.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 */
@Mapper
public interface AdminMapper {

    int insert(Admin Admin);

    Admin findCurrentAdmin(Long adminId);

    @Insert("insert into crm_admin(username, password,is_password,email,mobile,status,is_administrator)"
            + " values (#{username},#{password},#{isPassword},#{email},#{mobile},#{status},#{isAdministrator})")
    @Options(useGeneratedKeys = true)
    int insertAdministrator(Admin admin);

    Map<String, Object> findById(Long id);

    List<Map<String, Object>> pagingAdmin(@Param("pageStart") Integer pageStart, @Param("pageSize") Integer pageSize,@Param("username")String username);

    int countAdmin(@Param("username") String username);

    int update(Admin Admin);

    List<Map<String, Object>> getParents(Map<String, Object> params);

    @Update("UPDATE crm_admin SET status = #{status} WHERE id = #{id}")
    int updateAdminStatus(@Param("status") int status, @Param("id") Long id);

    @Select("SELECT * FROM crm_admin where mobile = #{mobile} and password = #{password}")
    @ResultMap("adminResultMap")
    Admin login(@Param("mobile") String mobile, @Param("password") String password);

    Map<String, Object> findLevelAndDeptByAdminId(@Param("id") Long id);

    @Update("UPDATE crm_admin SET password = #{password} where id = #{id}")
    int modifyPassword(@Param("password") String password, @Param("id") Long id);

    @Update("UPDATE crm_admin SET is_password = #{isPassword} where id = #{id}")
    int isPasswordStatus(@Param("isPassword") boolean isPassword, @Param("id") Long id);

    @Select("SELECT count(1) FROM crm_admin where id = #{id}")
    int countByUserId(@Param("id") Long id);

    @Select("SELECT count(1) FROM crm_admin where mobile = #{mobile}")
    int countByUserMobile(@Param("mobile") String mobile);

    Long[] getSubordinateIdByParams(@Param("admin_id") Long[] adminId, @Param("grade") int grade, @Param("dept_id") String deptId);

    Long[] getSubordinateIdByIds(@Param("admin_id") Long[] adminId);

    @Delete("DELETE FROM crm_admin WHERE id = #{adminId}")
    int delete(@Param("adminId") Long adminId);

    /**
     * 根据部门编号查询该部门下所有员工
     *
     * @return 部门下所有员工
     */
    List<Map<String, Object>> findAdminsByDeptId(Map<String, Object> params);

    /**
     * 查询部门下员工集合
     */
    List<Map<String, Object>> listServiceAdmin(Map<String, Object> params);

    int countFindAdminsByDeptId(Map<String, Object> params);

    /**
     * 根据名称查询adminId
     */

    @Select("select * from crm_admin where username=#{username}")
    @ResultMap("adminResultMap")
    Admin findByName(String username);
}
