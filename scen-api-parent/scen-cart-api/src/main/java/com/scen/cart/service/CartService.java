package com.scen.cart.service;

import com.scen.cart.service.hystrix.CartServiceHystrix;
import com.scen.vo.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 购物车接口
 *
 * @author Scen
 * @date 2018/5/27 16:17
 */
@FeignClient(value = "scen-cart-service", fallback = CartServiceHystrix.class)
public interface CartService {

    /**
     * 判断商品列表中是否存在此商品
     *
     * @param itemId
     * @param num
     * @param itemList
     * @return
     */
    @RequestMapping("cartService/getCartItem")
    CartItem getCartItem(
            @RequestParam("itemId") Long itemId,
            @RequestParam("num") Integer num,
            List<CartItem> itemList
    );

    /**
     * 删除购物车商品
     *
     * @param itemId
     * @param itemList
     * @return
     */
    List<CartItem> deleteCartItem(
            @RequestParam("itemId") Long itemId,
            List<CartItem> itemList);
}
