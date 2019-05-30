package com.farm.admin.admin.mapper;

import com.farm.base.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 ** @Date: 2019-04-20 19:42
 */
@Mapper
public interface UserMapper {

    int insert(User user);

    int update(User user);

    User findById(String id);

    List<User> findByMobileNumber(String username);

    List<Map<String,Object>> listAll();

    int delete(String id);

}
