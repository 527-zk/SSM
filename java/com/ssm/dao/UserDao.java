package com.ssm.dao;

import com.ssm.pojo.Role;
import com.ssm.pojo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author kneesh
 * @Description 用户信息
 * @date 2021/4/10-20:35
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    UserInfo queryUserByUsername(@Param("username") String username);

    /**
     * 查询所有的用户
     * @return
     */
    List<User> queryAllUser();

    /**
     * 添加用户
     * @param userInfo
     */
    void addUser(UserInfo userInfo);

    /**
     * 删除指定ID的用户
     * @param id
     */
    void deleteUser(@Param("id") Integer id);
    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    UserInfo queryUserById(@Param("id") int id);

    /**
     * 查询出用户没有的角色信息
     * @param id
     * @return
     */
    List<Role> queryOtherRoles(@Param("id") int id);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleId
     */
    void addRoleToUser(@Param("userId") int userId, @Param("roleId") int roleId);
    /**
     * 将用户从角色中删除
     * @param userId
     * @param roleId
     */
    void deleteRoleFromUser(@Param("userId") int userId, @Param("roleId") int roleId);

    /**
     * 修改用户个人信息(邮箱地址或联系电话)
     * @param userName
     * @param newEmail
     * @param newPhoneNum
     */
    void updateUserInfo(@Param("userName") String userName, @Param("email") String newEmail, @Param("phoneNum") String newPhoneNum);

    /**
     * 修改密码
     * @param userName
     * @param newPassword
     */
    void updatePassword(@Param("userName") String userName, @Param("newPassword") String newPassword);
}
