package com.scen.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 商品分类json数据模型
 *
 * @author Scen
 * @date 2018/4/2 10:00
 */
public class CatNode {
    @JsonProperty("n")
    private String name;
    @JsonProperty("u")
    private String url;
    @JsonProperty("i")
    private List<?> item;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
