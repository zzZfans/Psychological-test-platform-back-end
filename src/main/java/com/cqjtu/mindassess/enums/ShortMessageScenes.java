package com.cqjtu.mindassess.enums;


/**
 * @author zhangning
 *
 *      短信使用场景枚举类
 */
public enum ShortMessageScenes {

    /**
     * 注册场景
     */
    SM_REGISTER("register"),

    /**
     * 登录场景
     */
    SM_LOGIN("login");

    public String scenes;

    ShortMessageScenes(String scenes) {
        this.scenes = scenes;
    }
}
