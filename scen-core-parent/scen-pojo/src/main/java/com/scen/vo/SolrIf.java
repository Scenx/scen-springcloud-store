package com.scen.vo;

import java.io.Serializable;

/**
 * solr索引维护pojo
 *
 * @author Scen
 * @date 2018/4/9 13:01
 */
public class SolrIf implements Serializable {

    private static final long serialVersionUID = -7524585241043049411L;

    private Long id;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
