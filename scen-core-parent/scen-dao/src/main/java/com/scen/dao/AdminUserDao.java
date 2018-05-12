package com.scen.dao;

import com.scen.basedao.BaseDao;
import com.scen.pojo.AdminUser;

import java.util.Set;

/**
 * 管理员持久层
 *
 * @author Scen
 * @date 2018/5/11 19:44
 */
public interface AdminUserDao extends BaseDao<AdminUser> {

    /**
     * 通过用户名查询角色信息
     *
     * @param username
     * @return
     */
    Set<String> getRoles(String username);

    /**
     * 通过用户名查询权限信息
     *
     * @param username
     * @return
     */
    Set<String> getPermissions(String username);
}
