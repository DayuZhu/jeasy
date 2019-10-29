/**
 * 杀杀杀
 */
package com.zkt.idis.common.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        value = "预存信息列表DTO请求参数",
        description = "预存信息列表DTO请求参数"
)
public class PreStoredInfoListDtoRequest extends PageRequest {
    @ApiModelProperty("主题")
    private String title;

    public PreStoredInfoListDtoRequest() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
