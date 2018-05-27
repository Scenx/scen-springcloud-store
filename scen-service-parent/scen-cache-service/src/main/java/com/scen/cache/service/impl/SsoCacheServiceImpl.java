package com.scen.cache.service.impl;

import com.scen.cache.service.SsoCacheService;
import com.scen.common.utils.JsonUtils;
import com.scen.pojo.User;
import com.scen.vo.ScenResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 单点登录缓存服务
 *
 * @author Scen
 * @date 2018/5/26 20:12
 */
@RestController
public class SsoCacheServiceImpl implements SsoCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Long SSO_SESSION_EXPIRE;

    @Override
    @RabbitListener(queues = "addUserSession")
    @RabbitHandler
    public void addUserSession(@RequestBody Map<String, String> map) {
        if (map != null) {
            String token = map.get("token");
            String user = map.get("user");
            redisTemplate.opsForValue().set(REDIS_USER_SESSION_KEY + ":" + token, user);
            redisTemplate.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE, TimeUnit.SECONDS);
        }
    }

    @Override
    public ScenResult getUserByToken(String token) {
        //        根据token从redis中查询用户信息
        String json = redisTemplate.opsForValue().get(REDIS_USER_SESSION_KEY + ":" + token);
        //        判断是否为空
        if (StringUtils.isBlank(json)) {
            return ScenResult.build(400, "此session已经过期，请重新登录");
        }
//        更新过期时间
        redisTemplate.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE, TimeUnit.SECONDS);
//        返回用户信息
        return ScenResult.ok(JsonUtils.jsonToPojo(json, User.class));
    }

    @Override
    public ScenResult logoutUserByToken(String token) {
        try {
            redisTemplate.delete(REDIS_USER_SESSION_KEY + ":" + token);
            return ScenResult.ok("");
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "服务器繁忙。。。");
        }
    }
}
