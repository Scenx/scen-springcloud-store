package com.scen.admin.controller;

import com.scen.item.service.ItemParamService;
import com.scen.pojo.ItemParam;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品规格参数表现层
 *
 * @author Scen
 * @date 2018/3/22 10:50
 */
@RestController
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据id查询商品规格参数
     *
     * @param itemCatId
     * @return
     */
    @RequestMapping("/query/itemcatid/{itemCatId}")
    public ScenResult getItemParamByCid(@PathVariable Long itemCatId) {
        return itemParamService.getItemParamByCid(itemCatId);
    }

    /**
     * 保存商品规格
     *
     * @param cid
     * @param paramData
     * @return
     */
    @RequestMapping("/save/{cid}")
    public ScenResult insertItemParam(@PathVariable Long cid, String paramData) {
//        创建pojo对象
        ItemParam itemParam = new ItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        try {
            return itemParamService.insertItemParam(itemParam);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "保存失败");
        }
    }

    /**
     * 查询所有类目的规格模版
     *
     * @param catName
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    public EUDdataGridResult getItemParamList(String catName, Integer page, Integer rows) {
        return itemParamService.getItemParamList(page, rows, catName);
    }

    /**
     * 修改指定类目的规格
     *
     * @param itemParam
     * @return
     */
    @RequestMapping("/update")
    public ScenResult updateItemParam(ItemParam itemParam) {
        try {
            return itemParamService.updateItemParam(itemParam);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "编辑失败");
        }
    }

    /**
     * 根据id批量删除类目规格
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public ScenResult deleteItemParam(Long[] ids) {
        try {
            return itemParamService.deleteItemParam(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return ScenResult.build(500, "删除失败");
        }
    }
}
