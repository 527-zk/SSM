package com.ssm.pojo;

import java.util.List;

/**
 * @author kneesh
 * @Description 权限信息
 * @date 2021/4/15-20:24
 */
public class Permission {
    private Integer id;
    private String permissionName;
    private String url;
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}