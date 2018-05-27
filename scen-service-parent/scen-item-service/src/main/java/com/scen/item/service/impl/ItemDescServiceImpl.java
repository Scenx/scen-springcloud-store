package com.scen.item.service.impl;

import com.scen.dao.ItemDescDao;
import com.scen.item.service.ItemDescService;
import com.scen.pojo.ItemDesc;
import com.scen.vo.ScenResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品描述服务
 *
 * @author Scen
 * @date 2018/5/24 22:55
 */
@RestController
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private ItemDescDao itemDescDao;

    @Override
    public ItemDesc getItemDesc(Long itemId) {
        return itemDescDao.selectByPrimaryKey(itemId);
    }
}
