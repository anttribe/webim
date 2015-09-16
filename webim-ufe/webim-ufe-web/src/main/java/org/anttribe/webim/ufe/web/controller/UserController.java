/*
 * 文  件   名: UserController.java
 * 版         本 : (Anttribe).webim-ufe-web. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年9月16日
 */
package org.anttribe.webim.ufe.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.anttribe.webim.base.core.domain.User;
import org.anttribe.webim.ufe.web.constants.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @version 2015年9月16日
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @RequestMapping("/signout")
    public ModelAndView doSignout(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession)
    {
        ModelAndView mv = new ModelAndView();
        
        // 清空session中用户信息
        User user = (User)httpSession.getAttribute(Keys.USER_SESSION);
        httpSession.removeAttribute(Keys.USER_SESSION);
        
        LOGGER.debug("User has signout successful, userinfo {}", user);
        
        mv.setViewName("redirect:/signin");
        return mv;
    }
}