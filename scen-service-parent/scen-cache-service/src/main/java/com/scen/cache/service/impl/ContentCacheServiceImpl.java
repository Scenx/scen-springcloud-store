package com.scen.cache.service.impl;

import com.scen.cache.service.ContentCacheService;
import com.scen.common.utils.JsonUtils;
import com.scen.pojo.Content;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 内容缓存
 *
 * @author Scen
 * @date 2018/5/27 18:22
 */
@RestController
public class ContentCacheServiceImpl implements ContentCacheService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<Content> getContentList(Long contentCid) {
        String result = (String) redisTemplate.opsForHash().get(INDEX_CONTENT_REDIS_KEY, contentCid + "");
        if (StringUtils.isBlank(result)) {
            return null;
        }
        //                把字符串转换成list
        List<Content> resultList = JsonUtils.jsonToList(result, Content.class);
        return resultList;
    }

    @Override
    public void setContentList(Long contentCid, @RequestBody List<Content> list) {
        String cacheString = JsonUtils.objectToJson(list);
        redisTemplate.opsForHash().put(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);
    }
}
