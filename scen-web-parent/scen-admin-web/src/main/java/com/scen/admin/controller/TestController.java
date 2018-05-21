package com.scen.admin.controller;

import com.scen.admin.service.ContentCategoryService;
import com.scen.admin.service.ContentService;
import com.scen.pojo.Content;
import com.scen.pojo.ContentCategory;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Scen
 * @date 2018/5/12 10:58
 */
@RestController
public class TestController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/test")
    public ScenResult test() throws Exception {
        Content content = new Content();
        content.setId(1L);
        content.setCategoryId(89L);
        return contentService.saveContent(content);
    }
}
