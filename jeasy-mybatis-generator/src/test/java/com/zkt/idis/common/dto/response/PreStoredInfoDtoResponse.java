package com.zkt.idis.common.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

@ApiModel(
        value = "预存信息DTO响应参数",
        description = "预存信息DTO响应参数"
)
public class PreStoredInfoDtoResponse {
    @ApiModelProperty("主键ID")
    private Integer preStoredInfoId;
    @ApiModelProperty("主题")
    private String title;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("操作时间")
    private Date updateTime;
    @ApiModelProperty("操作人ID")
    private Integer userId;
    @ApiModelProperty("操作人")
    private String userName;

    public PreStoredInfoDtoResponse() {
    }

    public Integer getPreStoredInfoId() {
        return this.preStoredInfoId;
    }

    public void setPreStoredInfoId(Integer preStoredInfoId) {
        this.preStoredInfoId = preStoredInfoId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
