package com.scen.portal.service;

import com.scen.portal.service.hystrix.PortalParamDataServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 门户商品详细规格接口
 *
 * @author Scen
 * @date 2018/5/29 19:03
 */
@FeignClient(value = "scen-portal-service", fallback = PortalParamDataServiceHystrix.class)
public interface PortalParamDataService {


    /**
     * 获取门户商品详细规格
     *
     * @param paramData
     * @return
     */
    @RequestMapping("/portalParamDataService/getPortalParamData")
    String getPortalParamData(@RequestParam("paramData") String paramData);
}
