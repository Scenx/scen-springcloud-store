package com.scen.cache.service.impl;

import com.scen.cache.service.ItemParamItemCacheService;
import com.scen.common.utils.JsonUtils;
import com.scen.pojo.ItemParamItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 商品具体参数缓存服务
 *
 * @author Scen
 * @date 2018/5/27 22:43
 */
@RestController
public class ItemParamItemCacheServiceImpl implements ItemParamItemCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;


    @Value("${REDIS_ITEM_EXPIRE}")
    private Long REDIS_ITEM_EXPIRE;

    @Override
    public ItemParamItem getItemParamItem(Long itemId) {
        String json = redisTemplate.opsForValue().get(REDIS_ITEM_KEY + ":" + itemId + ":param");
        if (StringUtils.isBlank(json)) {
            return null;
        }
        //                把json转换成java对象
        return JsonUtils.jsonToPojo(json, ItemParamItem.class);
    }

    @Override
    public void setItemParamItem(ItemParamItem itemParamItem) {
        redisTemplate.opsForValue().set(REDIS_ITEM_KEY + ":" + itemParamItem.getItemId() + ":param", JsonUtils.objectToJson(itemParamItem));
        redisTemplate.expire(REDIS_ITEM_KEY + ":" + itemParamItem.getItemId() + ":param", REDIS_ITEM_EXPIRE, TimeUnit.SECONDS);
    }
}
