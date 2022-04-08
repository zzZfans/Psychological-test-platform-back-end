package com.cqjtu.mindassess.enums;

public enum AccessTokenName {
    ACCESS_TOKEN("X-Token")
    ;
    public String tokenName;

    AccessTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
