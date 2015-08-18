/*
 * 文  件   名: MessageApplication.java
 * 版         本 : (Anttribe).webim-base-application All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月14日
 */
package org.anttribe.webim.base.application;

import java.util.List;
import java.util.Map;

import org.anttribe.webim.base.core.domain.Message;

/**
 * @author zhaoyong
 * @version 2015年8月14日
 */
public interface MessageApplication
{
    /**
     * 保存消息
     * 
     * @param messages Message
     */
    void saveMessage(Message... messages);
    
    /**
     * 查询消息记录数据
     * 
     * @param params 参数 {mfrom: "", mto: "", pageSize: "", mtimestamp: ""}
     * @return List<Message>
     */
    List<Message> listMessageList(Map<String, Object> params);
}