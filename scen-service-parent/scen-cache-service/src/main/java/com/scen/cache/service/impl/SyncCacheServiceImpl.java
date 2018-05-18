package com.scen.cache.service.impl;

import com.scen.cache.service.SyncCacheService;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存同步服务提供者
 *
 * @author Scen
 * @date 2018/5/18 14:28
 */
@RestController
public class SyncCacheServiceImpl implements SyncCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public ScenResult syncContent(Long contentCid) {
        redisTemplate.opsForValue().set("hahahhaha", "哈哈哈哈哈");
        return ScenResult.ok();
    }

}
