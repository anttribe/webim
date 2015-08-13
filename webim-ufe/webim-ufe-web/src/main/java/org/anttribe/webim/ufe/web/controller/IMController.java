/*
 * 文  件   名: IMController.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月7日
 */
package org.anttribe.webim.ufe.web.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.anttribe.webim.base.core.common.Global;
import org.anttribe.webim.base.core.common.Result;
import org.anttribe.webim.base.core.common.errorno.MessageErrorNumber;
import org.anttribe.webim.base.core.domain.Message;
import org.anttribe.webim.base.core.domain.MessageBody;
import org.anttribe.webim.base.core.domain.MessageType;
import org.anttribe.webim.base.core.domain.User;
import org.anttribe.webim.ufe.web.constants.Keys;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhaoyong
 * @version 2015年8月7日
 */
@Controller
public class IMController
{
    private static Logger logger = LoggerFactory.getLogger(IMController.class);
    
    @RequestMapping("/im")
    public ModelAndView im(HttpSession httpSession)
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("hxAppKey", Global.me().getString("hx.APPKEY"));
        mv.addObject("user", (User)httpSession.getAttribute(Keys.USER_SESSION));
        mv.setViewName("/im/im");
        
        return mv;
    }
    
    /**
     * 持久化消息
     * 
     * @param message 聊天消息
     * @param file 聊天的文件
     * @param request
     * @param response
     * @return Result<?>
     */
    @RequestMapping("/im/persistent")
    @ResponseBody
    public Result<?> persistentMessage(Message message, @RequestParam(value = "file", required = false)
    MultipartFile file, HttpServletRequest request, HttpServletResponse response)
    {
        Result<String> result = new Result<String>();
        
        List<MessageBody> messageBodies = message.getMessageBodies();
        if (CollectionUtils.isEmpty(messageBodies))
        {
            result.setResultCode(MessageErrorNumber.MESSAGE_WITHOUT_BODIES_ERROR);
            return result;
        }
        MessageType type = messageBodies.get(0).getType();
        if ((MessageType.Image == type || MessageType.Audio == type) && null != file)
        {
            File targetFile = this.processFileUpload(request, file);
            for (MessageBody messageBody : messageBodies)
            {
                messageBody.setFilepath(targetFile.getPath());
                messageBody.setFilename(targetFile.getName());
                messageBody.setFileLength(targetFile.length());
            }
        }
        
        // result = easemobMessageService.persistentMessage(message);
        
        // 加入头信息
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        return result;
    }
    
    /**
     * 处理文件上传
     * 
     * @param request HttpServletRequest
     * @param file MultipartFile
     * @return File
     */
    private File processFileUpload(HttpServletRequest request, MultipartFile file)
    {
        // 如果携带文件，首先处理文件上传
        String fileUploadPath =
            request.getSession().getServletContext().getRealPath(Global.me().getString("upload.uploadPath"));
        String fileName = file.getOriginalFilename();
        File targetFile = new File(fileUploadPath, fileName);
        if (!targetFile.exists())
        {
            targetFile.mkdirs();
        }
        
        try
        {
            // 保存文件
            file.transferTo(targetFile);
        }
        catch (Exception e)
        {
            logger.error("Transfering upload file[{}] to target[{}] error, cause: {}",
                file.getOriginalFilename(),
                targetFile.getAbsolutePath(),
                e);
        }
        return targetFile;
    }
}