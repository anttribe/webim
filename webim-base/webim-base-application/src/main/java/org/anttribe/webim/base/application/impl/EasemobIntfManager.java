/*
 * 文  件   名: EasemobIntfManager.java
 * 版         本 : (Anttribe).webim-base-application All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月6日
 */
package org.anttribe.webim.base.application.impl;

import java.net.URL;
import java.util.List;

import org.anttribe.webim.base.constants.EndPoints;
import org.anttribe.webim.base.core.common.Global;
import org.anttribe.webim.base.core.domain.Message;
import org.anttribe.webim.base.core.domain.MessageBody;
import org.anttribe.webim.base.core.domain.MessageType;
import org.anttribe.webim.base.infra.httpclient.HTTPClientUtils;
import org.anttribe.webim.base.infra.httpclient.vo.Credential;
import org.anttribe.webim.base.infra.httpclient.vo.HTTPMethod;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author zhaoyong
 * @version 2015年8月6日
 */
public class EasemobIntfManager
{
    
    private static Logger logger = LoggerFactory.getLogger(EasemobIntfManager.class);
    
    private static JsonNodeFactory factory = new JsonNodeFactory(false);
    
    /**
     * 证书
     */
    private static Credential credential = new ClientSecretCredential(Global.me().getString("hx.APP_CLIENT_ID"),
        Global.me().getString("hx.APP_CLIENT_SECRET"), Global.me().getString("hx.USER_ROLE_APPADMIN"));
        
    /**
     * 发送消息
     * 
     * @param message 消息对象
     * @return ObjectNode
     */
    public static ObjectNode sendMessage(Message message)
    {
        List<MessageBody> messageBodies = message.getMessageBodies();
        MessageType type = messageBodies.get(0).getMessageType();
        
        // 构造消息
        ObjectNode messageNode = factory.objectNode();
        messageNode.put("target_type", message.getChatType().getHxTargetType());
        ArrayNode targetUsers = factory.arrayNode();
        String target = message.getMto();
        String[] users = target.split(",");
        for (String user : users)
        {
            if (!StringUtils.isEmpty(user))
            {
                targetUsers.add(user);
            }
        }
        messageNode.put("target", targetUsers);
        messageNode.put("from", message.getMfrom());
        messageNode.put("ext", message.getExtParams());
        // TODO: 针对图片、语音等媒体格式消息，做文件上传处理
        messageNode.put("msg", type.populateMessageBody(messageBodies.get(0)));
        
        ObjectNode resultNode =
            HTTPClientUtils.sendHTTPRequest(EndPoints.MESSAGES_URL, credential, messageNode, HTTPMethod.POST);
        return resultNode;
    }
    
    /**
     * 获取聊天记录
     * 
     * @param startMillis 开始好描述
     * @param limit 每次获取的数据条数
     * @param cursor 游标
     * @return ObjectNode
     */
    public static ObjectNode getChatMessages(Long startMillis, Integer limit, String cursor)
    {
        // 构建参数
        ObjectNode queryNode = factory.objectNode();
        queryNode.put("ql", "select * where timestamp>" + startMillis);
        if (null != limit && 0 != limit)
        {
            // 分页，每页数据条数
            queryNode.put("limit", limit);
        }
        if (!StringUtils.isEmpty(cursor))
        {
            // cursor不为空，表示还有下一页数据
            queryNode.put("cursor", cursor);
        }
        try
        {
            String rest = "";
            if (null != queryNode && queryNode.get("ql") != null && !StringUtils.isEmpty(queryNode.get("ql").asText()))
            {
                rest = "ql=" + java.net.URLEncoder.encode(queryNode.get("ql").asText(), "utf-8");
            }
            if (null != queryNode && queryNode.get("limit") != null
                && !StringUtils.isEmpty(queryNode.get("limit").asText()))
            {
                rest = rest + "&limit=" + queryNode.get("limit").asText();
            }
            if (null != queryNode && queryNode.get("cursor") != null
                && !StringUtils.isEmpty(queryNode.get("cursor").asText()))
            {
                rest = rest + "&cursor=" + queryNode.get("cursor").asText();
            }
            
            URL chatMessagesUrl = new URL(EndPoints.CHATMESSAGES_URL + "?" + rest);
            ObjectNode messagesObjectNode =
                HTTPClientUtils.sendHTTPRequest(chatMessagesUrl, credential, null, HTTPMethod.GET);
                
            return messagesObjectNode;
        }
        catch (Exception e)
        {
            logger.error("Getting chatmessages from Hx, get error, cause: %s", e);
        }
        return null;
    }
}