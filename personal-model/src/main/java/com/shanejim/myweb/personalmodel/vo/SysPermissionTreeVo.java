package com.shanejim.myweb.personalmodel.vo;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-02 15:56
 **/
public class SysPermissionTreeVo {
    private Long id;

    private Long parentId;

    private String text;

    private SysPermissionState state;

    private String parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SysPermissionState getState() {
        return state;
    }

    public void setState(SysPermissionState state) {
        this.state = state;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}


