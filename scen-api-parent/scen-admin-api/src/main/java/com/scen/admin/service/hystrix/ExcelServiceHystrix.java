package com.scen.admin.service.hystrix;

import com.scen.admin.service.ExcelService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
/**
 * 生成报表熔断器
 *
 * @author Scen
 * @date 2018/5/11 22:02
 */
@Component
public class ExcelServiceHystrix implements ExcelService {

    @Override
    public void getExcel(Long id, String title, String catName, Long startPrice, Long endPrice) throws Exception {

    }
}
