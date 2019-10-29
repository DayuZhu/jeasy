package com.zkt.idis.common.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(
        value = "预存信息DTO请求参数",
        description = "预存信息DTO请求参数"
)
public class PreStoredInfoDtoRequest extends BaseDto {
    @ApiModelProperty("主键ID")
    private Integer preStoredInfoId;
    @ApiModelProperty("主题")
    @NotBlank
    private String title;
    @ApiModelProperty("操作人ID")
    @NotNull
    private Integer userId;
    @ApiModelProperty("操作人姓名")
    @NotBlank
    private String userName;
    @ApiModelProperty("草稿内容")
    @NotBlank
    private String content;

    public PreStoredInfoDtoRequest() {
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

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
