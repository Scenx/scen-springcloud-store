package com.scen.cache.service.hystrix;

import com.scen.cache.service.SsoCacheService;
import com.scen.vo.ScenResult;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 单点登录缓存熔断器
 *
 * @author Scen
 * @date 2018/5/26 20:09
 */
@Component
public class SsoCacheServiceHystrix implements SsoCacheService {

    @Override
    public void addUserSession(Map<String, String> map) {

    }

    @Override
    public ScenResult getUserByToken(String token) {
        return ScenResult.build(233, "服务不可用");
    }
}
