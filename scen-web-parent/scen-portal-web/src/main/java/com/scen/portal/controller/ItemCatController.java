package com.scen.portal.controller;


import com.scen.cache.service.ItemCatCacheService;
import com.scen.common.utils.JsonUtils;
import com.scen.item.service.ItemCatService;
import com.scen.vo.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品分类列表
 *
 * @author Scen
 * @date 2018/4/2 10:49
 */
@Controller
@RequestMapping("/itemCat")
public class ItemCatController {

    @Autowired
    private ItemCatCacheService itemCatCacheService;

    @Autowired
    private ItemCatService itemCatService;


    /**
     * 返回商品分类json1
     *
     * @param callBack
     * @return
     */
    /*@RequestMapping(value = "/itemCat/list",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getItemList(String callBack) {
        CatResult catResult = itemCatService.getItemCatList();
        String json = JsonUtils.objectToJson(catResult);
//        拼装返回值
        String result = callBack + "(" + json + ");";
        return result;
    }*/

    /**
     * 返回商品分类json2
     *
     * @param callBack
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object getItemCatList(String callBack) {
        CatResult catResult = null;
        catResult = itemCatCacheService.getItemCatList();
        if (catResult == null) {
            catResult = new CatResult();
            catResult.setData(itemCatService.getPortalCatList(0L));
            String cacheString = JsonUtils.objectToJson(catResult);
            itemCatCacheService.setItemCatList(cacheString);
        }
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callBack);
        return mappingJacksonValue;
    }

}
