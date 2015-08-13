/*
 * 文  件   名: Result.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月4日
 */
package org.anttribe.webim.base.core.common;

/**
 * @author zhaoyong
 * @version 2015年8月3日
 */
public class Result<T>
{
    /**
     * 结果状态码
     */
    private String resultCode;
    
    /**
     * 信息
     */
    private String msg;
    
    /**
     * 结果数据
     */
    private T data;
    
    public String getResultCode()
    {
        return resultCode;
    }
    
    public void setResultCode(String resultCode)
    {
        this.resultCode = resultCode;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public T getData()
    {
        return data;
    }
    
    public void setData(T data)
    {
        this.data = data;
    }
}