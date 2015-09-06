/*
 * 文  件   名: UserAssembler.java
 * 版         本 : (Anttribe)webim-ufe-facade. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: Administrator
 * 修改时间: 2015年9月6日
 */
package org.anttribe.webim.ufe.facade.assembler;

import org.anttribe.webim.base.core.domain.User;
import org.anttribe.webim.ufe.facade.dto.SignupDTO;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author zhaoyong
 * @version 2015年9月6日
 */
public class UserAssembler
{
    /**
     * @param signupDTO
     * @return User
     */
    public static User toEntity(SignupDTO signupDTO)
    {
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        user.setEmail(signupDTO.getEmail());
        // 加密密码
        user.setPassword(DigestUtils.md5Hex(signupDTO.getPassword()));
        return user;
    }
}