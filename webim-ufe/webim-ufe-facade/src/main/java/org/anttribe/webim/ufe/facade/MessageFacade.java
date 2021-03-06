/*
 * 文  件   名: MessageFacade.java
 * 版         本 : (Anttribe).webim-ufe-facade. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月13日
 */
package org.anttribe.webim.ufe.facade;

import java.util.List;

import org.anttribe.webim.base.core.domain.Message;
import org.anttribe.webim.ufe.facade.dto.ChatHistoryDTO;

/**
 * @author zhaoyong
 * @version 2015年8月13日
 */
public interface MessageFacade
{
    /**
     * 持久化消息
     * 
     * @param message Message
     * @return String 消息id
     */
    Message persistentMessage(Message message);
    
    /**
     * 查询消息记录数据
     * 
     * @param params 参数 {mfrom: "", mto: "", pageSize: "", mtimestamp: ""}
     * @return List<Message>
     */
    List<Message> queryMessageList(ChatHistoryDTO chatHistoryDTO);
}