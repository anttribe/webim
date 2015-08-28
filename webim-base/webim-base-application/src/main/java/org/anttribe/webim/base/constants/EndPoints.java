/*
 * 文  件   名: EndPoints.java
 * 版         本 : (Anttribe).webim-base-application. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月4日
 */
package org.anttribe.webim.base.constants;

import java.net.URL;

import org.anttribe.webim.base.core.common.Global;
import org.anttribe.webim.base.infra.httpclient.HTTPClientUtils;

/**
 * @author zhaoyong
 * @version 2015年8月3日
 */
public interface EndPoints
{
    String API_HTTP_SCHEMA = Global.me().getString("hx.API_HTTP_SCHEMA");
    
    String API_SERVER_HOST = Global.me().getString("hx.API_SERVER_HOST");
    
    String APPKEY = Global.me().getString("hx.APPKEY");
    
    /**
     * token url
     */
    URL TOKEN_APP_URL = HTTPClientUtils.getURL(API_HTTP_SCHEMA, API_SERVER_HOST, APPKEY.replace("#", "/") + "/token");
    
    /**
     * users url
     */
    URL USERS_URL = HTTPClientUtils.getURL(API_HTTP_SCHEMA, API_SERVER_HOST, APPKEY.replace("#", "/") + "/users");
    
    /**
     * messages url
     */
    URL MESSAGES_URL = HTTPClientUtils.getURL(API_HTTP_SCHEMA, API_SERVER_HOST, APPKEY.replace("#", "/") + "/messages");
    
    /**
     * 聊天记录 url
     */
    URL CHATMESSAGES_URL = HTTPClientUtils.getURL(API_HTTP_SCHEMA, API_SERVER_HOST, APPKEY.replace("#", "/") + "/chatmessages");
    
    /**
     * chatgroups url
     */
    URL CHATGROUPS_URL =
        HTTPClientUtils.getURL(API_HTTP_SCHEMA, API_SERVER_HOST, APPKEY.replace("#", "/") + "/chatgroups");
        
    /**
     * chatfiles url
     */
    URL CHATFILES_URL =
        HTTPClientUtils.getURL(API_HTTP_SCHEMA, API_SERVER_HOST, APPKEY.replace("#", "/") + "/chatfiles");
}