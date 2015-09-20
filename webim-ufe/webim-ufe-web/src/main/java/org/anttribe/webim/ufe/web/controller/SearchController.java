/*
 * 文  件   名: SearchController.java
 * 版         本 : (Anttribe).webim-ufe-web. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年9月20日
 */
package org.anttribe.webim.ufe.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.anttribe.webim.base.core.common.Result;
import org.anttribe.webim.ufe.facade.UserFacade;
import org.anttribe.webim.ufe.facade.dto.SearchDTO;
import org.anttribe.webim.ufe.facade.dto.SearchType;
import org.anttribe.webim.ufe.facade.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @version 2015年9月20日
 */
@Controller
public class SearchController
{
    
    /**
     * userService
     */
    @Autowired
    private UserFacade userfacade;
    
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);
    
    @RequestMapping("/search")
    public ModelAndView search(String userid)
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("userid", userid);
        mv.setViewName("/im/search");
        return mv;
    }
    
    @RequestMapping("/search/doSearch")
    @ResponseBody
    public Result<?> doSearch(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
        @ModelAttribute SearchDTO searchDTO)
        throws IOException
    {
        Result<?> result = null;
        if (SearchType.roster == searchDTO.getType())
        {
            result = new Result<UserDTO>();
        }
        else if (SearchType.roster == searchDTO.getType())
        {
            
        }
        
        return result;
    }
}