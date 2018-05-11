package com.scen.pojo;


import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_admin_role")
public class AdminRole {

    @Id
    private Long id;
    private String roleName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
