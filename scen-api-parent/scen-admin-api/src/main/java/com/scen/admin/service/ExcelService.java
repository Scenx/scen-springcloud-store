package com.scen.admin.service;

import com.scen.admin.service.hystrix.ExcelServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;

import javax.servlet.http.HttpServletResponse;

/**
 * 生成报表接口
 *
 * @author Scen
 * @date 2018/5/11 22:01
 */
@FeignClient(value = "scen-admin-service", fallback = ExcelServiceHystrix.class)
public interface ExcelService {
    /**
     * 生成商品报表
     *
     * @param response
     * @param id
     * @param title
     * @param catName
     * @param startPrice
     * @param endPrice
     * @throws Exception
     */
    void getExcel(HttpServletResponse response, Long id, String title, String catName, Long startPrice, Long endPrice) throws Exception;
}
