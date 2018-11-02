package com.shanejim.myweb.personalmodel.query;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-02 11:10
 **/
public class AddOrUpdateSysPermissionQuery {
    private String text;

    private String sref;

    private Integer sort;

    private Long parentId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSref() {
        return sref;
    }

    public void setSref(String sref) {
        this.sref = sref;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
