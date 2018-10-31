package com.shanejim.myweb.personalmodel.query;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-31 11:05
 **/
public class AddOrUpdateEmployeeQuery {
    @NotBlank(message = "登录名不能为空")
    private String name;

    @NotBlank(message = "密码不能为空")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
