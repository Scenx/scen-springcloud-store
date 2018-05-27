package com.scen.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scen.pojo.Item;

import java.io.Serializable;

/**
 * 商品展示bean
 *
 * @author Scen
 * @date 2018/4/10 11:16
 */
public class ItemInfo extends Item implements Serializable {
    private static final long serialVersionUID = 2102324022807032142L;

    @JsonIgnore
    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
