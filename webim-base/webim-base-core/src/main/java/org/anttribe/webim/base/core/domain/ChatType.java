/*
 * 文  件   名: ChatType.java
 * 版         本 : (Anttribe).webim. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月3日
 */
package org.anttribe.webim.base.core.domain;

/**
 * @author zhaoyong
 * @version 2015年8月3日
 */
public enum ChatType
{
    chat("chat", "users"), groupchat("groupchat", "chatgroups");
    
    private String hxTypeCode;
    
    private String hxTargetType;
    
    /**
     * @param hxTypeCode
     */
    private ChatType(String hxTypeCode, String hxTargetType)
    {
        this.hxTypeCode = hxTypeCode;
        this.hxTargetType = hxTargetType;
    }
    
    public String getHxTypeCode()
    {
        return hxTypeCode;
    }
    
    public String getHxTargetType()
    {
        return hxTargetType;
    }
}