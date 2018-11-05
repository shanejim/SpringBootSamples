package com.shanejim.myweb.personalmodel.vo;

import com.shanejim.myweb.personalmodel.entity.SysPermission;

import java.util.List;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-05 16:48
 **/
public class SysPermissionSiderVo {
    private Long id;

    private Long parentId;

    private String text;

    private String parent;

    private String icon;

    private  String label;

    private List<SysPermission> submenu;

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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<SysPermission> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<SysPermission> submenu) {
        this.submenu = submenu;
    }
}
