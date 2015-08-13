/*
 * 文  件   名: MessageFacadeImpl.java
 * 版         本 : (Anttribe).webim-ufe-facade. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月13日
 */
package org.anttribe.webim.ufe.facade.impl;

import java.util.List;

import org.anttribe.component.lang.UUIDUtils;
import org.anttribe.webim.base.core.common.Result;
import org.anttribe.webim.base.core.common.errorno.SystemErrorNumber;
import org.anttribe.webim.base.core.common.exception.UnifyException;
import org.anttribe.webim.base.core.domain.Message;
import org.anttribe.webim.base.core.domain.MessageBody;
import org.anttribe.webim.ufe.facade.MessageFacade;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaoyong
 * @version 2015年8月13日
 */
public class MessageFacadeImpl implements MessageFacade
{
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(MessageFacadeImpl.class);
    
    @Override
    public String persistentMessage(Message message)
    {
        Result<String> result = new Result<String>();
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
        
        return message.getMessageId();
    }
    
}