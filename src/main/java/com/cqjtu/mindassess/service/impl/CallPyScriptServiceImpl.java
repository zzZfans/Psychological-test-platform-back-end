package com.cqjtu.mindassess.service.impl;

import com.alibaba.fastjson.JSON;
import com.cqjtu.mindassess.pojo.dto.TextEmotionResult;
import com.cqjtu.mindassess.service.ICallPyScriptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
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
     * 待解析的音频文件临时存放路径
     */
    private static final String TEMP_FILE_DIR = "F:\\awesome\\mind-assess\\temp\\";

    /**
     * 音频分析脚本解释器路径
     */
    private static final String AUDIO_PYTHON_VENV_INTERPRETER = "C:\\Users\\zhn\\Desktop\\ser\\venv\\Scripts\\python.exe";

    /**
     *  音频分析脚本文件路径
     */
    private static final String AUDIO_EMOTION_SCRIPT_PATH = "C:\\Users\\zhn\\Desktop\\ser\\predict.py";

    /**
     * 文本分析脚本解释器路径
     */
    private static final String TEXT_PYTHON_VENV_INTERPRETER = "F:\\awesome\\mind-assess\\scripts\\simple-text_emotion\\venv\\Scripts\\python.exe";

    /**
     * 文本分析脚本文件路径
     */
    private static final String TEXT_EMOTION_SCRIPT_PATH = "F:\\awesome\\mind-assess\\scripts\\simple-text_emotion\\analyze.py";


    @Async
    @Override
    public Future<String> callEmotionRecognition(InputStream is) {
        // 1.临时存储在一个文件中
        String tempFilePath = TEMP_FILE_DIR + UUID.randomUUID().toString() + ".wav";

        // 2.将输入流保存在文件中
        File file = new File(tempFilePath);
        OutputStream os = null;
        try {
            if (!file.exists()) {
                log.info("创建临时文件:" + tempFilePath);
                file.createNewFile();
            }
            os = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                os.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 3.执行脚本
        String cmd = AUDIO_PYTHON_VENV_INTERPRETER + " " + AUDIO_EMOTION_SCRIPT_PATH + " --audio " + tempFilePath;

        StringBuilder stdout = new StringBuilder();
        StringBuilder stderr = new StringBuilder();
        try {
            log.info("开始执行音频情绪识别脚本");
            Process proc = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = br.readLine()) != null) {
                stdout.append(line + "\n");
            }

            BufferedReader br2 = new BufferedReader(new InputStreamReader(proc.getErrorStream(), StandardCharsets.UTF_8));
            while ((line = br2.readLine()) != null) {
                stderr.append(line + "\n");
            }

            br.close();
            br2.close();
            proc.waitFor();
            if (stderr.length() != 0) {
                System.out.println(stderr);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (file.exists()) {
                file.delete();
                log.info("删除临时文件:" + tempFilePath);
            }
        }

        log.info("音频情绪识别结束");
        return new AsyncResult<>(stdout.toString());
    }

    @Override
    public String callTextEmotionRecognition(InputStream is) {
        String tempTextFilePath = TEMP_FILE_DIR + UUID.randomUUID().toString();

        File file = new File(tempTextFilePath);
        OutputStream os = null;
        try {
            if (!file.exists()) {
                log.info("创建临时文件:" + tempTextFilePath);
                file.createNewFile();
            }
            os = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = is.read(buf)) != -1) {
                os.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // call python script
        String script = TEXT_PYTHON_VENV_INTERPRETER + " " + TEXT_EMOTION_SCRIPT_PATH + " " +tempTextFilePath;
        StringBuilder stdout = new StringBuilder();
        StringBuilder stderr = new StringBuilder();
        try {
            Process proc = Runtime.getRuntime().exec(script);
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream(), StandardCharsets.UTF_8));
            String line = null;
            while ((line = br.readLine()) != null) {
                stdout.append(line);
            }

            BufferedReader br2 = new BufferedReader(new InputStreamReader(proc.getErrorStream(), StandardCharsets.UTF_8));
            while ((line = br2.readLine()) != null) {
                stderr.append(line + "\n");
            }

            br.close();
            br2.close();
            proc.waitFor();
            if (stderr.length() != 0) {
                System.out.println(stderr);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(file.exists()){
                file.delete();
            }
        }
        // 解析结果
        TextEmotionResult textEmotionResult = JSON.parseObject(stdout.toString(), TextEmotionResult.class);
        long[] count = new long[7];
        count[0] = textEmotionResult.getGood();
        count[1] = textEmotionResult.getHappy();
        count[2] = textEmotionResult.getDecline();
        count[3] = textEmotionResult.getAngry();
        count[4] = textEmotionResult.getFear();
        count[5] = textEmotionResult.getEvil();
        count[6] = textEmotionResult.getSurprise();
        int index = -1;
        long max = 0;
        for (int i = 0; i < count.length; i++) {
            if( count[i] > max){
                max = count[i];
                index = i;
            }
        }
        String emotion = "";
        switch (index){
            case -1:
                emotion = "neutral";
                break;
            case 0:
                emotion = "good";
                break;
            case 1:
                emotion = "happy";
                break;
            case 2:
                emotion = "decline";
                break;
            case 3:
                emotion = "angry";
                break;
            case 4:
                emotion = "fear";
                break;
            case 5:
                emotion = "evil";
                break;
            case 6:
                emotion = "surprise";
                break;
            default:
                emotion = "";
        }

        return emotion;
    }


    // 测试
    public static void main(String[] args) throws FileNotFoundException {
        ICallPyScriptService callPyScriptService = new CallPyScriptServiceImpl();

        // ================== 测试文本情绪======================
        String textFilePath = "C:\\Users\\zhn\\Desktop\\demo.txt";
        InputStream is = new FileInputStream(textFilePath);
        String rest = callPyScriptService.callTextEmotionRecognition(is);
        System.out.println(rest);
        try {
            if(is != null){
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ===================================================


        // ================== 测试音频===========================
        // 1.测试音频文件路径
        String audioFilePath = "C:\\Users\\zhn\\Desktop\\lyWAV16000.wav";

        // 2.读取文件为输入流
        InputStream is2 = null;
        is2 = new FileInputStream(audioFilePath);

        String result = null;
        try {
            result = callPyScriptService.callEmotionRecognition(is2).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            try {
                is2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("result: \n" + result);
        // =================================================
    }
}
