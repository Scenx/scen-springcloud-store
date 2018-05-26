package com.scen.admin.config;

import com.scen.admin.realm.MyRealm;
import com.scen.common.utils.Captcha;
import feign.Logger;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * shiro配置文件
 *
 * @author Scen
 * @date 2018/5/25 23:52
 */
@Configuration
public class ShiroConfig {

    /**
     * 自定义Realm
     *
     * @return
     */
    @Bean
    public MyRealm myRealm() {
        return new MyRealm();
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager(MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    /**
     * Shiro过滤器
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        Shiro的核心安全接口,这个属性是必须的
        shiroFilter.setSecurityManager(securityManager);
//        身份认证失败，则跳转到登录页面的配置
        shiroFilter.setLoginUrl("/");
//        权限认证失败，则跳转到指定页面
        shiroFilter.setUnauthorizedUrl("/");
//       Shiro连接约束配置,即过滤链的定义
        shiroFilter.setFilterChainDefinitions("/adminLogin=anon\n/js/**=anon\n/css/**=anon\n/img/**=anon\n/layer/**=anon\n/drawCheckCode=anon\n/**=authc");
        return shiroFilter;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro注解
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public Captcha captcha() {
        return Captcha.getInstance();
    }

    @Bean
    Logger.Level feignLoggerLevel() {

        return Logger.Level.FULL;

    }


}
