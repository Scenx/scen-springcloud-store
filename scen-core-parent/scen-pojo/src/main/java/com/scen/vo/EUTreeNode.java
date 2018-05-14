package com.scen.vo;

import java.io.Serializable;

/**
 * easyui树形控件节点格式
 *
 * @author Scen
 * @date 2018/3/10 12:26
 */
public class EUTreeNode implements Serializable {

    private static final long serialVersionUID = -8914276613994953114L;

    private Long id;
    private String text;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
