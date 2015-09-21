/*
 * 文  件   名: UserServiceImpl.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月7日
 */
package org.anttribe.webim.base.application.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.anttribe.component.lang.UUIDUtils;
import org.anttribe.webim.base.application.UserApplication;
import org.anttribe.webim.base.application.easemob.EasemobUserIntfManager;
import org.anttribe.webim.base.core.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author zhaoyong
 * @version 2015年8月7日
 */
@Service(value = "userApplication")
public class UserApplicationImpl implements UserApplication
{
    
    @Override
    public User findByUserAccount(String userAccount)
    {
        if (!StringUtils.isEmpty(userAccount))
        {
            return User.findByUserAccount(userAccount);
        }
        return null;
    }
    
    @Override
    public User findByUsername(String username)
    {
        if (!StringUtils.isEmpty(username))
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("username", username);
            List<User> users = User.findByEnsureCriteria(criteria);
            if (!CollectionUtils.isEmpty(users))
            {
                return users.get(0);
            }
        }
        return null;
    }
    
    @Override
    public User findByEmail(String email)
    {
        if (!StringUtils.isEmpty(email))
        {
            Map<String, Object> criteria = new HashMap<String, Object>();
            criteria.put("email", email);
            List<User> users = User.findByEnsureCriteria(criteria);
            if (!CollectionUtils.isEmpty(users))
            {
                return users.get(0);
            }
        }
        return null;
    }
    
    @Override
    public List<User> listByKeywords(String keywords, String currentUser)
    {
        Map<String, Object> criteria = new HashMap<String, Object>();
        criteria.put("username", keywords);
        criteria.put("currentUser", currentUser);
        criteria.put("available", 1);
        
        return User.find(User.class, criteria);
    }
    
    @Override
    public void saveUserInfo(User userInfo)
    {
        if (null != userInfo)
        {
            userInfo.setUserId(UUIDUtils.getRandomUUID());
            userInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userInfo.save();
            
            // 环信用户注册
            ObjectNode signupResNode =
                EasemobUserIntfManager.signupHxUser(userInfo.getUsername(),
                    userInfo.getPassword(),
                    userInfo.getNickname());
            
            // 更新用户信息
            userInfo.setHxUsername(userInfo.getUsername());
            userInfo.setHxPassword(userInfo.getPassword());
            userInfo.setAvailable(Boolean.TRUE);
            userInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            userInfo.update();
        }
    }
}