package com.scen.admin.controller;

import com.scen.admin.service.ContentCategoryService;
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
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/test")
    public ScenResult test() {
        try {
            return contentCategoryService.deleteContenCategory(99L);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(233, "服务不可用");
        }
    }
}
