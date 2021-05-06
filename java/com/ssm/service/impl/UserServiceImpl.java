package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.UserDao;
import com.ssm.pojo.Role;
import com.ssm.pojo.UserInfo;
import com.ssm.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kneesh
 * @Description 自定义 UserService的实现类
 * @date 2021/4/20-09:51
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登录权限认证的service，我们这里没有写Controller，因为spring security帮我们写了
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.queryUserByUsername(username);
        // 注意这里的 userInfo 与实际需要的 UserDetails 类型不一致，userInfo是我们自己创建的，
        // 而UserDetails 是Spring security 所需要的
        // 这里我们创建一个Spring security 自带的 User对象，而 User 实现了 UserDetails 接口
        // 并将我们自己的用户对象封装为 UserDetails
        User user = new User(userInfo.getUsername(),userInfo.getPassword(), userInfo.getStatus() != 0,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    /**
     * 获取当前登录用户的权限信息
     * @param roles
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    /**
     * 依据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public UserInfo queryUserByName(String userName) {
        UserInfo userInfo = userDao.queryUserByUsername(userName);
        return userInfo;
    }

    /**
     * 查询全部用户
     * @return
     */
    @Override
    public List<User> queryAllUsers() {
        return userDao.queryAllUser();
    }
    /**
     * 查询所有的用户--分页查询
     * @return
     */
    @Override
    public List<User> queryAllUser(int page,int size) {
        PageHelper.startPage(page,size);
        return userDao.queryAllUser();
    }

    /**
     * 添加用户
     * @param userInfo
     */
    @Override
    public void addUser(UserInfo userInfo) {
        // 使用 BCryptPasswordEncoder 对添加用户的密码进行加密
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.addUser(userInfo);
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUser(Integer id) {
        // 使用 BCryptPasswordEncoder 对添加用户的密码进行加密
        userDao.deleteUser(id);
    }
    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @Override
    public UserInfo queryUserById(int id) {
        return userDao.queryUserById(id);
    }

    /**
     * 查询用户所没有的角色信息
     * @param id
     * @return
     */
    @Override
    public List<Role> queryOtherRoles(int id) {
        return userDao.queryOtherRoles(id);
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     */
    @Override
    public void addRoleToUser(int userId, int[] roleIds) {
        for (int roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    /**
     * 将用户从角色中删除
     * @param userId
     * @param roleIds
     */
    @Override
    public void deleteRoleFromUser(int userId, int[] roleIds) {
        for (int roleId : roleIds) {
            userDao.deleteRoleFromUser(userId,roleId);
        }
    }

    /**
     * 修改用户个人信息
     *
     * @param userName
     * @param newEmail
     * @param newPhoneNum
     */
    @Override
    public void updateUserInfo(String userName, String newEmail, String newPhoneNum) {
        userDao.updateUserInfo(userName,newEmail,newPhoneNum);
    }

    /**
     * 修改密码
     *
     * @param userName
     * @param newPassword
     */
    @Override
    public void updatePassword(String userName, String newPassword) {
        String cryptPassword = bCryptPasswordEncoder.encode(newPassword);
        userDao.updatePassword(userName,cryptPassword);
    }

}
