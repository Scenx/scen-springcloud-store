package com.scen.admin.service;

import com.scen.admin.service.hystrix.AdminUserServiceHystrix;
import com.scen.admin.service.hystrix.ExcelServiceHystrix;
import com.scen.pojo.AdminUser;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Set;

/**
 * 后台管理员接口
 *
 * @author Scen
 * @date 2018/5/9 15:36
 */
@FeignClient(value = "scen-admin-service", fallback = AdminUserServiceHystrix.class)
public interface AdminUserService {
    /**
     * 通过用户名查询用户
     *
     * @param userName
     * @return
     */
    AdminUser getByUserName(String userName);

    /**
     * 通过用户名查询角色信息
     *
     * @param userName
     * @return
     */
    Set<String> getRoles(String userName);

    /**
     * 通过用户名查询权限信息
     *
     * @param userName
     * @return
     */
    Set<String> getPermissions(String userName);
}
