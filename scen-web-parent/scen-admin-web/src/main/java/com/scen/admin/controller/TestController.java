package com.scen.admin.controller;

import com.scen.admin.service.ContentCategoryService;
import com.scen.pojo.ContentCategory;
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
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/test")
    public void test(ContentCategory contentCategory) throws Exception {
        contentCategoryService.updateContentCategory(contentCategory);
    }
}
