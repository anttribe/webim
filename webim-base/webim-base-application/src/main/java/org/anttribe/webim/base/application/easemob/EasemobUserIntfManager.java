/*
 * 文  件   名: EasemobUserIntfManager.java
 * 版         本 : (Anttribe)webim-base-application. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: Administrator
 * 修改时间: 2015年9月6日
 */
package org.anttribe.webim.base.application.easemob;

import org.anttribe.webim.base.application.impl.ClientSecretCredential;
import org.anttribe.webim.base.constants.EndPoints;
import org.anttribe.webim.base.core.common.Global;
import org.anttribe.webim.base.infra.httpclient.HTTPClientUtils;
import org.anttribe.webim.base.infra.httpclient.vo.Credential;
import org.anttribe.webim.base.infra.httpclient.vo.HTTPMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @author zhaoyong
 * @version 2015年9月6日
 */
public class EasemobUserIntfManager
{
    private static Logger logger = LoggerFactory.getLogger(EasemobUserIntfManager.class);
    
    private static JsonNodeFactory factory = new JsonNodeFactory(false);
    
    /**
     * 证书
     */
    private static Credential credential = new ClientSecretCredential(Global.me().getString("hx.APP_CLIENT_ID"),
        Global.me().getString("hx.APP_CLIENT_SECRET"), Global.me().getString("hx.USER_ROLE_APPADMIN"));
    
    /**
     * 注册环信用户
     * 
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     */
    public static ObjectNode signupHxUser(String username, String password, String nickname)
    {
        // 构造请求数据
        ObjectNode userInfoNode = factory.objectNode();
        userInfoNode.put("username", username);
        userInfoNode.put("password", password);
        userInfoNode.put("nickname", nickname);
        
        ObjectNode signupResNode =
            HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL, credential, userInfoNode, HTTPMethod.GET);
        return signupResNode;
    }
}