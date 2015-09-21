/*
 * 文  件   名: UserService.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月7日
 */
package org.anttribe.webim.base.application;

import java.util.List;

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
     * @return User
     */
    User findByUserAccount(String userAccount);
    
    /**
     * 根据用户名查询用户信息
     * 
     * @param username
     * @return User
     */
    User findByUsername(String username);
    
    /**
     * 根据用户邮箱查询用户
     * 
     * @param email
     * @return User
     */
    User findByEmail(String email);
    
    /**
     * 保存用户信息
     * 
     * @param userInfo
     */
    void saveUserInfo(User userInfo);
    
    /**
     * 根据关键字列表用户信息
     * 
     * @param keywords 关键字
     * @param currentUser 当前用户
     * @return List<User>
     */
    List<User> listByKeywords(String keywords, String currentUser);
}