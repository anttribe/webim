/*
 * 文  件   名: MessageApplicationImpl.java
 * 版         本 : (Anttribe).webim-base-application All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月14日
 */
package org.anttribe.webim.base.application.impl;

import java.util.List;

import org.anttribe.webim.base.application.MessageApplication;
import org.anttribe.webim.base.core.domain.Message;
import org.anttribe.webim.base.core.domain.MessageBody;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhaoyong
 * @version 2015年8月14日
 */
@Service("messageApplication")
public class MessageApplicationImpl implements MessageApplication
{
    
    @Override
    public void saveMessage(Message... messages)
    {
        if (!ArrayUtils.isEmpty(messages))
        {
            for (Message message : messages)
            {
                if (null == message)
                {
                    continue;
                }
                message.save();
                
                List<MessageBody> messageBodies = message.getMessageBodies();
                if (CollectionUtils.isEmpty(messageBodies))
                {
                    continue;
                }
                MessageBody.batchSave(MessageBody.class, messageBodies);
            }
        }
    }
    
}