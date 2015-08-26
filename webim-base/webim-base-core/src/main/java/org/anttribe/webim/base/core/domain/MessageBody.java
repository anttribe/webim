/*
 * 文  件   名: MessageBody.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月3日
 */
package org.anttribe.webim.base.core.domain;

import org.anttribe.opengadget.core.domain.MybatisAbstractEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author zhaoyong
 * @version 2015年8月3日
 */
public class MessageBody extends MybatisAbstractEntity
{
    /**
     * messageBodyId
     */
    private String messageBodyId;
    
    /**
     * 所属消息
     */
    @JsonIgnore
    private Message message;
    
    /**
     * 消息类型
     */
    private MessageType messageType = MessageType.Text;
    
    /**
     * 文本消息的消息内容
     */
    private String msg;
    
    /**
     * 媒体消息的文件名
     */
    private String filename;
    
    /**
     * 媒体消息的文件url
     */
    private String hxFileUrl;
    
    /**
     * 语音视频等时长(秒)
     */
    private long duration;
    
    /**
     * 文件长度
     */
    private long fileLength;
    
    /**
     * 图片语音secret值
     */
    private String secret;
    
    /**
     * 缩略图
     */
    private String hxThumbUrl;
    
    /**
     * 缩略图secret值
     */
    private String thumbSecret;
    
    /**
     * 文件实际保存路径
     */
    private String filepath;
    
    /**
     * 缩略图实际保存路径
     */
    private String thumbpath;
    
    /**
     * 地理位置信息
     */
    private String address;
    
    /**
     * 纬度
     */
    private String lat;
    
    /**
     * 经度
     */
    private String lng;

    public String getMessageBodyId()
    {
        return messageBodyId;
    }

    public void setMessageBodyId(String messageBodyId)
    {
        this.messageBodyId = messageBodyId;
    }

    public Message getMessage()
    {
        return message;
    }

    public void setMessage(Message message)
    {
        this.message = message;
    }

    public MessageType getMessageType()
    {
        return messageType;
    }

    public void setMessageType(MessageType messageType)
    {
        this.messageType = messageType;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getHxFileUrl()
    {
        return hxFileUrl;
    }

    public void setHxFileUrl(String hxFileUrl)
    {
        this.hxFileUrl = hxFileUrl;
    }

    public long getDuration()
    {
        return duration;
    }

    public void setDuration(long duration)
    {
        this.duration = duration;
    }

    public long getFileLength()
    {
        return fileLength;
    }

    public void setFileLength(long fileLength)
    {
        this.fileLength = fileLength;
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public String getHxThumbUrl()
    {
        return hxThumbUrl;
    }

    public void setHxThumbUrl(String hxThumbUrl)
    {
        this.hxThumbUrl = hxThumbUrl;
    }

    public String getThumbSecret()
    {
        return thumbSecret;
    }

    public void setThumbSecret(String thumbSecret)
    {
        this.thumbSecret = thumbSecret;
    }

    public String getFilepath()
    {
        return filepath;
    }

    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }

    public String getThumbpath()
    {
        return thumbpath;
    }

    public void setThumbpath(String thumbpath)
    {
        this.thumbpath = thumbpath;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getLat()
    {
        return lat;
    }

    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public String getLng()
    {
        return lng;
    }

    public void setLng(String lng)
    {
        this.lng = lng;
    }
}