/*
 * 文  件   名: WebUtils.java
 * 版         本 : (Anttribe)webim-base-infra. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: Administrator
 * 修改时间: 2015年9月1日
 */
package org.anttribe.webim.base.infra;

import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;

/**
 * @author zhaoyong
 * @version 2015年9月1日
 */
public class WebUtils
{
    /**
     * Web app root key parameter at the servlet context level (i.e. a context-param in {@code web.xml}):
     * "webAppRootKey".
     */
    public static final String WEB_APP_ROOT_KEY_PARAM = "webAppRootKey";
    
    /** Default web app root key: "webapp.root" */
    public static final String DEFAULT_WEB_APP_ROOT_KEY = "webapp.root";
    
    public static String web_app_root_key = DEFAULT_WEB_APP_ROOT_KEY;
    
    public static void setWebAppRootSystemProperty(ServletContext servletContext)
        throws IllegalStateException
    {
        String root = servletContext.getRealPath("/");
        if (root == null)
        {
            throw new IllegalStateException("Cannot set web app root system property when WAR file is not expanded");
        }
        String param = servletContext.getInitParameter(WEB_APP_ROOT_KEY_PARAM);
        web_app_root_key = (param != null ? param : DEFAULT_WEB_APP_ROOT_KEY);
        String oldValue = System.getProperty(web_app_root_key);
        if (oldValue != null && !StringUtils.equals(oldValue, root))
        {
            throw new IllegalStateException("Web app root system property already set to different value: '"
                + web_app_root_key + "' = [" + oldValue + "] instead of [" + root + "] - "
                + "Choose unique values for the 'webAppRootKey' context-param in your web.xml files!");
        }
        System.setProperty(web_app_root_key, root);
        servletContext.log("Set web app root system property: '" + web_app_root_key + "' = [" + root + "]");
    }
}