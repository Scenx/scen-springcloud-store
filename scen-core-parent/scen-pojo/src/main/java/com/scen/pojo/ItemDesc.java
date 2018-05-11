package com.scen.pojo;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_item_desc")
public class ItemDesc {

    @Id
    private Long itemId;
    private String itemDesc;
    private Date created;
    private Date updated;


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }


    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}
