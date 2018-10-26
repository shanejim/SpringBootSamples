package com.shanejim.myweb.personaldao.mapper;

import com.shanejim.myweb.personalmodel.entity.PayMall;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayMallMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayMall record);

    int insertSelective(PayMall record);

    PayMall selectByPrimaryKey(Long id);

    PayMall selectByMallKey(@Param("mallkey") String mallkey);

    int updateByPrimaryKeySelective(PayMall record);

    int updateByPrimaryKey(PayMall record);

    List<PayMall> selectAllPayMall();
}