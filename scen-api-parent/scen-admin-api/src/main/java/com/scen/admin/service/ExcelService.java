package com.scen.admin.service;

import com.scen.admin.service.hystrix.ExcelServiceHystrix;
import com.scen.vo.ItemBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
     * @param id
     * @param title
     * @param catName
     * @param startPrice
     * @param endPrice
     * @return
     * @throws Exception
     */
    @RequestMapping("/excelService/getExcel")
    List<ItemBean> getExcel(
            @RequestParam("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("catName") String catName,
            @RequestParam("startPrice") Long startPrice,
            @RequestParam("endPrice") Long endPrice
    ) throws Exception;
}
