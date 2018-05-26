package com.scen.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转controller
 *
 * @author Scen
 * @date 2018/4/11 13:07
 */
@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * 打开注册页面
     *
     * @return
     */
    @RequestMapping("/register")
    public String showRegister() {
        return "register";
    }


    /**
     * 打开登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String showLogin(String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login";
    }
}
