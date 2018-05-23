package com.scen.admin.controller;

import com.scen.vo.ScenResult;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Scen
 * @date 2018/5/12 10:58
 */
@RestController
public class TestController {


    @RequestMapping("/test")
    public ScenResult test(Long id, String title, String catName, Long startPrice, Long endPrice) throws Exception {
        return ScenResult.ok();
    }
}
