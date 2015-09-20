/*
 * 文  件   名: SearchDTO.java
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
public class SearchDTO
{
    /**
     * 当前用户
     */
    private String currentUser;
    
    /**
     * 搜索关键字
     */
    private String keys;
    
    /**
     * 搜索类型
     */
    private SearchType type;
    
    public String getCurrentUser()
    {
        return currentUser;
    }
    
    public void setCurrentUser(String currentUser)
    {
        this.currentUser = currentUser;
    }
    
    public String getKeys()
    {
        return keys;
    }
    
    public void setKeys(String keys)
    {
        this.keys = keys;
    }
    
    public SearchType getType()
    {
        return type;
    }
    
    public void setType(SearchType type)
    {
        this.type = type;
    }
}