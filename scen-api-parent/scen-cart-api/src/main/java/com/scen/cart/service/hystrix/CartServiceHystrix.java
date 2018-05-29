package com.scen.cart.service.hystrix;

import com.scen.cart.service.CartService;
import com.scen.vo.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 购物车熔断器
 *
 * @author Scen
 * @date 2018/5/27 16:19
 */
@Component
public class CartServiceHystrix implements CartService {
    @Override
    public CartItem getCartItem(Long itemId, List<CartItem> itemList) {
        return null;
    }

    @Override
    public List<CartItem> addCartItemNum(Long itemId, Integer num, List<CartItem> itemList) {
        return null;
    }

    @Override
    public List<CartItem> deleteCartItem(Long itemId, List<CartItem> itemList) {
        return null;
    }
}
