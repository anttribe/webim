/*
 * 文  件   名: FFMPEGLocator.java
 * 版         本 : (Anttribe).webim-base-infra. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月31日
 */
package org.anttribe.webim.base.infra.ffmpeg;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author zhaoyong
 * @version 2015年8月31日
 */
public class FFMPEGLocator extends it.sauronsoftware.jave.FFMPEGLocator
{
    
    private static final String DEFAULT_CONFIG_FILE = "ffmpeg.properties";
    
    /**
     * 配置属性
     */
    private static Configuration configuration;
    
    static
    {
        try
        {
            configuration = new PropertiesConfiguration(DEFAULT_CONFIG_FILE);
        }
        catch (ConfigurationException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * <默认构造器>
     */
    public FFMPEGLocator()
    {
    }
    
    @Override
    protected String getFFMPEGExecutablePath()
    {
        return configuration.getString("ffmpeg.home") + "/ffmpeg";
    }
    
}