/*
 * 文  件   名: UserFacade.java
 * 版         本: (Anttribe).webim. All rights reserved
 * 描         述: <描述>
 * 修   改  人: zhaoyong
 * 修改时 间: 2015年2月4日
 */
package org.anttribe.webim.base.core.common.errorno;

/**
 * 用户模块错误码定义
 * 
 * @author zhaoyong
 * @version 2015年2月4日
 */
public final class UserErrorNumber
{
    /**
     * 模块编号
     */
    public static final String MODULE_NO = "01";
    
    /**
     * 用户账户或密码为空
     */
    public static final String USERACCOUNT_OR_PASSWORD_NULL = "010001";
    
    /**
     * 用户信息不存在
     */
    public static final String USERINFO_NOT_EXIST = "010002";
    
    /**
     * 用户密码不正确
     */
    public static final String USER_PASSWORD_ERROR = "010003";
    
    /**
     * 更新用户信息失败
     */
    public static final String UPDATE_USER_ERROR = "010004";
}