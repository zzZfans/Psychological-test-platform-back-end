package com.cqjtu.mindassess.util;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

public class ShortMessageTest {
    public static void main(String[] args) {
        Credential cred =
                new Credential("AKIDiFVUDfqjLgkX3JI0RcW8TXKq52i8G8k5",
                        "5tRggQt62i8H9k47uQfeNp0uYv1pMddM");

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("sms.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);

        SmsClient client = new SmsClient(cred, "ap-guangzhou", clientProfile);

        SendSmsRequest req = new SendSmsRequest();
        req.setSmsSdkAppid("1400644446");
        req.setSign("张宁个人笔记网");
        req.setTemplateID("1355525");
        String[] templateParams = {"1234","10"};
        req.setTemplateParamSet(templateParams);

        String[] phoneNumberSet = {"+8613240260313"};
        req.setPhoneNumberSet(phoneNumberSet);

        try {
            SendSmsResponse res = client.SendSms(req);
            System.out.println(SendSmsResponse.toJsonString(res));
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }


    }
}
