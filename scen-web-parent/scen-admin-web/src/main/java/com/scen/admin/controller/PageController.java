package com.scen.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 *
 * @author Scen
 * @date 2018/3/9 20:44
 */
@Controller
public class PageController {

    /**
     * 展示首页
     *
     * @return
     */
    @RequestMapping("/admin-web")
    public String showAdminWeb() {
        return "admin-web";
    }


    @RequestMapping("/content")
    public String showContent() {
        return "content";
    }

    @RequestMapping("/content-add")
    public String showContentAdd() {
        return "content-add";
    }


    @RequestMapping("/content-category")
    public String showContentCategory() {
        return "content-category";
    }

    @RequestMapping("/content-edit")
    public String showContentEdit() {
        return "content-edit";
    }

    @RequestMapping("/item-add")
    public String showItemAdd() {
        return "item-add";
    }

    @RequestMapping("/item-edit")
    public String showItemEdit() {
        return "item-edit";
    }


    @RequestMapping("/item-list")
    public String showItemList() {
        return "item-list";
    }


    @RequestMapping("/item-param-add")
    public String showItemParamAdd() {
        return "item-param-add";
    }


    @RequestMapping("/item-param-edit")
    public String showItemParamEdt() {
        return "item-param-edit";
    }

    @RequestMapping("/item-param-list")
    public String showItemParamList() {
        return "item-param-list";
    }
}
