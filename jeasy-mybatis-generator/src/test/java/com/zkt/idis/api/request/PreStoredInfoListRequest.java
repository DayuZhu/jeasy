package com.zkt.idis.api.request;

import com.zkt.idis.common.dto.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述:
 *
 * @className:PreStoredInfoListRequest
 * @projectName:20190808-idis-api
 * @author:Dayu
 * @date: 2019/10/24 15:57
 */

@ApiModel(value = "预存信息列表DTO请求参数", description = "预存信息列表DTO请求参数")
public class PreStoredInfoListRequest extends PageRequest {

    @ApiModelProperty("主题")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
