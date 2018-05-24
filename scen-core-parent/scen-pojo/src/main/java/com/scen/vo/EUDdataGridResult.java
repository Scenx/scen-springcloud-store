package com.scen.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 后台分页查询所有商品数据模型
 *
 * @author Scen
 * @date 2018/3/9 23:35
 */
public class EUDdataGridResult implements Serializable {

    private static final long serialVersionUID = -2824382913594869450L;

    private Long total;
    private List<?> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
