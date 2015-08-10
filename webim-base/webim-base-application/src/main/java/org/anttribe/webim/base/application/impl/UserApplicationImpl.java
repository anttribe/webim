/*
 * 文  件   名: UserServiceImpl.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月7日
 */
package org.anttribe.webim.base.application.impl;

import org.anttribe.webim.base.application.UserApplication;
import org.anttribe.webim.base.core.domain.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

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
}