package com.cqjtu.mindassess.config.aliyunivconf;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cqjtu.mindassess.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 阿里云智能语音服务客户端
 * @Author: amosdzhn
 * @CreateTime: 2022/5/7 15:22
 */
public class AliyunIVClient {
    // 地域ID，常量，固定值。
    private static final String REGIONID = "cn-shanghai";
    private static final String ENDPOINTNAME = "cn-shanghai";
    private static final String PRODUCT = "nls-filetrans";
    private static final String DOMAIN = "filetrans.cn-shanghai.aliyuncs.com";
    private static final String API_VERSION = "2018-08-17";
    private static final String POST_REQUEST_ACTION = "SubmitTask";
    private static final String GET_REQUEST_ACTION = "GetTaskResult";
    // 请求参数
    private static final String KEY_APP_KEY = "appkey";
    private static final String KEY_FILE_LINK = "file_link";
    private static final String KEY_VERSION = "version";
    private static final String KEY_ENABLE_WORDS = "enable_words";
    // 响应参数
    private static final String KEY_TASK = "Task";
    private static final String KEY_TASK_ID = "TaskId";
    private static final String KEY_STATUS_TEXT = "StatusText";
    private static final String KEY_RESULT = "Result";
    // 状态值
    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_RUNNING = "RUNNING";
    private static final String STATUS_QUEUEING = "QUEUEING";
    // 阿里云鉴权client
    private IAcsClient client;

    // 阿里云appKey
    private String appKey;

    public IAcsClient getClient() {
        return client;
    }

    public void setClient(IAcsClient client) {
        this.client = client;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public AliyunIVClient(String accessKeyId, String accessKeySecret) {
        // 设置endpoint
        try {
            DefaultProfile.addEndpoint(ENDPOINTNAME, REGIONID, PRODUCT, DOMAIN);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(REGIONID, accessKeyId, accessKeySecret);
        this.client = new DefaultAcsClient(profile);
    }

    /**
     * 提交音频文件识别的请求
     * @param fileLink 在线的音频文件链接
     * @return taskId，用于查询识别结果
     */
    public String submitFileTransRequest(String fileLink){
        CommonRequest req = new CommonRequest();
        req.setDomain(DOMAIN);
        req.setVersion(API_VERSION);
        req.setAction(POST_REQUEST_ACTION);
        req.setProduct(PRODUCT);

        JSONObject taskObject = new JSONObject();
        taskObject.put(KEY_APP_KEY,this.appKey);
        taskObject.put(KEY_FILE_LINK,fileLink);
        taskObject.put(KEY_VERSION,"4.0");
        taskObject.put(KEY_ENABLE_WORDS,false);
        String task = taskObject.toJSONString();

        req.setMethod(MethodType.POST);
        req.putBodyParameter(KEY_TASK,task);

        String taskId = null;
        try {
            CommonResponse resp = client.getCommonResponse(req);
            if(resp.getHttpStatus() == 200){
                JSONObject result = JSONObject.parseObject(resp.getData());
                String statusText = result.getString(KEY_STATUS_TEXT);
                if(STATUS_SUCCESS.equals(statusText)){
                    taskId =result.getString(KEY_TASK_ID);
                }
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return taskId;
    }

    /**
     * 获取音频文件识别结果
     * @param taskId 任务id
     * @return -
     */
    public List<IVResponse> getFileTranResult(String taskId) {
        CommonRequest getRequest = new CommonRequest();
        getRequest.setDomain(DOMAIN);
        getRequest.setVersion(API_VERSION);
        getRequest.setAction(GET_REQUEST_ACTION);
        getRequest.setProduct(PRODUCT);
        getRequest.putQueryParameter(KEY_TASK_ID, taskId);
        getRequest.setMethod(MethodType.GET);

        List<IVResponse> list = new ArrayList<>();
        // 轮询查询结果
        while (true) {
            try {
                CommonResponse getResponse = client.getCommonResponse(getRequest);
                System.err.println("识别查询结果：" + getResponse.getData());
                if (getResponse.getHttpStatus() != 200) {
                    throw new BusinessException("阿里云智能语音服务处理错误 HttpStatus:" + getResponse.getHttpStatus());
                }
                JSONObject rootObj = JSONObject.parseObject(getResponse.getData());
                String statusText = rootObj.getString(KEY_STATUS_TEXT);
                if (STATUS_RUNNING.equals(statusText) || STATUS_QUEUEING.equals(statusText)) {
                    // 继续轮询，注意设置轮询时间间隔。
                    Thread.sleep(5000);
                }
                else {
                    // 状态信息为成功，返回识别结果；状态信息为异常，返回空。
                    if (STATUS_SUCCESS.equals(statusText)) {
                        String result = rootObj.getJSONObject(KEY_RESULT).getString("Sentences");
                        // 状态信息为成功，但没有识别结果，则可能是由于文件里全是静音、噪音等导致识别为空。
                        if(result != null) {
                            list = JSONObject.parseArray(result, IVResponse.class);
                        }
                    }
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
