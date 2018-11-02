package com.shanejim.myweb.personalmodel.query;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-02 09:22
 **/
public class AddOrUpdateSysRoleQuery {
    @NotBlank(message = "名称不能为空")
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
