package com.zkt.idis.svc.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @表名 idis_pre_stored_info
 * @日期 2019-10-29
 * @作者 generator-code 
 * @修改人  
 */
public class PreStoredInfo implements Serializable {
    /** 主键id (主健ID) (无默认值) */
    private Integer preStoredInfoId;

    /** 预存信息主键(必填项)  (默认值为: 0) */
    private Integer relationId;

    /** 0-其他 1-商户创建信息(必填项)  (默认值为: 0) */
    private Integer relationType;

    /** 预存信息类型描述 例:草稿-商户创建信息(必填项)  (默认值为: ) */
    private String relationTypeDesc;

    /** 预存内容主题(必填项)  (默认值为: ) */
    private String title;

    /** 创建时间(必填项)  (默认值为: CURRENT_TIMESTAMP) */
    private Date createTime;

    /** 更新时间(必填项)  (默认值为: CURRENT_TIMESTAMP) */
    private Date updateTime;

    /** 操作人id(必填项)  (默认值为: 0) */
    private Integer userId;

    /** 操作人名称(必填项)  (默认值为: ) */
    private String userName;

    /** 预存内容(必填项) (无默认值) */
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