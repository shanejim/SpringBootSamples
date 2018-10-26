package com.shanejim.myweb.personalmodel.entity;

import java.util.Date;

public class PayMall {
    private Long id;

    private Byte isDeleted;

    private Date addTime;

    private Date modifiedTime;

    private String mallkey;

    private String wxMchAppid;

    private String wxMchid;

    private String wxMchkey;

    private String aliAppid;

    private String aliAppPrivateKey;

    private String aliPublicKey;

    private String privateKeyPkcs8;

    private String privateKeyPkcs1;

    private String hostUrl;

    private String aliAppPublicKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getMallkey() {
        return mallkey;
    }

    public void setMallkey(String mallkey) {
        this.mallkey = mallkey == null ? null : mallkey.trim();
    }

    public String getWxMchAppid() {
        return wxMchAppid;
    }

    public void setWxMchAppid(String wxMchAppid) {
        this.wxMchAppid = wxMchAppid == null ? null : wxMchAppid.trim();
    }

    public String getWxMchid() {
        return wxMchid;
    }

    public void setWxMchid(String wxMchid) {
        this.wxMchid = wxMchid == null ? null : wxMchid.trim();
    }

    public String getWxMchkey() {
        return wxMchkey;
    }

    public void setWxMchkey(String wxMchkey) {
        this.wxMchkey = wxMchkey == null ? null : wxMchkey.trim();
    }

    public String getAliAppid() {
        return aliAppid;
    }

    public void setAliAppid(String aliAppid) {
        this.aliAppid = aliAppid == null ? null : aliAppid.trim();
    }

    public String getAliAppPrivateKey() {
        return aliAppPrivateKey;
    }

    public void setAliAppPrivateKey(String aliAppPrivateKey) {
        this.aliAppPrivateKey = aliAppPrivateKey == null ? null : aliAppPrivateKey.trim();
    }

    public String getAliPublicKey() {
        return aliPublicKey;
    }

    public void setAliPublicKey(String aliPublicKey) {
        this.aliPublicKey = aliPublicKey == null ? null : aliPublicKey.trim();
    }

    public String getPrivateKeyPkcs8() {
        return privateKeyPkcs8;
    }

    public void setPrivateKeyPkcs8(String privateKeyPkcs8) {
        this.privateKeyPkcs8 = privateKeyPkcs8 == null ? null : privateKeyPkcs8.trim();
    }

    public String getPrivateKeyPkcs1() {
        return privateKeyPkcs1;
    }

    public void setPrivateKeyPkcs1(String privateKeyPkcs1) {
        this.privateKeyPkcs1 = privateKeyPkcs1 == null ? null : privateKeyPkcs1.trim();
    }

    public String getHostUrl() {
        return hostUrl;
    }

    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl == null ? null : hostUrl.trim();
    }

    public String getAliAppPublicKey() {
        return aliAppPublicKey;
    }

    public void setAliAppPublicKey(String aliAppPublicKey) {
        this.aliAppPublicKey = aliAppPublicKey == null ? null : aliAppPublicKey.trim();
    }
}