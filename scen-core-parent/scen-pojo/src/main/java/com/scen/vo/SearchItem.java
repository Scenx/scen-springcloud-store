package com.scen.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 商品检索模型
 *
 * @author Scen
 * @date 2018/5/25 15:21
 */
public class SearchItem implements Serializable {
    private static final long serialVersionUID = -7608171840072643608L;

    private String id;
    private String title;
    private String sellPoint;
    private Long price;
    private String image;
    private String categoryName;
    private String itemDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    @JsonIgnore
    public String[] getImages() {
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
