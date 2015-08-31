/*
 * 文  件   名: Amr2Mp3Converter.java
 * 版         本 : (Anttribe).webim-base-infra. All rights reserved
 * 描         述 : <描述>
 * 修   改  人: zhaoyong
 * 修改时间: 2015年8月31日
 */
package org.anttribe.webim.base.infra.ffmpeg;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import java.io.File;

import org.apache.commons.lang.StringUtils;

/**
 * @author zhaoyong
 * @version 2015年8月31日
 */
public class Amr2Mp3Converter
{
    /**
     * 转换处理
     * 
     * @param srcFilepath 源文件路径
     * @param destFilepath 目标文件路径
     */
    public void convert(String srcFilepath, String destFilepath)
    {
        if (!StringUtils.isEmpty(srcFilepath) && !StringUtils.isEmpty(destFilepath))
        {
            File srcFile = new File(srcFilepath);
            File destFile = new File(destFilepath);
            
            AudioAttributes audioAttrs = new AudioAttributes();
            audioAttrs.setCodec("libmp3lame");
            // audioAttrs.setBitRate(new Integer(64000));
            // audioAttrs.setChannels(new Integer(1));
            // audioAttrs.setSamplingRate(new Integer(22050));
            
            Encoder encoder = new Encoder(new FFMPEGLocator());
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
}