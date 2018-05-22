package com.scen.admin.service.hystrix;

import com.scen.admin.service.ExcelService;
import com.scen.vo.ItemBean;
import com.scen.vo.ScenResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 生成报表熔断器
 *
 * @author Scen
 * @date 2018/5/11 22:02
 */
@Component
public class ExcelServiceHystrix implements ExcelService {

    @Override
    public List<ItemBean> getExcel(Long id, String title, String catName, Long startPrice, Long endPrice) throws Exception {
        return null;
    }
}
