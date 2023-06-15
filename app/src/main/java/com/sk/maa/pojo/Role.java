package com.sk.maa.pojo;

public class Role {
    private Object createBy;
    private Object createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private int roleId;
    private String roleName;
    private String roleKey;
    private int roleSort;
    private String dataScope;
    private boolean menuCheckStrictly;
    private boolean deptCheckStrictly;
    private String status;
    private Object delFlag;
    private boolean flag;
    private Object menuIds;
    private Object deptIds;
    private Object permissions;
    private boolean admin;

    public Role() {
    }

    public Role(Object createBy, Object createTime, Object updateBy, Object updateTime, Object remark, int roleId, String roleName, String roleKey, int roleSort, String dataScope, boolean menuCheckStrictly, boolean deptCheckStrictly, String status, Object delFlag, boolean flag, Object menuIds, Object deptIds, Object permissions, boolean admin) {
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.remark = remark;
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.roleSort = roleSort;
        this.dataScope = dataScope;
        this.menuCheckStrictly = menuCheckStrictly;
        this.deptCheckStrictly = deptCheckStrictly;
        this.status = status;
        this.delFlag = delFlag;
        this.flag = flag;
        this.menuIds = menuIds;
        this.deptIds = deptIds;
        this.permissions = permissions;
        this.admin = admin;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public int getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(int roleSort) {
        this.roleSort = roleSort;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public boolean isMenuCheckStrictly() {
        return menuCheckStrictly;
    }

    public void setMenuCheckStrictly(boolean menuCheckStrictly) {
        this.menuCheckStrictly = menuCheckStrictly;
    }

    public boolean isDeptCheckStrictly() {
        return deptCheckStrictly;
    }

    public void setDeptCheckStrictly(boolean deptCheckStrictly) {
        this.deptCheckStrictly = deptCheckStrictly;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Object delFlag) {
        this.delFlag = delFlag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Object menuIds) {
        this.menuIds = menuIds;
    }

    public Object getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(Object deptIds) {
        this.deptIds = deptIds;
    }

    public Object getPermissions() {
        return permissions;
    }

    public void setPermissions(Object permissions) {
        this.permissions = permissions;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Role{" +
                "createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", remark=" + remark +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", roleSort=" + roleSort +
                ", dataScope='" + dataScope + '\'' +
                ", menuCheckStrictly=" + menuCheckStrictly +
                ", deptCheckStrictly=" + deptCheckStrictly +
                ", status='" + status + '\'' +
                ", delFlag=" + delFlag +
                ", flag=" + flag +
                ", menuIds=" + menuIds +
                ", deptIds=" + deptIds +
                ", permissions=" + permissions +
                ", admin=" + admin +
                '}';
    }
}
