package com.scen.admin.service.hystrix;

import com.scen.admin.service.AdminUserService;
import com.scen.pojo.AdminUser;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 后台用户熔断器
 *
 * @author Scen
 * @date 2018/5/11 22:30
 */
@Component
public class AdminUserServiceHystrix implements AdminUserService {

    @Override
    public AdminUser getByUserName(String username) {
        return null;
    }

    @Override
    public Set<String> getRoles(String username) {
        return null;
    }

    @Override
    public Set<String> getPermissions(String username) {
        return null;
    }
}
