package com.zkt.idis.common.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        value = "预存信息内容DTO响应参数",
        description = "预存信息内容DTO响应参数"
)
public class PreStoredInfoContentDtoResponse {
    @ApiModelProperty("主键ID")
    private Integer preStoredInfoId;
    @ApiModelProperty("主题")
    private String title;
    @ApiModelProperty("内容")
    private String content;

    public PreStoredInfoContentDtoResponse() {
    }

    public Integer getPreStoredInfoId() {
        return this.preStoredInfoId;
    }

    public void setPreStoredInfoId(Integer preStoredInfoId) {
        this.preStoredInfoId = preStoredInfoId;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
