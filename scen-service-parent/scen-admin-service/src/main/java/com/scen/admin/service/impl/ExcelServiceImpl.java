package com.scen.admin.service.impl;

import com.scen.admin.service.ExcelService;

import javax.servlet.http.HttpServletResponse;

/**
 * 生成报表服务提供者
 *
 * @author Scen
 * @date 2018/5/11 22:02
 */
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void getExcel(HttpServletResponse response, Long id, String title, String catName, Long startPrice, Long endPrice) throws Exception {
        response.getWriter().print("服务不可用");
    }
}
