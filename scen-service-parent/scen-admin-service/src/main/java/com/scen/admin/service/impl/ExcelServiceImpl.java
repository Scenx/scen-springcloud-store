package com.scen.admin.service.impl;

import com.scen.admin.service.ExcelService;
import com.scen.dao.ItemDao;
import com.scen.vo.ItemBean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import java.util.List;
import java.util.Map;


/**
 * 生成报表服务提供者
 *
 * @author Scen
 * @date 2018/5/11 22:02
 */
@RestController
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public List<ItemBean> getExcel(Long id, String title, String catName, Long startPrice, Long endPrice) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        //检索条件封装
        if (id != null) {
            map.put("id", id);
        }
        if (StringUtils.isNotBlank(title)) {
            map.put("title", title);
        }
        if (StringUtils.isNotBlank(catName)) {
            map.put("catName", catName);
        }
        if (startPrice != null && endPrice == null) {
            map.put("startPrice", startPrice);
        }
        if (endPrice != null && startPrice == null) {
            map.put("endPrice", endPrice);
        }
        if (startPrice != null && endPrice != null) {
            map.put("startPrice", startPrice);
            map.put("endPrice", endPrice);
        }
        List<ItemBean> list = itemDao.getSearchItemList(map);
        return list;
    }
}
