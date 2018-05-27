package com.scen.cache.service.hystrix;

import com.scen.cache.service.ContentCacheService;
import com.scen.pojo.Content;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 内容缓存熔断器
 *
 * @author Scen
 * @date 2018/5/27 18:11
 */
@Component
public class ContentCacheServiceHystrix implements ContentCacheService {

    @Override
    public List<Content> getContentList(Long contentCid) {
        return null;
    }

    @Override
    public void setContentList(Long contentCid, List<Content> list) {

    }
}
