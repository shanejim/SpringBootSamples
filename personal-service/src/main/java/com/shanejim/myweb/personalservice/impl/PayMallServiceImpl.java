package com.shanejim.myweb.personalservice.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shanejim.myweb.personaldao.mapper.PayMallMapper;
import com.shanejim.myweb.personalmodel.config.ApiException;
import com.shanejim.myweb.personalmodel.entity.PayMall;
import com.shanejim.myweb.personalmodel.enums.CodeEnums;
import com.shanejim.myweb.personalmodel.query.AddOrUpdatePayMallQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalservice.PayMallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 14:30
 **/
@Service
public class PayMallServiceImpl implements PayMallService {

    @Autowired
    private PayMallMapper payMallMapper;

    @Override
    public int insertPayMall(AddOrUpdatePayMallQuery dto) {
        PayMall sameMallKey = payMallMapper.selectByMallKey(dto.getMallkey());
        if (sameMallKey != null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "商户key不能重复");
        }
        PayMall payMall = new PayMall();

        payMall.setIsDeleted(new Byte("0"));
        payMall.setAddTime(new Date());
        payMall.setModifiedTime(new Date());

        payMall.setWxMchAppid(dto.getWxMchAppid());
        payMall.setWxMchid(dto.getWxMchid());
        payMall.setWxMchkey(dto.getWxMchkey());
        payMall.setAliAppid(dto.getAliAppid());
        payMall.setAliAppPrivateKey(dto.getAliAppPrivateKey());
        payMall.setAliPublicKey(dto.getAliPublicKey());
        payMall.setMallkey(dto.getMallkey());
        payMall.setHostUrl(dto.getHostUrl());

        return payMallMapper.insertSelective(payMall);
    }

    @Override
    public int updatePayMall(Long id, AddOrUpdatePayMallQuery dto) {
        PayMall payMallBefore = payMallMapper.selectByPrimaryKey(id);
        if (payMallBefore == null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "商户不存在或已被删除，请刷新后重试");
        }
        if (!payMallBefore.getMallkey().equals(dto.getMallkey())) {
            PayMall sameMallKey = payMallMapper.selectByMallKey(dto.getMallkey());
            if (sameMallKey != null) {
                throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "商户key不能重复");
            }
        }
        PayMall payMall = new PayMall();
        payMall.setId(id);
        payMall.setModifiedTime(new Date());

        payMall.setWxMchAppid(dto.getWxMchAppid());
        payMall.setWxMchid(dto.getWxMchid());
        payMall.setWxMchkey(dto.getWxMchkey());
        payMall.setAliAppid(dto.getAliAppid());
        payMall.setAliAppPrivateKey(dto.getAliAppPrivateKey());
        payMall.setAliPublicKey(dto.getAliPublicKey());
        payMall.setMallkey(dto.getMallkey());
        payMall.setHostUrl(dto.getHostUrl());

        return payMallMapper.updateByPrimaryKeySelective(payMall);
    }

    @Override
    public int deletePayMall(Long id) {
        PayMall payMallBefore = payMallMapper.selectByPrimaryKey(id);
        if (payMallBefore == null) {
            throw new ApiException(CodeEnums.COMMON_ERR.getCode(), "商户不存在或已被删除，请刷新后重试");
        }

        PayMall payMall = new PayMall();
        payMall.setId(id);
        payMall.setModifiedTime(new Date());
        payMall.setIsDeleted(new Byte("1"));
        return payMallMapper.updateByPrimaryKeySelective(payMall);
    }

    @Override
    public PayMall getPayMallById(Long id) {
        PayMall payMall = payMallMapper.selectByPrimaryKey(id);

        return payMall;
    }

    @Override
    public PagingReturn<PayMall> listPayMall(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }

        List<PayMall> payMallList = payMallMapper.selectAllPayMall();
        PageInfo<PayMall> pageInfo = new PageInfo<>(payMallList);

        PagingReturn<PayMall> model = new PagingReturn<>();
        model.setTotal(pageInfo.getTotal());
        model.setResults(payMallList);
        return model;
    }
}
