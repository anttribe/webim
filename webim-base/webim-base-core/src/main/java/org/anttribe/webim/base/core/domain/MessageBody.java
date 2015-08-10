/*
 * 文  件   名: MessageBody.java
 * 版         本 : (Anttribe).webim All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月3日
 */
package org.anttribe.webim.base.core.domain;

import org.anttribe.opengadget.core.domain.MybatisAbstractEntity;

/**
 * @author zhaoyong
 * @version 2015年8月3日
 */
public class MessageBody extends MybatisAbstractEntity
{
    /**
     * id
     */
    private String id;
    
    /**
     * 所属消息
     */
    private Message message;
    
    /**
     * 消息类型
     */
    private MessageType type = MessageType.Text;
    
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
    private String hxUrl;
    
    /**
     * 语音视频等时长(秒)
     */
    private long length;
    
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
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public Message getMessage()
    {
        return message;
    }
    
    public void setMessage(Message message)
    {
        this.message = message;
    }
    
    public MessageType getType()
    {
        return type;
    }
    
    public void setType(MessageType type)
    {
        this.type = type;
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
    
    public String getHxUrl()
    {
        return hxUrl;
    }
    
    public void setHxUrl(String hxUrl)
    {
        this.hxUrl = hxUrl;
    }
    
    public long getLength()
    {
        return length;
    }
    
    public void setLength(long length)
    {
        this.length = length;
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