package com.scen.portal.controller;

import com.scen.cache.service.ContentCacheService;
import com.scen.content.service.ContentService;
import com.scen.pojo.Content;
import com.scen.portal.service.PortalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 加载商城首页controller
 *
 * @author Scen
 * @date 2018/3/28 13:06
 */
@Controller
public class IndexController {

    @Autowired
    private ContentCacheService contentCacheService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private PortalContentService portalContentService;

    /**
     * 加载首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(Model model) {
        List<Content> contentList = null;
        contentList = contentCacheService.getContentList(89L);
        if (contentList == null) {
            contentList = contentService.getContentList(89L);
            contentCacheService.setContentList(89L, contentList);
        }
        String adJson = portalContentService.getResultContentList(contentList);
        model.addAttribute("ad1", adJson);
        return "index";
    }
}
