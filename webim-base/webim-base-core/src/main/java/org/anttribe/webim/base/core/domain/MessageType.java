/*
 * 文  件   名: MessageType.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月3日
 */
package org.anttribe.webim.base.core.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author zhaoyong
 * @version 2015年8月3日
 */
public enum MessageType
{
    Text("txt")
    {
        @Override
        public ObjectNode populateMessageBody(MessageBody messageBody)
        {
            ObjectNode msgNode = factory.objectNode();
            msgNode.put("msg", messageBody.getMsg());
            msgNode.put("type", "txt");
            return msgNode;
        }
        
        @Override
        public MessageBody parseMessageBodyFromJsonNode(JsonNode bodyNode)
        {
            if (null != bodyNode)
            {
                MessageBody messageBody = new MessageBody();
                messageBody.setMessageType(this);
                
                JsonNode msgNode = bodyNode.get("msg");
                if (null != msgNode && !StringUtils.isEmpty(msgNode.asText()))
                {
                    messageBody.setMsg(msgNode.asText());
                }
                return messageBody;
            }
            return null;
        }
    },
    Image("img")
    {
        @Override
        public ObjectNode populateMessageBody(MessageBody messageBody)
        {
            return null;
        }
        
        @Override
        public MessageBody parseMessageBodyFromJsonNode(JsonNode bodyNode)
        {
            if (null != bodyNode)
            {
                MessageBody messageBody = new MessageBody();
                messageBody.setMessageType(this);
                
                Map<String, JsonNode> propJsonNodes = new HashMap<String, JsonNode>();
                propJsonNodes.put("hxFileUrl", bodyNode.get("url"));
                propJsonNodes.put("secret", bodyNode.get("secret"));
                propJsonNodes.put("filename", bodyNode.get("filename"));
                propJsonNodes.put("hxThumbUrl", bodyNode.get("thumb"));
                propJsonNodes.put("thumbSecret", bodyNode.get("thumb_secret"));
                
                for (Map.Entry<String, JsonNode> entry : propJsonNodes.entrySet())
                {
                    if (null != entry.getValue() && !StringUtils.isEmpty(entry.getValue().asText()))
                    {
                        try
                        {
                            BeanUtils.copyProperty(messageBody, entry.getKey(), entry.getValue().asText());
                        }
                        catch (Exception e)
                        {
                        }
                    }
                }
                return messageBody;
            }
            return null;
        }
    },
    Audio("audio")
    {
        @Override
        public ObjectNode populateMessageBody(MessageBody messageBody)
        {
            return null;
        }
        
        @Override
        public MessageBody parseMessageBodyFromJsonNode(JsonNode bodyNode)
        {
            if (null != bodyNode)
            {
                MessageBody messageBody = new MessageBody();
                messageBody.setMessageType(this);
                
                Map<String, JsonNode> propJsonNodes = new HashMap<String, JsonNode>();
                propJsonNodes.put("hxFileUrl", bodyNode.get("url"));
                propJsonNodes.put("secret", bodyNode.get("secret"));
                propJsonNodes.put("filename", bodyNode.get("filename"));
                propJsonNodes.put("duration", bodyNode.get("duration"));
                
                for (Map.Entry<String, JsonNode> entry : propJsonNodes.entrySet())
                {
                    if (null != entry.getValue() && !StringUtils.isEmpty(entry.getValue().asText()))
                    {
                        try
                        {
                            BeanUtils.copyProperty(messageBody, entry.getKey(), entry.getValue().asText());
                        }
                        catch (Exception e)
                        {
                        }
                    }
                }
                return messageBody;
            }
            return null;
        }
    },
    File("")
    {
        @Override
        public ObjectNode populateMessageBody(MessageBody messageBody)
        {
            return null;
        }
        
        @Override
        public MessageBody parseMessageBodyFromJsonNode(JsonNode bodyNode)
        {
            return null;
        }
    };
    
    /**
     * 环信的messageType
     */
    private String hxMessageType;
    
    /**
     * <构造器>
     * 
     * @param hxMessageType
     */
    private MessageType(String hxMessageType)
    {
        this.hxMessageType = hxMessageType;
    }
    
    public String getHxMessageType()
    {
        return hxMessageType;
    }
    
    /**
     * 根据环信的messageType获取对应的messageType
     * 
     * @return MessageType
     */
    public static MessageType valueOfHxMessageType(String hxMessageType)
    {
        if (!StringUtils.isEmpty(hxMessageType))
        {
            MessageType[] types = MessageType.values();
            for (MessageType messageType : types)
            {
                if (hxMessageType.equals(messageType.getHxMessageType()))
                {
                    return messageType;
                }
            }
        }
        return null;
    }
    
    private static final JsonNodeFactory factory = new JsonNodeFactory(false);
    
    /**
     * 构造消息体数据
     * 
     * @return Object
     */
    public abstract ObjectNode populateMessageBody(MessageBody messageBody);
    
    /**
     * 从jsonNode中解析出MessageBody对象
     * 
     * @param bodyNode
     * @return MessageBody
     */
    public abstract MessageBody parseMessageBodyFromJsonNode(JsonNode bodyNode);
    
    /**
     * 从jsonNode中解析出MessageBody集合对象
     * 
     * @param jsonNode
     * @return List<MessageBody>
     */
    public static List<MessageBody> parseMessageBodies(JsonNode jsonNode)
    {
        List<MessageBody> messageBodies = new ArrayList<MessageBody>();
        if (null != jsonNode)
        {
            if (jsonNode.isArray())
            {
                Iterator<JsonNode> iter = jsonNode.iterator();
                while (iter.hasNext())
                {
                    JsonNode bodyNode = iter.next();
                    MessageBody messageBody = parseMessageBody(bodyNode);
                    if (null != messageBody)
                    {
                        messageBodies.add(messageBody);
                    }
                }
            }
            else
            {
                MessageBody messageBody = parseMessageBody(jsonNode);
                if (null != messageBody)
                {
                    messageBodies.add(messageBody);
                }
            }
        }
        return messageBodies;
    }
    
    /**
     * 从jsonNode中解析出MessageBody集合对象
     * 
     * @param bodyNode
     * @return MessageBody
     */
    public static MessageBody parseMessageBody(JsonNode bodyNode)
    {
        if (null != bodyNode)
        {
            JsonNode messageTypeNode = bodyNode.get("type");
            if (null == messageTypeNode || StringUtils.isEmpty(messageTypeNode.asText()))
            {
                MessageType messageType = MessageType.valueOf(messageTypeNode.asText());
                if (null != messageType)
                {
                    MessageBody messageBody = messageType.parseMessageBodyFromJsonNode(bodyNode);
                    return messageBody;
                }
            }
        }
        return null;
    }
}