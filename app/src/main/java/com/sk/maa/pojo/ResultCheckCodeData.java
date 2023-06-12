package com.sk.maa.pojo;

public class ResultCheckCodeData {
    private String msg;
    private String img;
    private int code;
    private boolean captchaEnabled;
    private String uuid;

    public ResultCheckCodeData() {
    }

    public ResultCheckCodeData(String msg, String img, int code, boolean captchaEnabled, String uuid) {
        this.msg = msg;
        this.img = img;
        this.code = code;
        this.captchaEnabled = captchaEnabled;
        this.uuid = uuid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isCaptchaEnabled() {
        return captchaEnabled;
    }

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ResultCheckCodeData{" +
                "msg='" + msg + '\'' +
                ", img='" + img + '\'' +
                ", code=" + code +
                ", captchaEnabled=" + captchaEnabled +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
