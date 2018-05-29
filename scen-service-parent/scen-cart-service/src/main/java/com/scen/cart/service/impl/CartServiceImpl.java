package com.scen.cart.service.impl;

import com.scen.cart.service.CartService;
import com.scen.vo.CartItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 购物车服务
 *
 * @author Scen
 * @date 2018/5/27 17:33
 */
@RestController
public class CartServiceImpl implements CartService {
    @Override
    public CartItem getCartItem(
            Long itemId,
            @RequestBody List<CartItem> itemList
    ) {
        CartItem cartItem = null;
        //判断商品列表中是否存在此商品
        for (CartItem cItem : itemList) {
//            如果存在此商品
            if (cItem.getId().equals(itemId)) {
                cartItem = cItem;
                break;
            }
        }
        return cartItem;
    }

    @Override
    public List<CartItem> addCartItemNum(
            Long itemId,
            Integer num,
            @RequestBody List<CartItem> itemList
    ) {
        for (CartItem cItem : itemList) {
//            如果存在此商品
            if (cItem.getId().equals(itemId)) {
                //                增加商品数量
                cItem.setNum(cItem.getNum() + num);
                break;
            }
        }
        return itemList;
    }

    @Override
    public List<CartItem> deleteCartItem(
            Long itemId,
            @RequestBody List<CartItem> itemList
    ) {
        //        从列表中找到此商品
        for (CartItem cartItem : itemList) {
            if (cartItem.getId().equals(itemId)) {
                itemList.remove(cartItem);
                break;
            }
        }
        return itemList;
    }
}
