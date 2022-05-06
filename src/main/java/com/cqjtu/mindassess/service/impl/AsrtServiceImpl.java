package com.cqjtu.mindassess.service.impl;

import com.cqjtu.mindassess.asrt.BaseSpeechRecognizer;
import com.cqjtu.mindassess.asrt.models.AsrtApiResponse;
import com.cqjtu.mindassess.asrt.models.Wave;
import com.cqjtu.mindassess.exception.BusinessException;
import com.cqjtu.mindassess.service.IAsrtService;
import com.sun.media.sound.WaveFileReader;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangning
 */
@Service
public class AsrtServiceImpl implements IAsrtService {

    @Resource
    BaseSpeechRecognizer asrt;

    /**
     * ASRT 支持的WAV音频文件最大采样率
     */
    private static final int ASRT_SERVER_MAX_SAMPLING_RATE = 16000;

    @Override
    public String recognizeSpeechToChinese(byte[] audio) {
        Wave wave1 = new Wave();
        wave1.deserialize(audio);
        byte[] sampleBytes = wave1.getRawSamples();
        int sampleRate = wave1.sampleRate;
        int channels = wave1.channels;
        int byteWidth = wave1.sampleWidth;
        if( sampleRate > 16000){
            throw new BusinessException("不支持 大于16000 的采样率");
        }
        AsrtApiResponse response = asrt.Recognite(sampleBytes, sampleRate, channels, byteWidth);
        return ((String) response.result);
    }

    /**
     * 线性转换采样率
     * @param src 源二进制
     * @param srcSampleRate 源文件采样率
     * @param destSampleRate 目标文件采样率
     * @return 目标二进制
     */
    @Deprecated // 该方法不生效
    private static byte[] sampleRateConvert(byte[] src,int srcSampleRate,int destSampleRate){
        int srcLen=src.length;
        int destLen=(int)(Math.ceil((destSampleRate*1.0/srcSampleRate)*srcLen));
        byte[] dest=new byte[destLen];
        double arc=srcSampleRate*1.0/destSampleRate;
        int lastPos=srcLen-1;
        for(int i=0;i<destLen;i++){
            double index=i*arc;
            int p1=(int)index;
            double coef=index-p1;
            int p2=  p1 < lastPos ? p1 + 1 : p1;
            dest[i] = (byte)((1-coef) * src[p1] + coef * src[p2]);
        }
        return dest;
    }
}
