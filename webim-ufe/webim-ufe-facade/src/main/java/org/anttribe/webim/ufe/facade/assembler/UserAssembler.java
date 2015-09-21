/*
 * 文  件   名: UserAssembler.java
 * 版         本 : (Anttribe)webim-ufe-facade. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: Administrator
 * 修改时间: 2015年9月6日
 */
package org.anttribe.webim.ufe.facade.assembler;

import java.util.ArrayList;
import java.util.List;

import org.anttribe.webim.base.core.domain.User;
import org.anttribe.webim.ufe.facade.dto.SignupDTO;
import org.anttribe.webim.ufe.facade.dto.UserDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;

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
    
    /**
     * 
     * @param users
     * @return UserDTO
     */
    public static UserDTO toDTO(User userinfo)
    {
        if (null != userinfo)
        {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(userinfo.getUserId());
            userDTO.setUsername(userinfo.getUsername());
            userDTO.setEmail(userinfo.getEmail());
            userDTO.setAvatar(userinfo.getAvatar());
            userDTO.setNickname(userinfo.getNickname());
            userDTO.setSignature(userinfo.getSignature());
            userDTO.setHxUsername(userinfo.getHxUsername());
            userDTO.setHxPassword(userinfo.getHxPassword());
            return userDTO;
        }
        return null;
    }
    
    /**
     * @param users
     * @return List<UserDTO>
     */
    public static List<UserDTO> toDTO(List<User> users)
    {
        List<UserDTO> dtos = null;
        if (!CollectionUtils.isEmpty(users))
        {
            dtos = new ArrayList<UserDTO>();
            for (User user : users)
            {
                dtos.add(UserAssembler.toDTO(user));
            }
        }
        return dtos;
    }
}