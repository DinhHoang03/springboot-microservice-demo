package com.example.user_service.exception.custom;

import com.example.user_service.exception.code.AppCode;

public class AppException extends RuntimeException {
    private AppCode appCode;

    public AppException(AppCode appCode) {
        super(appCode.getMessage());
        this.appCode = appCode;
    }

    public AppCode getAppCode() {
        return appCode;
    }

    public void setAppCode(AppCode appCode) {
        this.appCode = appCode;
    }
}
