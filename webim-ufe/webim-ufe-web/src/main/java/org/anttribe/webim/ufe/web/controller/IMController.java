/*
 * 文  件   名: IMController.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月7日
 */
package org.anttribe.webim.ufe.web.controller;

import javax.servlet.http.HttpSession;

import org.anttribe.webim.base.core.common.Global;
import org.anttribe.webim.base.core.domain.User;
import org.anttribe.webim.ufe.web.constants.Keys;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @version 2015年8月7日
 */
@Controller
public class IMController
{
    @RequestMapping("/im")
    public ModelAndView im(HttpSession httpSession)
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("hxAppKey", Global.me().getString("hx.APPKEY"));
        mv.addObject("user", (User)httpSession.getAttribute(Keys.USER_SESSION));
        mv.setViewName("/im/im");
        
        return mv;
    }
}