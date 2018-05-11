package com.scen.vo;

import java.util.List;

/**
 * @author Scen
 * @date 2018/3/9 23:35
 */
public class EUDdataGridResult {
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
