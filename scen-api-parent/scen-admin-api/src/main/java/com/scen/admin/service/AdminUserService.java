package com.scen.admin.service;

import com.scen.admin.service.hystrix.AdminUserServiceHystrix;
import com.scen.pojo.AdminUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @param username 用户名
     * @return 管理员对象
     */
    @RequestMapping("/adminUserService/getByUserName")
    AdminUser getByUserName(
            @RequestParam("username") String username
    );

    /**
     * 通过用户名查询角色信息
     *
     * @param username 用户名
     * @return Set集合包含查出来的所有用户
     */
    @RequestMapping("/adminUserService/getRoles")
    Set<String> getRoles(
            @RequestParam("username") String username
    );

    /**
     * 通过用户名查询权限信息
     *
     * @param username 用户名
     * @return Set集合包含查出来的所有用户
     */
    @RequestMapping("/adminUserService/getPermissions")
    Set<String> getPermissions(
            @RequestParam("username") String username
    );
}
