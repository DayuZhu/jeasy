package com.zkt.idis.api.request;

import com.zkt.idis.common.dto.request.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 功能描述:
 *
 * @className:PreStoredInfoRequest
 * @projectName:20190808-idis-api
 * @author:Dayu
 * @date: 2019/10/24 15:54
 */
@ApiModel(value = "预存信息请求参数", description = "预存信息请求参数")
public class PreStoredInfoRequest extends BaseDto {

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

    @NotNull
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
