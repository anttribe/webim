/*
 * 文  件   名: HxMessageSyncJob.java
 * 版         本 : (Anttribe).webim-base-application All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月5日
 */
package org.anttribe.webim.base.jobs;

import java.util.Map;

import org.anttribe.webim.base.application.MessageApplication;
import org.anttribe.webim.base.constants.Constants;
import org.anttribe.webim.base.core.common.Global;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhaoyong
 * @version 2015年8月5日
 */
public class HxMessageSyncJob
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
        Integer limit = Global.me().getInt("hx.sync.limit", Constants.DEFAULT_HX_CHARMESSAGES_LIMIT);
        Map<String, Object> result = messageApplication.synchHxMessage(null, limit, null);
        Object cursor = result.get("cursor");
        Integer count = (Integer)result.get("count");
        limit = (Integer)result.get("limit");
        while (!MapUtils.isEmpty(result) && null != cursor && count >= limit)
        {
            try
            {
                // 保证1s之内 ，不会调2次 环信的rest接口
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            result = messageApplication.synchHxMessage((Long)result.get("startMillis"), limit, cursor.toString());
        }
    }
}