package com.sk.maa.pojo;

public class Notice {
    private String createBy;
    private String createTime;
    private String updateBy;
    private Object updateTime;
    private String remark;
    private int noticeId;
    private String noticeTitle;
    private String noticeType;
    private String noticeContent;
    private String status;

    public Notice() {
    }

    public Notice(String createBy, String createTime, String updateBy, Object updateTime, String remark, int noticeId, String noticeTitle, String noticeType, String noticeContent, String status) {
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.remark = remark;
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeType = noticeType;
        this.noticeContent = noticeContent;
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", remark='" + remark + '\'' +
                ", noticeId=" + noticeId +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeType='" + noticeType + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
