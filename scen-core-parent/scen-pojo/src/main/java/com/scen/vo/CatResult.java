package com.scen.vo;

import java.util.List;

/**
 * 商品类型返回json数据模型
 * 解决
 * @author Scen
 * @date 2018/4/2 10:13
 */
public class CatResult {
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
