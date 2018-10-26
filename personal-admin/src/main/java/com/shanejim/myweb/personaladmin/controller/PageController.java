package com.shanejim.myweb.personaladmin.controller;

import com.shanejim.myweb.personalmodel.entity.PayMall;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @description: TODO
 * @author: panshenjia
 * @create: 2018-10-26 15:42
 **/
public class PageController {

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/page/payMall/index")
    public ModelAndView weixinPayMallIndex() {

        ModelAndView mv = new ModelAndView("payMall/PayMallIndex");
        PayMall payMall = new PayMall();
        payMall.setMallkey("woshikey123");
        mv.getModel().put("user", payMall);
        mv.addObject("time", new Date());
        return mv;
    }
}
