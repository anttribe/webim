/*
 * 文  件   名: AutioFileConverter.java
 * 版         本 : (Anttribe)webim-base-infra. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: Administrator
 * 修改时间: 2015年8月31日
 */
package org.anttribe.webim.base.infra;

import java.io.File;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

/**
 * 音频格式文件转换器
 * 
 * @author zhaoyong
 * @version 2015年8月31日
 */
public class AudioFileConverter
{
    /**
     * 转换处理
     * 
     * @param srcFilepath 源文件路径
     * @param destFilepath 目标文件路径
     */
    public void convert(String srcFilepath, String destFilepath)
    {
        File srcFile = new File(srcFilepath);
        File destFile = new File(destFilepath);
        
        AudioAttributes audioAttrs = new AudioAttributes();
        Encoder encoder = new Encoder();
        audioAttrs.setCodec("libmp3lame");
        
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audioAttrs);
        try
        {
            encoder.encode(srcFile, destFile, attrs);
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (InputFormatException e)
        {
            e.printStackTrace();
        }
        catch (EncoderException e)
        {
            e.printStackTrace();
        }
    }
}