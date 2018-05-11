package com.scen.admin.service.hystrix;

import com.scen.admin.service.ExcelService;

import javax.servlet.http.HttpServletResponse;

/**
 * 生成报表熔断器
 *
 * @author Scen
 * @date 2018/5/11 22:02
 */
public class ExcelServiceHystrix implements ExcelService {

    @Override
    public void getExcel(HttpServletResponse response, Long id, String title, String catName, Long startPrice, Long endPrice) throws Exception {
        response.getWriter().print("服务不可用");
    }
}
