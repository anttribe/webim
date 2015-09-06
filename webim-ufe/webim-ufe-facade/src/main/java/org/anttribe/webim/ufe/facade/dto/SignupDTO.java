/*
 * 文  件   名: SignupDTO.java
 * 版         本 : (Anttribe).webim-ufe-facade. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年9月4日
 */
package org.anttribe.webim.ufe.facade.dto;

/**
 * 注册用户DTO
 * 
 * @author zhaoyong
 * @version 2015年9月4日
 */
public class SignupDTO
{
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 用户密码
     */
    private String password;
    
    /**
     * 确认密码
     */
    private String confirmPassword;
    
    /**
     * 用户邮箱
     */
    private String email;
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getConfirmPassword()
    {
        return confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
}