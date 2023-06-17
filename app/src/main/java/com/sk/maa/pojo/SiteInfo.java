package com.sk.maa.pojo;

public class SiteInfo {
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private String remark;
    private int id;
    private int siteTypeId;
    private String pic;
    private String address;
    private String content;
    private String delFlag;
    private SiteType siteType;
    private int siteId;

    public SiteInfo() {
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSiteTypeId() {
        return siteTypeId;
    }

    public void setSiteTypeId(int siteTypeId) {
        this.siteTypeId = siteTypeId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public SiteType getSiteType() {
        return siteType;
    }

    public void setSiteType(SiteType siteType) {
        this.siteType = siteType;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return "SiteInfo{" +
                "createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", remark='" + remark + '\'' +
                ", id=" + id +
                ", siteTypeId=" + siteTypeId +
                ", pic='" + pic + '\'' +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", siteType=" + siteType +
                ", siteId=" + siteId +
                '}';
    }
}
