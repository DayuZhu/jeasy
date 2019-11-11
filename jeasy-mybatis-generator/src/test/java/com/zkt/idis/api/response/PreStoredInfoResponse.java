package com.zkt.idis.api.response;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 功能描述:
 *
 * @className:PreStoredInfoResponse
 * @projectName:20190808-idis-api
 * @author:Dayu
 * @date: 2019/10/24 15:56
 */
@ApiModel(value = "预存信息DTO响应参数", description = "预存信息DTO响应参数")
public class PreStoredInfoResponse {

    @ApiModelProperty("主键ID")
    private Integer preStoredInfoId;

    @ApiModelProperty("主题")
    private String title;

    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty("操作时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty("操作人ID")
    private Integer userId;

    @ApiModelProperty("操作人")
    private String userName;

    public Integer getPreStoredInfoId() {
        return preStoredInfoId;
    }

    public void setPreStoredInfoId(Integer preStoredInfoId) {
        this.preStoredInfoId = preStoredInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        this.userName = userName;
    }
}
