package com.scen.cache.service.impl;

import com.scen.cache.service.ItemCatCacheService;
import com.scen.common.utils.JsonUtils;
import com.scen.vo.CatResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类目缓存服务
 *
 * @author Scen
 * @date 2018/5/29 23:01
 */
@RestController
public class ItemCatCacheServiceImpl implements ItemCatCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${INDEX_ITEMCAT_REDIS_KEY}")
    private String INDEX_ITEMCAT_REDIS_KEY;

    @Override
    public CatResult getItemCatList() {
        String json = redisTemplate.opsForValue().get(INDEX_ITEMCAT_REDIS_KEY);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        //                把json转换成java对象
        return JsonUtils.jsonToPojo(json, CatResult.class);
    }

    @Override
    public void setItemCatList(String cacheString) {
        redisTemplate.opsForValue().set(INDEX_ITEMCAT_REDIS_KEY, cacheString);
    }
}
