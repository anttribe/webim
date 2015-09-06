/*
 * 文  件   名: UserService.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月7日
 */
package org.anttribe.webim.base.application;

import org.anttribe.webim.base.core.domain.User;

/**
 * @author zhaoyong
 * @version 2015年8月7日
 */
public interface UserApplication
{
    /**
     * 根据用户帐号获取用户信息
     * 
     * @param userAccount
     * @return
     */
    User findByUserAccount(String userAccount);
    
    /**
     * 保存用户信息
     * 
     * @param userInfo
     */
    void saveUserInfo(User userInfo);
}