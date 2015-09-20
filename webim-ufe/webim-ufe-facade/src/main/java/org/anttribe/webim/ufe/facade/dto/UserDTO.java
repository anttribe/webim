/*
 * 文  件   名: UserDTO.java
 * 版         本 : (Anttribe).webim-ufe-facade. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年9月20日
 */
package org.anttribe.webim.ufe.facade.dto;

/**
 * @author zhaoyong
 * @version 2015年9月20日
 */
public class UserDTO
{
    /**
     * 用户编号: 唯一
     */
    private String userId;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 用户昵称
     */
    private String nickname;
    
    /**
     * 用户邮箱
     */
    private String email;
    
    /**
     * 联系电话
     */
    private String mobile;
    
    /**
     * 头像
     */
    private String avatar;
    
    /**
     * 个性签名
     */
    private String signature;
    
    /**
     * 环信用户名
     */
    private String hxUsername;
    
    /**
     * 环信的用户密码
     */
    private String hxPassword;
    
    public String getUserId()
    {
        return userId;
    }
    
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getNickname()
    {
        return nickname;
    }
    
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getMobile()
    {
        return mobile;
    }
    
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    
    public String getAvatar()
    {
        return avatar;
    }
    
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }
    
    public String getSignature()
    {
        return signature;
    }
    
    public void setSignature(String signature)
    {
        this.signature = signature;
    }
    
    public String getHxUsername()
    {
        return hxUsername;
    }
    
    public void setHxUsername(String hxUsername)
    {
        this.hxUsername = hxUsername;
    }
    
    public String getHxPassword()
    {
        return hxPassword;
    }
    
    public void setHxPassword(String hxPassword)
    {
        this.hxPassword = hxPassword;
    }
}