package com.ssm.service;

import com.ssm.pojo.Role;
import com.ssm.pojo.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author kneesh
 * @Description 自定义的spring security服务接口并继承UserDetailsService，用于规范spring security接口
 * @date 2021/4/20-09:53
 */
public interface UserService extends UserDetailsService {
    /**
     * 依据用户名查询用户信息
     * @param userName
     * @return
     */
    UserInfo queryUserByName(String userName);

    /**
     * 查询全部用户
     * @return
     */
    List<User> queryAllUsers();
    /**
     * 查询所有的用户--分页查询
     * @param page
     * @param size
     * @return
     */
    List<User> queryAllUser(int page, int size);

    /**
     * 添加用户
     * @param userInfo
     */
    void addUser(UserInfo userInfo);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);
    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    UserInfo queryUserById(int id);

    /**
     * 通过ID查询出用户没有的角色
     * @param id
     * @return
     */
    List<Role> queryOtherRoles(int id);

    /**
     * 给用户批量添加角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(int userId, int[] roleIds);

    /**
     * 将用户从角色中删除
     * @param userId
     * @param roleIds
     */
    void deleteRoleFromUser(int userId, int[] roleIds);
    /**
     * 修改用户个人信息
     * @param userName
     * @param newEmail
     * @param newPhoneNum
     */
    void updateUserInfo(String userName, String newEmail, String newPhoneNum);

    /**
     * 修改密码
     * @param userName
     * @param newPassword
     */
    void updatePassword(String userName, String newPassword);
}
