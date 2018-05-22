package com.scen.vo;

import com.scen.pojo.Item;

import java.io.Serializable;

/**
 * 导出excle报表bean
 *
 * @author Scen
 * @date 2018/3/23 9:40
 */
public class ItemBean extends Item implements Serializable {
    private static final long serialVersionUID = 2474165141275081643L;
    private String catName;

    private String statusName;


    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
