package com.cqjtu.mindassess.service.impl;

import com.cqjtu.mindassess.exception.SystemErrorException;
import com.cqjtu.mindassess.service.ICallPyScriptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Description:
 * @Author: amosdzhn
 * @CreateTime: 2022/5/8 15:50
 */
@Service
@Slf4j
public class CallPyScriptServiceImpl implements ICallPyScriptService {

    /**
     *  脚本解释器可执行文件路径
     */
    private static final String PYTHON_INTERPRETER = "python";

    /**
     * 执行的脚本路径
     */
    private static final String SCRIPT_PATH = "C:\\Users\\zhn\\Desktop\\ser\\predict.py";

    /**
     * 待解析的音频文件临时存放路径
     */
    private static final String TEMP_AUDIO_FILE_PATH = "F:\\awesome\\mind-assess\\temp\\audio.wav";

    @Async
    @Override
    public Future<String> callEmotionRecognition(InputStream is) {
        // 1.临时存储在一个文件中
        File file = new File(TEMP_AUDIO_FILE_PATH);
        OutputStream os = null;
        try {
            if(!file.exists()){
                log.info("创建临时文件:" + TEMP_AUDIO_FILE_PATH);
                file.createNewFile();
            }
            os = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len=is.read(buf)) != -1){
                os.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        String cmd = PYTHON_INTERPRETER + " " + SCRIPT_PATH ;

        StringBuilder stdout = new StringBuilder();
        StringBuilder stderr = new StringBuilder();
        try {
            log.info("开始执行音频情绪识别脚本");
            Process proc = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line= br.readLine()) != null){
                stdout.append(line + "\n");
            }

            BufferedReader br2 = new BufferedReader(new InputStreamReader(proc.getErrorStream(),StandardCharsets.UTF_8));
            while ((line=br2.readLine()) != null){
                stderr.append(line + "\n");
            }

            br.close();
            br2.close();
            proc.waitFor();
            if( stderr.length() != 0){
                System.out.println(stderr);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }finally {
            if(file.exists()){
                file.delete();
            }
        }

        log.info("音频情绪识别结束");
        return new AsyncResult<>(stdout.toString());
    }

    // 测试
    public static void main(String[] args) throws FileNotFoundException {
        ICallPyScriptService callPyScriptService = new CallPyScriptServiceImpl();

        String audioFilePath = "C:\\Users\\zhn\\Desktop\\lyWAV16000.wav";

        InputStream is = null;
        is = new FileInputStream(audioFilePath);

        String result = null;
        try {
            result = callPyScriptService.callEmotionRecognition(is).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("result: \n" + result);

    }
}
