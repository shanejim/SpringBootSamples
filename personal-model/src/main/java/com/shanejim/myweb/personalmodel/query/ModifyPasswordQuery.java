package com.shanejim.myweb.personalmodel.query;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-11-01 11:52
 **/
public class ModifyPasswordQuery {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "密码不能为空")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
