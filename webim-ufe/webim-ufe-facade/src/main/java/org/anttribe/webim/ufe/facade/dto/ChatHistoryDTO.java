/*
 * 文  件   名: ChatHistoryDTO.java
 * 版         本 : (Anttribe).webim-ufe-facade All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月18日
 */
package org.anttribe.webim.ufe.facade.dto;

/**
 * @author zhaoyong
 * @version 2015年8月18日
 */
public class ChatHistoryDTO
{
    private String mfrom;
    
    private String mto;
    
    private long mtimestamp;
    
    private int limit;
    
    public String getMfrom()
    {
        return mfrom;
    }
    
    public void setMfrom(String mfrom)
    {
        this.mfrom = mfrom;
    }
    
    public String getMto()
    {
        return mto;
    }
    
    public void setMto(String mto)
    {
        this.mto = mto;
    }
    
    public long getMtimestamp()
    {
        return mtimestamp;
    }
    
    public void setMtimestamp(long mtimestamp)
    {
        this.mtimestamp = mtimestamp;
    }
    
    public int getLimit()
    {
        return limit;
    }
    
    public void setLimit(int limit)
    {
        this.limit = limit;
    }
}