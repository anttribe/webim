/*
 * 文  件   名: HxMessageProcessorJob.java
 * 版         本 : (Anttribe)webim-base-application. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: Administrator
 * 修改时间: 2015年8月31日
 */
package org.anttribe.webim.base.jobs;

import org.anttribe.webim.base.application.MessageApplication;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Administrator
 * @version 2015年8月31日
 */
public class HxMessageDownloadJob
{
    /**
     * messageApplication
     */
    @Autowired
    private MessageApplication messageApplication;
    
    /**
     * 执行任务
     */
    public void execute()
    {
        messageApplication.downloadHxMessageFiles();
    }
}