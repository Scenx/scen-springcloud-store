package com.scen.portal.controller;

import com.scen.cache.service.ItemCacheService;
import com.scen.cart.service.CartService;
import com.scen.common.utils.CookieUtils;
import com.scen.common.utils.JsonUtils;
import com.scen.item.service.ItemService;
import com.scen.pojo.Item;
import com.scen.vo.CartItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车表现层
 *
 * @author Scen
 * @date 2018/4/12 16:50
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ItemCacheService itemCacheService;

    @Autowired
    private ItemService itemService;


    @Value("${SCEN_CART_NAME}")
    private String SCEN_CART_NAME;


    /**
     * 添加购物车
     *
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response) {
        CartItem cartItem = null;
        List<CartItem> itemList = null;
        //        从cookie中取商品列表
        String cartJson = CookieUtils.getCookieValue(request, SCEN_CART_NAME, true);
        if (StringUtils.isBlank(cartJson)) {
            itemList = new ArrayList<>();
        } else {
            //        把json转换成商品列表
            itemList = JsonUtils.jsonToList(cartJson, CartItem.class);
            cartItem = cartService.getCartItem(itemId, itemList);
        }
        if (cartItem == null) {
            cartItem = new CartItem();
            //        取商品信息
//        根据商品id查询缓存中的商品基本信息
            Item item = JsonUtils.jsonToPojo(itemCacheService.getItemBaseInfo(itemId), Item.class);
            if (item == null) {
//                如果是空就去数据库中查并添加到缓存
                item = itemService.getItemById(itemId);
                itemCacheService.setItemBaseInfo(item);
            }
            cartItem.setId(item.getId());
            cartItem.setTitle(item.getTitle());
            cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
            cartItem.setNum(num);
            cartItem.setPrice(item.getPrice());
            itemList.add(cartItem);
        } else {
            itemList = cartService.addCartItemNum(itemId, num, itemList);
        }
        //        把购物车列表写入cookie
        CookieUtils.setCookie(request, response, SCEN_CART_NAME, JsonUtils.objectToJson(itemList), true);
        return "redirect:/cart/success.html";
    }

    @RequestMapping("/success")
    public String showSuccess() {
        return "cartSuccess";
    }


    /**
     * 查看购物车
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/cart")
    public String showItemCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> itemList = null;
        //        从cookie中取商品列表
        String cartJson = CookieUtils.getCookieValue(request, SCEN_CART_NAME, true);
        if (StringUtils.isBlank(cartJson)) {
            itemList = new ArrayList<>();
        } else {
            //        把json转换成商品列表
            itemList = JsonUtils.jsonToList(cartJson, CartItem.class);
        }
        model.addAttribute("cartList", itemList);
        return "cart";
    }


    /**
     * 删除购物车商品
     *
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        List<CartItem> itemList = null;
        //        从cookie中取商品列表
        String cartJson = CookieUtils.getCookieValue(request, SCEN_CART_NAME, true);
        if (!StringUtils.isBlank(cartJson)) {
            //        把json转换成商品列表
            itemList = JsonUtils.jsonToList(cartJson, CartItem.class);
            itemList = cartService.deleteCartItem(itemId, itemList);
        }
        //        把购物车列表重新写入cookie
        CookieUtils.setCookie(request, response, SCEN_CART_NAME, JsonUtils.objectToJson(itemList), true);
        return "redirect:/cart/cart.html";
    }

}