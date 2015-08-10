/*
 * 文  件   名: UserServiceImpl.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月7日
 */
package org.anttribe.webim.ufe.facade.impl;

import org.anttribe.webim.base.application.UserApplication;
import org.anttribe.webim.base.core.common.errorno.UserErrorNumber;
import org.anttribe.webim.base.core.common.exception.UnifyException;
import org.anttribe.webim.base.core.domain.User;
import org.anttribe.webim.ufe.facade.UserFacade;
import org.anttribe.webim.ufe.facade.dto.SigninDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyong
 * @version 2015年8月7日
 */
@Service(value = "userfacade")
public class UserFacadeImpl implements UserFacade
{
    @Autowired
    private UserApplication userApplication;
    
    @Override
    public User signin(SigninDTO signinDTO)
    {
        if (StringUtils.isEmpty(signinDTO.getUserAccount()) || StringUtils.isEmpty(signinDTO.getPassword()))
        {
            // 参数不正确
            throw new UnifyException(UserErrorNumber.USERACCOUNT_OR_PASSWORD_NULL);
        }
        
        // 根据用户账号获取用户
        User userInfo = userApplication.findByUserAccount(signinDTO.getUserAccount());
        if (null == userInfo)
        {
            // 用户不存在
            throw new UnifyException(UserErrorNumber.USERINFO_NOT_EXIST);
        }
        
        // 加密密码，与数据库比较 MD5加密
        String pwdCiphertext = DigestUtils.md5Hex(signinDTO.getPassword());
        if (!pwdCiphertext.equals(userInfo.getPassword()))
        {
            // 用户密码不正确
            throw new UnifyException(UserErrorNumber.USER_PASSWORD_ERROR);
        }
        return userInfo;
    }
}