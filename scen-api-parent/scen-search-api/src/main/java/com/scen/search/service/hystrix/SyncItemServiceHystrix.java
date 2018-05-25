package com.scen.search.service.hystrix;

import com.scen.search.service.SyncItemService;
import org.springframework.stereotype.Component;

/**
 * 商品同步熔断器
 *
 * @author Scen
 * @date 2018/5/18 14:28
 */
@Component
public class SyncItemServiceHystrix implements SyncItemService {


    @Override
    public void syncAddItem(Long itemId) {

    }

    @Override
    public void syncDelItem(Long itemId) {

    }
}
