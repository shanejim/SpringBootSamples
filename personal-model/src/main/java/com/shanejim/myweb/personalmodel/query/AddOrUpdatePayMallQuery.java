package com.shanejim.myweb.personalmodel.query;

import io.swagger.annotations.ApiModelProperty;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 14:24
 **/
public class AddOrUpdatePayMallQuery {
    @ApiModelProperty(value = "微信商户账号appid，申请商户号的appid或商户号绑定的appid", name = "mchAppid", required = true)
    private String wxMchAppid;

    @ApiModelProperty(value = "微信商户号", name = "mchid", required = true)
    private String wxMchid;

    @ApiModelProperty(value = "微信商户密钥", name = "mchkey", required = true)
    private String wxMchkey;

    @ApiModelProperty(value = "商户key（商户唯一标示）", name = "mallkey", required = true, example = "testmallkey")
    private String mallkey;

    @ApiModelProperty(value = "支付宝应用appid", name = "aliAppid", required = true)
    private String aliAppid;

    @ApiModelProperty(value = "支付宝应用秘钥", name = "aliAppPrivateKey", required = true)
    private String aliAppPrivateKey;

    @ApiModelProperty(value = "支付宝公钥", name = "aliPublicKey", required = true)
    private String aliPublicKey;

    @ApiModelProperty(value = "接口host", name = "hostUrl", required = true)
    private String hostUrl;

    public String getWxMchAppid() {
        return wxMchAppid;
    }

    public void setWxMchAppid(String wxMchAppid) {
        this.wxMchAppid = wxMchAppid;
    }

    public String getWxMchid() {
        return wxMchid;
    }

    public void setWxMchid(String wxMchid) {
        this.wxMchid = wxMchid;
    }

    public String getWxMchkey() {
        return wxMchkey;
    }

    public void setWxMchkey(String wxMchkey) {
        this.wxMchkey = wxMchkey;
    }

    public String getMallkey() {
        return mallkey;
    }

    public void setMallkey(String mallkey) {
        this.mallkey = mallkey;
    }

    public String getAliAppid() {
        return aliAppid;
    }

    public void setAliAppid(String aliAppid) {
        this.aliAppid = aliAppid;
    }

    public String getAliAppPrivateKey() {
        return aliAppPrivateKey;
    }

    public void setAliAppPrivateKey(String aliAppPrivateKey) {
        this.aliAppPrivateKey = aliAppPrivateKey;
    }

    public String getAliPublicKey() {
        return aliPublicKey;
    }

    public void setAliPublicKey(String aliPublicKey) {
        this.aliPublicKey = aliPublicKey;
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }
}
