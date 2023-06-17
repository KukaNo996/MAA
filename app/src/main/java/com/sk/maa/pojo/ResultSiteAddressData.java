package com.sk.maa.pojo;

import java.util.ArrayList;

public class ResultSiteAddressData {
    private int total;
    private ArrayList<SiteInfo> rows;
    private int code;
    private String msg;

    public ResultSiteAddressData() {
    }

    public ResultSiteAddressData(int total, ArrayList<SiteInfo> rows, int code, String msg) {
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

    public ArrayList<SiteInfo> getRows() {
        return rows;
    }

    public void setRows(ArrayList<SiteInfo> rows) {
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
        return "ResultSiteAddressData{" +
                "total=" + total +
                ", rows=" + rows +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
