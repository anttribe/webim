/*
 * 文  件   名: MessageFacadeImpl.java
 * 版         本 : (Anttribe).webim-ufe-facade. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月13日
 */
package org.anttribe.webim.ufe.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.anttribe.component.lang.UUIDUtils;
import org.anttribe.webim.base.application.MessageApplication;
import org.anttribe.webim.base.core.common.Global;
import org.anttribe.webim.base.core.common.errorno.SystemErrorNumber;
import org.anttribe.webim.base.core.common.exception.UnifyException;
import org.anttribe.webim.base.core.domain.Message;
import org.anttribe.webim.base.core.domain.MessageBody;
import org.anttribe.webim.ufe.facade.MessageFacade;
import org.anttribe.webim.ufe.facade.dto.ChatHistoryDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyong
 * @version 2015年8月13日
 */
@Service("messageFacade")
public class MessageFacadeImpl implements MessageFacade
{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(MessageFacadeImpl.class);
    
    /**
     * messageApplication
     */
    @Autowired
    private MessageApplication messageApplication;
    
    @Override
    public String persistentMessage(Message message)
    {
        if (null == message)
        {
            logger.error("Sending message, param message is null.");
            // 参数为空
            throw new UnifyException(SystemErrorNumber.PARAMETER_IS_NULL);
        }
        
        if (StringUtils.isEmpty(message.getMfrom()) || StringUtils.isEmpty(message.getMto()))
        {
            logger.warn("Sending message, message from or to is null.");
            throw new UnifyException(SystemErrorNumber.PARAMETE_ILLEGAL);
        }
        
        List<MessageBody> messageBodies = message.getMessageBodies();
        if (CollectionUtils.isEmpty(messageBodies))
        {
            logger.warn("Sending message, message bodies is null.");
            throw new UnifyException(SystemErrorNumber.PARAMETE_ILLEGAL);
        }
        
        // 保存消息数据到DB
        message.setMessageId(UUIDUtils.getRandomUUID());
        message.setMtimestamp(System.currentTimeMillis());
        List<MessageBody> tempMessageBodies = message.getMessageBodies();
        for (MessageBody messageBody : tempMessageBodies)
        {
            messageBody.setId(UUIDUtils.getRandomUUID());
            messageBody.setMessage(message);
        }
        messageApplication.saveMessage(message);
        
        return message.getMessageId();
    }
    
    @Override
    public List<Message> queryMessageList(ChatHistoryDTO chatHistoryDTO)
    {
        List<Message> messageList = new ArrayList<Message>();
        if (StringUtils.isEmpty(chatHistoryDTO.getMfrom()) || StringUtils.isEmpty(chatHistoryDTO.getMto()))
        {
            logger.error("Querying messagew, param message is null.");
            // 参数为空
            throw new UnifyException(SystemErrorNumber.PARAMETER_IS_NULL);
        }
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mfrom", chatHistoryDTO.getMfrom());
        params.put("mto", chatHistoryDTO.getMto());
        int limit = chatHistoryDTO.getLimit();
        if (limit <= 0)
        {
            // 每次获取10条记录
            limit = Global.me().getInt("chat.history.limit", 10);
        }
        params.put("limit", limit);
        long mtimestamp = chatHistoryDTO.getMtimestamp();
        if (mtimestamp <= 0)
        {
            mtimestamp = new Date().getTime();
        }
        params.put("mtimestamp", mtimestamp);
        
        messageList = messageApplication.listMessageList(params);
        return messageList;
    }
    
}