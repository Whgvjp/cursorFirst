package com.library.management.mapper;

import com.library.management.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户Mapper接口
 */
@Mapper
public interface UserMapper {
    
    /**
     * 新增用户
     */
    int insert(User user);
    
    /**
     * 根据ID更新用户
     */
    int updateById(User user);
    
    /**
     * 根据ID删除用户
     */
    int deleteById(@Param("id") Long id);
    
    /**
     * 根据ID查询用户
     */
    User selectById(@Param("id") Long id);
    
    /**
     * 根据用户名查询用户
     */
    User selectByUsername(@Param("username") String username);
    
    /**
     * 查询所有用户
     */
    List<User> selectAll();
    
    /**
     * 分页查询用户
     */
    List<User> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 根据条件查询用户
     */
    List<User> selectByCondition(@Param("username") String username, 
                                @Param("realName") String realName, 
                                @Param("userType") Integer userType);
    
    /**
     * 查询用户总数
     */
    int selectCount();
    
    /**
     * 更新用户状态
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}
