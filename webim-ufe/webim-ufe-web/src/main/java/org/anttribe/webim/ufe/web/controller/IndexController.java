/*
 * 文  件   名: IndexController.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月4日
 */
package org.anttribe.webim.ufe.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhaoyong
 * @version 2015年8月4日
 */
@Controller
public class IndexController
{
    @RequestMapping({"/", "/index"})
    public String index()
    {
        return "index";
    }
}