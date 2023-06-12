package com.sk.maa.pojo;

public class ResultLoginData {
    private String msg;
    private int code;
    private String token;

    public ResultLoginData() {
    }

    public ResultLoginData(String msg, int code, String token) {
        this.msg = msg;
        this.code = code;
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResultLoginData{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", token='" + token + '\'' +
                '}';
    }
}
