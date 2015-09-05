/*
 * 文  件   名: SignupController.java
 * 版         本 : (Anttribe).webim-ufe-web. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年9月4日
 */
package org.anttribe.webim.ufe.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.anttribe.webim.base.core.common.exception.UnifyException;
import org.anttribe.webim.ufe.facade.UserFacade;
import org.anttribe.webim.ufe.facade.dto.SignupDTO;
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
 * @version 2015年9月4日
 */
@Controller
public class SignupController
{
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SignupController.class);
    
    /**
     * userService
     */
    @Autowired
    private UserFacade userfacade;
    
    @RequestMapping("/signup")
    public String signup()
    {
        return "signup/signup";
    }
    
    @RequestMapping("/doSignup")
    public ModelAndView doSignup(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
        @ModelAttribute
        SignupDTO signupDTO)
    {
        ModelAndView mv = new ModelAndView();
        
        // 去除session中的用户信息
        httpSession.setAttribute(Keys.USER_SESSION, null);
        try
        {
            // 注册逻辑
        }
        catch (UnifyException e)
        {
            LOGGER.warn("While signuping, getting error: {}", e.getErrorNo());
            mv.addObject("errorNo", e.getErrorNo());
            mv.setViewName("signup/signup");
            return mv;
        }
        LOGGER.debug("User has signup successful, username {}, user-email: {}",
            signupDTO.getUsername(),
            signupDTO.getEmail());
        
        mv.setViewName("redirect:signin/signin");
        return mv;
    }
}