/*
 * 文  件   名: MessageApplicationImpl.java
 * 版         本 : (Anttribe).webim-base-application All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月14日
 */
package org.anttribe.webim.base.application.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.anttribe.component.lang.UUIDUtils;
import org.anttribe.webim.base.application.MessageApplication;
import org.anttribe.webim.base.core.common.Global;
import org.anttribe.webim.base.core.domain.ChatType;
import org.anttribe.webim.base.core.domain.Message;
import org.anttribe.webim.base.core.domain.MessageBody;
import org.anttribe.webim.base.core.domain.MessageType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author zhaoyong
 * @version 2015年8月14日
 */
@Service("messageApplication")
public class MessageApplicationImpl implements MessageApplication
{
    
    /**
     * 用于格式化Message上的create_month
     */
    private static DateFormat createMonthFormat = new SimpleDateFormat("yyyyMM");
    
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
                message.setCreateMonth(createMonthFormat.format(new Date()));
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
    
    @Override
    public List<Message> listMessageList(Map<String, Object> params)
    {
        if (!MapUtils.isEmpty(params))
        {
            return Message.find(Message.class, params);
        }
        return null;
    }
    
    @Override
    public Map<String, Object> synchHxMessage(Long startMillis, Integer limit, String cursor)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        // 当startMillis为null时, 表示需要重新创建消息更新记录
        if (null == startMillis)
        {
            startMillis = System.currentTimeMillis() - Global.me().getLong("hx.sync.interval", 36000000L);
            // 获取最近一次同步的消息
            Message latestMessage = Message.findLatestSyncMessage();
            if (null != latestMessage)
            {
                startMillis = latestMessage.getMtimestamp();
            }
        }
        
        ObjectNode messagesObjectNode = EasemobIntfManager.getChatMessages(startMillis, limit, cursor);
        if (null != messagesObjectNode)
        {
            // 处理消息数据
            this.processHxMessages(messagesObjectNode.path("entities"));
            
            result.put("limit", limit);
            result.put("cursor", messagesObjectNode.get("cursor"));
            result.put("count", messagesObjectNode.get("count").asInt());
            result.put("startMillis", startMillis);
        }
        return result;
    }
    
    /**
     * 处理环信返回的消息数据
     * 
     * @param messages
     */
    private void processHxMessages(JsonNode messages)
    {
        if (null != messages && messages.isArray())
        {
            List<Message> updateMessageList = new ArrayList<Message>();
            List<Message> insertMessageList = new ArrayList<Message>();
            Iterator<JsonNode> messagesJsoNodes = messages.iterator();
            while (messagesJsoNodes.hasNext())
            {
                // "uuid": "673bbdfa-3beb-11e5-ae9b-d55704c15e40",
                // "type": "chatmessage",
                // "created": 1438831793999,
                // "modified": 1438831793999,
                // "timestamp": 1438831793263,
                // "from": "anttribe",
                // "msg_id": "91341040522363284_WEBIM_287bf001ba",
                // "to": "jsyc",
                // "chat_type": "chat",
                // "payload": {
                // "bodies": [
                // {
                // "type": "img",
                // "url":
                // "https://a1.easemob.com/jinglexinxi/yidongzizhen/chatfiles/62bac9b0-3beb-11e5-95d6-dbb553857d3c",
                // "secret": "YrrJujvrEeWCpImBGvZrIoHeT6sG4e-0_FcWeiO-JolWNGYU",
                // "filename": "QQ截图20150805142328.png",
                // "thumb":
                // "https://a1.easemob.com/jinglexinxi/yidongzizhen/chatfiles/62bac9b0-3beb-11e5-95d6-dbb553857d3c",
                // "thumb_secret": "",
                // "size": {
                // "width": 735,
                // "height": 309
                // }
                // }
                // ],
                // "ext": {
                // "messageId": "2f58579a93a942459fd14f48ef043cdb"
                // }
                // }
                
                JsonNode messageJsonNode = messagesJsoNodes.next();
                JsonNode payloadNode = messageJsonNode.path("payload");
                if (null == payloadNode)
                {
                    continue;
                }
                JsonNode extNode = payloadNode.path("ext");
                if (null == extNode)
                {
                    continue;
                }
                
                String messageId = "";
                JsonNode messageIdNode = extNode.get("messageId");
                if (null != messageIdNode && !StringUtils.isEmpty(messageIdNode.asText()))
                {
                    // 获取messageId
                    messageId = messageIdNode.asText();
                }
                Message message = new Message();
                message.setMessageId(messageId);
                message.setMfrom(messageJsonNode.get("from").asText());
                message.setMto(messageJsonNode.get("to").asText());
                message.setChatType(ChatType.valueOf(messageJsonNode.get("chat_type").asText()));
                message.setHxMsgId(messageJsonNode.get("msg_id").asText());
                message.setMtimestamp(messageJsonNode.get("timestamp").asLong());
                message.setBodies(payloadNode.get("bodies").toString());
                message.setExtParams(extNode.toString());
                // 处理消息体
                List<MessageBody> messageBodies = MessageType.parseMessageBodies(payloadNode.get("bodies"));
                if (!CollectionUtils.isEmpty(messageBodies))
                {
                    for (MessageBody messageBody : messageBodies)
                    {
                        messageBody.setMessage(message);
                    }
                    message.setMessageBodies(messageBodies);
                }
                
                if (StringUtils.isEmpty(message.getMessageId()))
                {
                    message.setMessageId(UUIDUtils.getRandomUUID());
                    for (MessageBody messageBody : message.getMessageBodies())
                    {
                        messageBody.setMessageBodyId(UUIDUtils.getRandomUUID());
                    }
                    insertMessageList.add(message);
                }
                else
                {
                    updateMessageList.add(message);
                }
            }
            
            if (!CollectionUtils.isEmpty(updateMessageList))
            {
                Message.batchUpdate(Message.class, updateMessageList);
            }
            if (!CollectionUtils.isEmpty(insertMessageList))
            {
                this.saveMessage(insertMessageList.toArray(new Message[insertMessageList.size()]));
            }
        }
    }
}