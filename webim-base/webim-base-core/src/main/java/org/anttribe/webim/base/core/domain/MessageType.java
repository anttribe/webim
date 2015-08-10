/*
 * 文  件   名: MessageType.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月3日
 */
package org.anttribe.webim.base.core.domain;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author zhaoyong
 * @version 2015年8月3日
 */
public enum MessageType
{
    Text()
    {
        @Override
        public ObjectNode populateMessageBody(MessageBody messageBody)
        {
            ObjectNode msgNode = factory.objectNode();
            msgNode.put("msg", messageBody.getMsg());
            msgNode.put("type", "txt");
            return msgNode;
        }
    },
    Image()
    {
        @Override
        public ObjectNode populateMessageBody(MessageBody messageBody)
        {
            return null;
        }
    },
    Audio()
    {
        @Override
        public ObjectNode populateMessageBody(MessageBody messageBody)
        {
            return null;
        }
    };
    
    private static final JsonNodeFactory factory = new JsonNodeFactory(false);
    
    /**
     * 构造消息体数据
     * 
     * @return Object
     */
    public abstract ObjectNode populateMessageBody(MessageBody messageBody);
}