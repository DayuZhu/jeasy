package com.zkt.idis.svc.model;

import java.io.Serializable;
import java.util.Date;

public class PreStoredInfo implements Serializable {
    private Integer preStoredInfoId;

    private Integer relationId;

    private Integer relationType;

    private String relationTypeDesc;

    private String title;

    private Date createTime;

    private Date updateTime;

    private Integer userId;

    private String userName;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getPreStoredInfoId() {
        return preStoredInfoId;
    }

    public void setPreStoredInfoId(Integer preStoredInfoId) {
        this.preStoredInfoId = preStoredInfoId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public String getRelationTypeDesc() {
        return relationTypeDesc;
    }

    public void setRelationTypeDesc(String relationTypeDesc) {
        this.relationTypeDesc = relationTypeDesc == null ? null : relationTypeDesc.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}