package com.scen.pojo;


import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_admin_user")
public class AdminUser {

    @Id
    private Long id;
    private String userName;
    private String password;
    private Long roleId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
