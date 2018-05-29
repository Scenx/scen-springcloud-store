package com.scen.portal.service.hystrix;

import com.scen.pojo.Content;
import com.scen.portal.service.PortalContentService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 首页内容熔断器
 *
 * @author Scen
 * @date 2018/5/29 17:15
 */
@Component
public class PortalContentServiceHystrix implements PortalContentService {

    @Override
    public String getResultContentList(List<Content> contentList) {
        return null;
    }
}
