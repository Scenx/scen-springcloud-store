package com.scen.admin.controller;

import com.scen.admin.service.AdminUserService;
import com.scen.pojo.AdminUser;
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
    private AdminUserService adminUserService;

    @RequestMapping("/test")
    public AdminUser test() throws Exception {
        return adminUserService.getByUserName("admin");
    }
}
