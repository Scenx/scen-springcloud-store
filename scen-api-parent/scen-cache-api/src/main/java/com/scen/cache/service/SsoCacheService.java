package com.scen.cache.service;

import com.scen.cache.service.hystrix.SsoCacheServiceHystrix;
import com.scen.vo.ScenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 单点登录缓存接口
 *
 * @author Scen
 * @date 2018/5/26 20:08
 */
@FeignClient(value = "scen-cache-service", fallback = SsoCacheServiceHystrix.class)
public interface SsoCacheService {

    /**
     * 存用户信息到缓存服务器
     *
     * @param map
     */
    @RequestMapping("/ssoCacheService/addUserSession")
    void addUserSession(Map<String, String> map);

    /**
     * 根据用户使用浏览器里的cookie存储的session令牌查看用户登录信息
     *
     * @param token
     * @return
     */
    @RequestMapping("/ssoCacheService/getUserByToken")
    ScenResult getUserByToken(@RequestParam("token") String token);


    /**
     * 根据用户使用浏览器里的cookie存储的session令牌注销用户
     *
     * @param token
     * @return
     */
    @RequestMapping("/ssoCacheService/logoutUserByToken")
    ScenResult logoutUserByToken(@RequestParam("token") String token);
}
