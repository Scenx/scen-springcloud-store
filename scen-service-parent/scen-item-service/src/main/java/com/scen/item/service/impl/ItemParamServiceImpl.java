package com.scen.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scen.dao.ItemParamDao;
import com.scen.item.service.ItemParamService;
import com.scen.pojo.ItemParam;
import com.scen.vo.EUDdataGridResult;
import com.scen.vo.ItemParamBean;
import com.scen.vo.ScenResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品规格参数规则服务
 *
 * @author Scen
 * @date 2018/5/24 23:35
 */
@RestController
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private ItemParamDao itemParamDao;

    @Override
    public ScenResult getItemParamByCid(Long cid) {
        Example example = new Example(ItemParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemCatId", cid);
        List<ItemParam> list = itemParamDao.selectByExample(example);
//        判断是否查询到结果
        if (list != null && list.size() > 0) {
            return ScenResult.ok(list.get(0));
        }
        return ScenResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult insertItemParam(@RequestBody ItemParam itemParam) throws Exception {
        //        补全数据
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
//        插入表
        itemParamDao.insert(itemParam);
        return ScenResult.ok();
    }

    @Override
    public EUDdataGridResult getItemParamList(Integer page, Integer rows, String catName) {
        Map<String, Object> map = new HashMap<String, Object>();
        //检索条件封装
        if (StringUtils.isNotBlank(catName)) {
            map.put("catName", catName);
        }
        PageHelper.startPage(page, rows);
        List<ItemParamBean> list = itemParamDao.getItemParamList(map);
        //创建一个返回值对象
        EUDdataGridResult result = new EUDdataGridResult();
        result.setRows(list);
//        取记录总条数
        PageInfo<ItemParamBean> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult updateItemParam(@RequestBody ItemParam itemParam) throws Exception {
        itemParamDao.updateByPrimaryKeySelective(itemParam);
        return ScenResult.ok();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScenResult deleteItemParam(@RequestBody Long[] ids) throws Exception {
        for (Long id : ids) {
            itemParamDao.deleteByPrimaryKey(id);
        }
        return ScenResult.ok();
    }
}
