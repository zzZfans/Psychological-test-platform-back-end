package com.cqjtu.mindassess.enums;


/**
 * @author zhangning
 *
 *      短信使用场景枚举类
 */
public enum ShortMessageScenes {
    SM_REGISTER("register"),
    SM_LOGIN("login");

    public String scenes;

    ShortMessageScenes(String scenes) {
        this.scenes = scenes;
    }
}
