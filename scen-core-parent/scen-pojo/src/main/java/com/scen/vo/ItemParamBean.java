package com.scen.vo;

import com.scen.pojo.ItemParam;

import java.io.Serializable;

/**
 * @author Scen
 * @date 2018/3/27 14:48
 */
public class ItemParamBean extends ItemParam implements Serializable {
    private static final long serialVersionUID = -2782146530320667723L;
    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
