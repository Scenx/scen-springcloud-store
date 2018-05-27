package com.scen.portal.controller;

import com.scen.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 加载商城首页controller
 *
 * @author Scen
 * @date 2018/3/28 13:06
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    /**
     * 加载首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(Model model) {
        String adJson = contentService.getContentList();
        model.addAttribute("ad1", adJson);
        return "index";
    }
}
