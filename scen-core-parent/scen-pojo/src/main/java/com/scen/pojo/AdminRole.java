package com.scen.pojo;


import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tb_admin_role")
public class AdminRole implements Serializable {

    private static final long serialVersionUID = 8053214458244500620L;

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
