/*
 * 文  件   名: UserFacade.java
 * 版         本 : (Anttribe).webim-ufe-facade. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月9日
 */
package org.anttribe.webim.ufe.facade;

import org.anttribe.webim.base.core.domain.User;
import org.anttribe.webim.ufe.facade.dto.SigninDTO;
import org.anttribe.webim.ufe.facade.dto.SignupDTO;

/**
 * @author zhaoyong
 * @version 2015年8月9日
 */
public interface UserFacade
{
    /**
     * 用户登录
     * 
     * @param signinDTO SigninDTO
     * @return User
     */
    User signin(SigninDTO signinDTO);
    
    /**
     * 用户注册
     * 
     * @param signupDTO
     */
    void signup(SignupDTO signupDTO);
}