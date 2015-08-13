/*
 * 文  件   名: MessageFacade.java
 * 版         本 : (Anttribe).webim-ufe-facade. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月13日
 */
package org.anttribe.webim.ufe.facade;

import org.anttribe.webim.base.core.common.Result;
import org.anttribe.webim.base.core.domain.Message;

/**
 * @author zhaoyong
 * @version 2015年8月13日
 */
public interface MessageFacade
{
    /**
     * 持久化消息
     * 
     * @param message Message
     * @return Result<String>
     */
    Result<String> persistentMessage(Message message);
}