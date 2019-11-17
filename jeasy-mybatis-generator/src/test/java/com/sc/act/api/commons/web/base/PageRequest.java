package com.sc.act.api.commons.web.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 功能描述:
 *
 * @className:Page
 * @projectName:20190808-idis-common
 * @author:Dayu
 * @date: 2019/7/26 16:25
 */
@ApiModel(value = "分页DTO请求参数", description = "分页DTO请求参数")
public class PageRequest extends BasePojo {

    @ApiModelProperty(value = "当前页数", name = "pageIndex", example = "1")
    @Min(1)
    @NotNull(message = "当前页数不能为空")
    private Integer pageIndex;

    @ApiModelProperty(value = "每页显示条数", name = "pageSize", example = "15")
    @NotNull(message = "当前页数不能显示条数")
    @Min(1)
    private Integer pageSize;

    @NotNull
    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(@NotNull Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    @NotNull
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(@NotNull Integer pageSize) {
        this.pageSize = pageSize;
    }
}
