package com.scen.cache.service.impl;

import com.scen.cache.service.ItemDescCacheService;
import com.scen.common.utils.JsonUtils;
import com.scen.pojo.ItemDesc;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 商品描述缓存服务
 *
 * @author Scen
 * @date 2018/5/27 22:06
 */
@RestController
public class ItemDescCacheServiceImpl implements ItemDescCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;


    @Value("${REDIS_ITEM_EXPIRE}")
    private Long REDIS_ITEM_EXPIRE;

    @Override
    public ItemDesc getItemDesc(Long itemId) {
        String json = redisTemplate.opsForValue().get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
        if (StringUtils.isBlank(json)) {
            return null;
        }
        //                把json转换成java对象
        return JsonUtils.jsonToPojo(json, ItemDesc.class);
    }

    @Override
    public void setItemDesc(@RequestBody ItemDesc itemDesc) {
        redisTemplate.opsForValue().set(REDIS_ITEM_KEY + ":" + itemDesc.getItemId() + ":desc", JsonUtils.objectToJson(itemDesc));
        redisTemplate.expire(REDIS_ITEM_KEY + ":" + itemDesc.getItemId() + ":desc", REDIS_ITEM_EXPIRE, TimeUnit.SECONDS);
    }
}
