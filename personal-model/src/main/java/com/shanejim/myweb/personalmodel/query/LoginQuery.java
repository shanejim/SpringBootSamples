package com.shanejim.myweb.personalmodel.query;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-31 14:01
 **/
public class LoginQuery {
    private String userName;

    private String passWord;

    private Boolean rememberMe;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
