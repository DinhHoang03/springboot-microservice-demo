package com.example.user_service.resources;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
    private int code = 1000;
    private String message;
    private T result;

    public APIResponse() {

    }

    public APIResponse(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        APIResponse<?> that = (APIResponse<?>) o;
        return code == that.code && Objects.equals(message, that.message) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, result);
    }
}
