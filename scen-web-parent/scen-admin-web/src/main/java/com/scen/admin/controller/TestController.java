package com.scen.admin.controller;

import com.scen.admin.service.ExcelService;

import com.scen.vo.ItemBean;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Scen
 * @date 2018/5/12 10:58
 */
@RestController
public class TestController {

    @Autowired
    private ExcelService excelService;

    @RequestMapping("/test")
    public ScenResult test(Long id, String title, String catName, Long startPrice, Long endPrice) throws Exception {
        List<ItemBean> list = excelService.getExcel(id, title, catName, startPrice, endPrice);
        return ScenResult.ok(list);
    }
}
