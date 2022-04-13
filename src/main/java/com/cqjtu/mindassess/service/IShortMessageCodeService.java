package com.cqjtu.mindassess.service;

/**
 * @author zhangning
 *
 * 短信验证码服务
 */
@Deprecated
public interface IShortMessageCodeService {


    /**
     * 请求一个短信验证码
     * @param phoneNumber - 电话号码
     * @param scenes - 使用场景
     */
    long requestSmCode(String phoneNumber,String scenes);


    /**
     * 确认短信验证码
     * @param phoneNumber - 电话号码
     * @param code - 验证码
     * @param scenes - 使用场景
     * @return - 验证成功返回true,验证失败返回false
     */
    boolean
    confirmSmCode(String phoneNumber,String code,String scenes);
}
