/*
 * 文  件   名: SigninController.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月7日
 */
package org.anttribe.webim.ufe.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.anttribe.webim.base.core.common.exception.UnifyException;
import org.anttribe.webim.base.core.domain.User;
import org.anttribe.webim.ufe.facade.UserFacade;
import org.anttribe.webim.ufe.facade.dto.SigninDTO;
import org.anttribe.webim.ufe.web.constants.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @version 2015年8月7日
 */
@Controller
public class SigninController
{
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SigninController.class);
    
    /**
     * userService
     */
    @Autowired
    private UserFacade userfacade;
    
    @RequestMapping("/signin")
    public String signin()
    {
        return "signin/signin";
    }
    
    @RequestMapping("/doSignin")
    public ModelAndView doSignin(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
        @ModelAttribute
        SigninDTO signinDTO)
    {
        ModelAndView mv = new ModelAndView();
        
        User user = (User)httpSession.getAttribute(Keys.USER_SESSION);
        if (null == user)
        {
            try
            {
                // 登录逻辑
                user = userfacade.signin(signinDTO);
            }
            catch (UnifyException e)
            {
                LOGGER.warn("While signining, getting error: {}", e.getErrorNo());
                mv.addObject("errorNo", e.getErrorNo());
                mv.setViewName("signin/signin");
                return mv;
            }
        }
        LOGGER.debug("User has signin successful, userinfo {}", user);
        
        // 将用户信息保存到session中
        httpSession.setAttribute(Keys.USER_SESSION, user);
        mv.addObject("user", user);
        mv.setViewName("forward:/im");
        return mv;
    }
}