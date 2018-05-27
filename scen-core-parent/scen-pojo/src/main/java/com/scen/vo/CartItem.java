package com.scen.vo;

import java.io.Serializable;

/**
 * 购物车商品数据模型
 *
 * @author Scen
 * @date 2018/4/12 13:46
 */
public class CartItem implements Serializable {
    private static final long serialVersionUID = 4022197983633456565L;
    private Long id;
    private String title;
    private Integer num;
    private Long price;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
}
