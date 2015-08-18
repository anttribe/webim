/*
 * 文  件   名: Message.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月3日
 */
package org.anttribe.webim.base.core.domain;

import java.util.List;

import org.anttribe.opengadget.core.domain.MybatisAbstractEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author zhaoyong
 * @version 2015年8月3日
 */
public class Message extends MybatisAbstractEntity
{
    /**
     * 消息id
     */
    private String messageId;
    
    /**
     * 消息发送者id
     */
    private String mfrom;
    
    /**
     * 消息接收者id
     */
    private String mto;
    
    /**
     * 聊天类型
     */
    private ChatType chatType = ChatType.chat;
    
    /**
     * 消息发送时间
     */
    private long mtimestamp;
    
    /**
     * 消息体
     */
    private String bodies;
    
    /**
     * 消息体对象
     */
    private List<MessageBody> messageBodies;
    
    /**
     * 扩展参数
     */
    private String extParams;
    
    /**
     * 环信消息id
     */
    private String hxMsgId;
    
    /**
     * 最近一次同步的消息
     * 
     * @return Message
     */
    public static Message findHxLastUpdatedMessage()
    {
        String statement =
            (new StringBuilder()).append(Message.class.getCanonicalName()).append(".queryHxLastUpdated").toString();
        return getSqlSessionTemplate().selectOne(statement);
    }
    
    public String getMessageId()
    {
        return messageId;
    }
    
    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }
    
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
    
    public ChatType getChatType()
    {
        return chatType;
    }
    
    public void setChatType(ChatType chatType)
    {
        this.chatType = chatType;
    }
    
    public long getMtimestamp()
    {
        return mtimestamp;
    }
    
    public void setMtimestamp(long mtimestamp)
    {
        this.mtimestamp = mtimestamp;
    }
    
    public String getBodies()
    {
        return bodies;
    }
    
    public void setBodies(String bodies)
    {
        this.bodies = bodies;
    }
    
    public List<MessageBody> getMessageBodies()
    {
        return messageBodies;
    }
    
    public void setMessageBodies(List<MessageBody> messageBodies)
    {
        this.messageBodies = messageBodies;
    }
    
    public String getExtParams()
    {
        return extParams;
    }
    
    public void setExtParams(String extParams)
    {
        this.extParams = extParams;
    }
    
    public String getHxMsgId()
    {
        return hxMsgId;
    }
    
    public void setHxMsgId(String hxMsgId)
    {
        this.hxMsgId = hxMsgId;
    }
}