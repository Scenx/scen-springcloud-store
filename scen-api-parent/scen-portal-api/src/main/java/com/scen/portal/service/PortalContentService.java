package com.scen.portal.service;

import com.scen.pojo.Content;
import com.scen.portal.service.hystrix.PortalContentServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 首页内容接口
 *
 * @author Scen
 * @date 2018/5/29 17:14
 */
@FeignClient(value = "scen-portal-service", fallback = PortalContentServiceHystrix.class)
public interface PortalContentService {

    /**
     * 返回给页面能解析的内容
     *
     * @param contentList
     * @return
     */
    @RequestMapping("portalContentService/getResultContentList")
    String getResultContentList(List<Content> contentList);
}
