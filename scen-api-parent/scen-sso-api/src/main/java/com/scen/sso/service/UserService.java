package com.scen.sso.service;


import com.scen.pojo.User;
import com.scen.sso.service.hystrix.UserServiceHystrix;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户操作接口
 *
 * @author Scen
 * @date 2018/4/11 8:59
 */
@FeignClient(value = "scen-sso-service", fallback = UserServiceHystrix.class)
public interface UserService {

    /**
     * 检查注册数据是否可用
     *
     * @param content
     * @param type
     * @return
     */
    @RequestMapping("/userService/checkData")
    ScenResult checkData(
            @RequestParam("content") String content,
            @RequestParam("type") Integer type
    );

    /**
     * 用户注册
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/userService/createUser")
    ScenResult createUser(User user) throws Exception;

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/userService/userLogin")
    ScenResult userLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    );

}
