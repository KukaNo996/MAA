package com.sk.maa.pojo;

import java.util.List;

public class ResultPGData {
    private int total;
    private List<PGItem> rows;
    private int code;
    private String msg;

    public ResultPGData() {
    }

    public ResultPGData(int total, List<PGItem> rows, int code, String msg) {
        this.total = total;
        this.rows = rows;
        this.code = code;
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PGItem> getRows() {
        return rows;
    }

    public void setRows(List<PGItem> rows) {
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
        return "ResultPGData{" +
                "total=" + total +
                ", rows=" + rows +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
