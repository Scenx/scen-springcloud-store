package com.scen.search.service.hystrix;

import com.scen.search.service.SearchItemService;
import com.scen.vo.ScenResult;
import com.scen.vo.SearchResult;
import org.springframework.stereotype.Component;

/**
 * 商品检索熔断器
 *
 * @author Scen
 * @date 2018/5/25 15:48
 */
@Component
public class SearchItemServiceHystrix implements SearchItemService {
    @Override
    public ScenResult importAllItems() {
        return ScenResult.build(233, "服务不可用");
    }

    @Override
    public SearchResult getPortalItems(String queryString, Integer page, Integer rows) throws Exception {
        return null;
    }
}
