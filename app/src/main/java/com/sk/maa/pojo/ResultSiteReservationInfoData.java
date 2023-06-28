package com.sk.maa.pojo;

import java.util.HashMap;
import java.util.Map;

public class ResultSiteReservationInfoData {
    private String msg;
    private int code;
    private Map<String, Integer> data = new HashMap<>();

    public ResultSiteReservationInfoData() {
    }

    public ResultSiteReservationInfoData(String msg, int code, Map<String, Integer> data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
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

    public Map<String, Integer> getData() {
        return data;
    }

    public void setData(Map<String, Integer> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultSiteReservationInfoData{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
