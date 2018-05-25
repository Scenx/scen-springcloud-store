package com.scen.cache.service.impl;

import com.scen.cache.service.SyncCacheService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * 内容同步服务提供者
 *
 * @author Scen
 * @date 2018/5/18 14:28
 */
@RestController
public class SyncCacheServiceImpl implements SyncCacheService {


    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    @RabbitListener(queues = "syncContent")
    @RabbitHandler
    public void syncContent(Long contentCid) {
        redisTemplate.opsForHash().delete(INDEX_CONTENT_REDIS_KEY, contentCid + "");
    }

}
