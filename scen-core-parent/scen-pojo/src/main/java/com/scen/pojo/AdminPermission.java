package com.scen.pojo;


import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_admin_permission")
public class AdminPermission {

    @Id
    private Long id;
    private String permissionName;
    private Long roleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
