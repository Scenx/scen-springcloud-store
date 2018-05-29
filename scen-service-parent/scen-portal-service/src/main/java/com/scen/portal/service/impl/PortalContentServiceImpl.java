package com.scen.portal.service.impl;

import com.scen.common.utils.JsonUtils;
import com.scen.pojo.Content;
import com.scen.portal.service.PortalContentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页内容服务
 *
 * @author Scen
 * @date 2018/5/29 17:28
 */
@RestController
public class PortalContentServiceImpl implements PortalContentService {

    @Override
    public String getResultContentList(@RequestBody List<Content> contentList) {
        List<Map> resultList = new ArrayList<>();
//            创建一个jsp要求的pojo格式
        for (Content content : contentList) {
            Map map = new HashMap();
            map.put("src", content.getPic());
            map.put("height", 240);
            map.put("width", 670);
            map.put("srcB", content.getPic2());
            map.put("widthB", 550);
            map.put("heightB", 240);
            map.put("href", content.getUrl());
            map.put("alt", content.getSubTitle());
            resultList.add(map);
        }
        return JsonUtils.objectToJson(resultList);
    }
}
