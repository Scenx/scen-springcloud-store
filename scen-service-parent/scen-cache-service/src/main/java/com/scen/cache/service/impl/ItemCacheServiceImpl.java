package com.scen.cache.service.impl;

import com.scen.cache.service.ItemCacheService;
import com.scen.common.utils.JsonUtils;
import com.scen.pojo.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 商品缓存服务
 *
 * @author Scen
 * @date 2018/5/27 21:46
 */
@RestController
public class ItemCacheServiceImpl implements ItemCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;


    @Value("${REDIS_ITEM_EXPIRE}")
    private Long REDIS_ITEM_EXPIRE;

    @Override
    public String getItemBaseInfo(Long itemId) {
        String json = redisTemplate.opsForValue().get(REDIS_ITEM_KEY + ":" + itemId + ":base");
        if (StringUtils.isBlank(json)) {
            return null;
        }
        //                把json转换成java对象
        return json;
    }

    @Override
    public void setItemBaseInfo(@RequestBody Item item) {
        redisTemplate.opsForValue().set(REDIS_ITEM_KEY + ":" + item.getId() + ":base", JsonUtils.objectToJson(item));
        redisTemplate.expire(REDIS_ITEM_KEY + ":" + item.getId() + ":base", REDIS_ITEM_EXPIRE, TimeUnit.SECONDS);
    }
}
