package com.scen.admin.realm;


import com.scen.admin.service.AdminUserService;
import com.scen.pojo.AdminUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;


/**
 * @author Scen
 * @date 2018/5/9 9:37
 */
public class MyRealm extends AuthorizingRealm {

    @Resource
    private AdminUserService adminUserService;


    /**
     * 为当限前登录的用户授予角色和权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(adminUserService.getRoles(userName));
        authorizationInfo.setStringPermissions(adminUserService.getPermissions(userName));
        return authorizationInfo;
    }

    /**
     * 验证当前登录的用户
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        AdminUser adminUser = adminUserService.getByUserName(userName);
        if (adminUser != null) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(adminUser.getUserName(), adminUser.getPassword(), "xx");
            return authcInfo;
        } else {
            return null;
        }
    }

}
