package com.sk.maa.pojo;

import java.util.ArrayList;

public class ResultNoticeData {
    private Integer total;
    private ArrayList<Notice> rows;
    private int code;
    private String msg;

    public ResultNoticeData() {
    }

    public ResultNoticeData(Integer total, ArrayList<Notice> rows, int code, String msg) {
        this.total = total;
        this.rows = rows;
        this.code = code;
        this.msg = msg;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ArrayList<Notice> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Notice> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultNoticeData{" +
                "total=" + total +
                ", rows=" + rows +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
