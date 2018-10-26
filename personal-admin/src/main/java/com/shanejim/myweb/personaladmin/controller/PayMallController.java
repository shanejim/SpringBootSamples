package com.shanejim.myweb.personaladmin.controller;

import com.shanejim.myweb.personalmodel.entity.PayMall;
import com.shanejim.myweb.personalmodel.query.AddOrUpdatePayMallQuery;
import com.shanejim.myweb.personalmodel.response.PagingReturn;
import com.shanejim.myweb.personalmodel.response.Result;
import com.shanejim.myweb.personalmodel.utils.ResultUtil;
import com.shanejim.myweb.personalservice.PayMallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 14:37
 **/
@Api(value = "payMalls", description = "支付商户相关的操作", tags = {"支付商户管理相关接口"})
@RestController
@RequestMapping("/payMalls")
public class PayMallController {

    @Autowired
    private PayMallService payMallService;

    @ApiOperation(value = "添加商户", notes = "添加商户接口，Post，传json")
    @PostMapping("/v1")
    public Result insertChatRoom(@RequestBody @Validated AddOrUpdatePayMallQuery dto) {
        payMallService.insertPayMall(dto);

        return ResultUtil.success();
    }

    @ApiOperation(value = "删除商户", notes = "根据id删除商户")
    @DeleteMapping("/v1/{id}")
    public Result deleteChatRoom(@PathVariable Long id) {
        payMallService.deletePayMall(id);

        return ResultUtil.success();
    }

    @ApiOperation(value = "更新商户信息", notes = "更新商户信息接口，改了哪个字段传哪个字段，不改的字段可以不传")
    @PutMapping("/v1/{id}")
    public Result updateChatRoom(@PathVariable Long id, @RequestBody @Validated AddOrUpdatePayMallQuery dto) {
        payMallService.updatePayMall(id, dto);

        return ResultUtil.success();
    }

    @ApiOperation(value = "获取商户详情", notes = "根据id获取商户信息")
    @GetMapping("/v1/{id}")
    public Result<PayMall> getChatRoom(@PathVariable Long id) {
        PayMall payMall = payMallService.getPayMallById(id);

        return ResultUtil.success(payMall);
    }

    @ApiOperation(value = "分页获取商户列表", notes = "分页获取所有商户")
    @GetMapping(value = "/v1")
    public Result<PagingReturn<PayMall>> getPagingList(@RequestParam(name = "pageNum", required = false) Integer pageNum,
                                                       @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        PagingReturn model = payMallService.listPayMall(pageNum, pageSize);
        return ResultUtil.success(model);
    }
}

