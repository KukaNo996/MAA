package com.sk.maa.pojo;

import java.util.ArrayList;

public class ResultUserInfoData {
    private String msg;
    private int code;
    private ArrayList<String> permissions;
    private ArrayList<String> roles;
    private User user;

    public ResultUserInfoData() {
    }

    public ResultUserInfoData(String msg, int code, ArrayList<String> permissions, ArrayList<String> roles, User user) {
        this.msg = msg;
        this.code = code;
        this.permissions = permissions;
        this.roles = roles;
        this.user = user;
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

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<String> permissions) {
        this.permissions = permissions;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ResultUserInfoData{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", permissions=" + permissions +
                ", roles=" + roles +
                ", user=" + user +
                '}';
    }
}
