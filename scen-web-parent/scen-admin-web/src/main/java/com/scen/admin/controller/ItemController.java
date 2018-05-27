package com.scen.admin.controller;

import com.scen.admin.service.ExcelService;
import com.scen.common.utils.ExcelUtil;
import com.scen.common.utils.JsonUtils;
import com.scen.item.service.ItemDescService;
import com.scen.item.service.ItemService;
import com.scen.pojo.Item;
import com.scen.pojo.ItemDesc;
import com.scen.pojo.ItemParamItem;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ItemBean;
import com.scen.vo.ScenResult;
import com.scen.vo.SolrIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商品管理表现层
 *
 * @author Scen
 * @date 2018/3/9 20:16
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private ExcelService excelService;

    /**
     * 查询所有商品
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    public EUDdataGridResult getItemList(Long id, String title, String catName, Long startPrice, Long endPrice, Integer page, Integer rows) {
        return itemService.getItemList(page, rows, id, title, catName, startPrice, endPrice);
    }

    /**
     * 生成商品报表
     *
     * @param id
     * @param title
     * @param catName
     * @param startPrice
     * @param endPrice
     */
    @RequestMapping("/outputExcel")
    public void outputExcel(HttpServletResponse response, Long id, String title, String catName, Long startPrice, Long endPrice) {
        try {
            List<ItemBean> list = excelService.getExcel(id, title, catName, startPrice, endPrice);
            LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
            fieldMap.put("id", "商品id");
            fieldMap.put("title", "商品标题");
            fieldMap.put("catName", "商品分类");
            fieldMap.put("sellPoint", "卖点");
            fieldMap.put("price", "价格");
            fieldMap.put("num", "库存");
            fieldMap.put("barcode", "条形码");
            fieldMap.put("statusName", "状态");
            fieldMap.put("created", "创建时间");
            fieldMap.put("updated", "更新时间");
            ExcelUtil.listToExcel(list, fieldMap, "商品报表", "商品报表", response);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().println("服务器内部错误");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 保存商品
     *
     * @param item
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ScenResult cteateItem(Item item, ItemDesc itemDesc, ItemParamItem itemParamItem) {
        Map<String, Object> itemMap = new HashMap<>();
        itemMap.put("item", item);
        itemMap.put("itemDesc", itemDesc);
        itemMap.put("itemParamItem", itemParamItem);
        try {
            return itemService.createItem(itemMap);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "保存失败");
        }
    }

    /**
     * 更新商品
     *
     * @param item
     * @param itemDesc
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ScenResult updateItem(Item item, ItemDesc itemDesc, Long itemParamId, String itemParams) {
        Map<String, Object> itemMap = new HashMap<>();
        itemMap.put("item", item);
        itemMap.put("itemDesc", itemDesc);
        try {
            return itemService.updateItem(itemMap, itemParamId, itemParams);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "编辑失败");
        }

    }

    /**
     * 重写日期绑定接受前台的日期字符串
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

    }


    /**
     * 根据商品id查询商品详细信息
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/desc/{itemId}")
    public ScenResult getItemDesc(@PathVariable Long itemId) {
        ItemDesc itemDesc = itemDescService.getItemDesc(itemId);
        return ScenResult.ok(itemDesc);
    }

    /**
     * 根据id批量删除商品
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public ScenResult deleteItem(String ids) {
        List<SolrIf> list = JsonUtils.jsonToList(ids, SolrIf.class);
        try {
            return itemService.deleteItem(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "删除失败");
        }
    }

    /**
     * 根据id批量下架商品
     *
     * @param ids
     * @return
     */
    @RequestMapping("/instock")
    public ScenResult instockItem(String ids) {
        List<SolrIf> list = JsonUtils.jsonToList(ids, SolrIf.class);
        try {
            return itemService.instockItem(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "下架失败");
        }
    }

    /**
     * 根据id批量上架商品
     *
     * @param ids
     * @return
     */
    @RequestMapping("/reshelf")
    public ScenResult reshelfItem(Long[] ids) {
        try {
            return itemService.reshelfItem(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "上架失败");
        }
    }
}
