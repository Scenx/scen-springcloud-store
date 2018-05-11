package com.scen.admin.service.hystrix;

import com.scen.admin.service.AdminUserService;
import com.scen.pojo.AdminUser;

import java.util.Set;

/**
 * 后台用户熔断器
 *
 * @author Scen
 * @date 2018/5/11 22:30
 */
public class AdminUserServiceHystrix implements AdminUserService {

    @Override
    public AdminUser getByUserName(String userName) {
        return null;
    }

    @Override
    public Set<String> getRoles(String userName) {
        return null;
    }

    @Override
    public Set<String> getPermissions(String userName) {
        return null;
    }
}
