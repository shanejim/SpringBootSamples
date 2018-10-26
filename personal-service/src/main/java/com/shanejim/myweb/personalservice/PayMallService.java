package com.shanejim.myweb.personalservice;

import com.shanejim.myweb.personalmodel.entity.PayMall;
import com.shanejim.myweb.personalmodel.query.AddOrUpdatePayMallQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;

public interface PayMallService {
    int insertPayMall(AddOrUpdatePayMallQuery dto);

    int updatePayMall(Long id, AddOrUpdatePayMallQuery dto);

    int deletePayMall(Long id);

    PayMall getPayMallById(Long id);

    PagingReturn listPayMall(Integer pageNum, Integer pageSize);
}
