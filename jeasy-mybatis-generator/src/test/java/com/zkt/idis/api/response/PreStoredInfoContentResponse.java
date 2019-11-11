package com.zkt.idis.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述:
 *
 * @className:PreStoredInfoContentResponse
 * @projectName:20190808-idis-api
 * @author:Dayu
 * @date: 2019/10/24 15:55
 */

@ApiModel(value = "预存信息内容DTO响应参数", description = "预存信息内容DTO响应参数")
public class PreStoredInfoContentResponse {

    @ApiModelProperty("主键ID")
    private Integer preStoredInfoId;

    @ApiModelProperty("主题")
    private String title;

    @ApiModelProperty("内容")
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
