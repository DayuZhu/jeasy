<#assign dateTime = .now>
package ${package};

import com.zkt.common.model.AjaxResult;
import com.zkt.idis.common.dto.request.${tableClass.shortClassName}DtoRequest;
import com.zkt.idis.common.dto.request.${tableClass.shortClassName}ListDtoRequest;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.idis.common.dto.response.${tableClass.shortClassName}ContentDtoResponse;
import com.zkt.idis.common.dto.response.${tableClass.shortClassName}DtoResponse;
import ${projectPackage}.service.${tableClass.shortClassName}Service;
import com.zkt.log.LogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: ${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}控制类
 *
 * @className:${tableClass.shortClassName}${mapperSuffix}
 * @projectName:idis-svc
 * @author: generater-code
 * @date: ${dateTime?string("yyyy-MM-dd HH:mm:ss zzzz")}
 */
@RestController
@RequestMapping(value = "/mis<#list tableClass.tableName?split("_") as uri><#if uri_index != 0>/${uri}</#if></#list>")
@Api(value = "${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}控制类", tags = "${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}控制类")
@Validated
public class ${tableClass.shortClassName}Controller {

    @Autowired
    private ${tableClass.shortClassName}Service ${tableClass.variableName}Service;

    @ApiOperation("创建或更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}")
    @PostMapping("/createOrModify")
    public AjaxResult creationOrModify(@RequestBody @Valid ${tableClass.shortClassName}DtoRequest ${tableClass.variableName}DtoRequest) {
        LogUtil.logApplicationInfo("创建或更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}请求参数" + ${tableClass.variableName}DtoRequest.toString());
        AjaxResult result = new AjaxResult();

        if (null != <#list tableClass.pkFields as fieldNames>${tableClass.variableName}DtoRequest.get${fieldNames.fieldName?cap_first}</#list>()) {
            ${tableClass.variableName}Service.update${tableClass.shortClassName}(${tableClass.variableName}DtoRequest);
        } else {
            ${tableClass.variableName}Service.insert${tableClass.shortClassName}(${tableClass.variableName}DtoRequest);
        }
        result.setMessage("操作成功");
        return result;
    }

    @ApiOperation("查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}")
    @GetMapping("/query/info")
    public AjaxResult<${tableClass.shortClassName}ContentDtoResponse> queryInfo(@NotNull @RequestParam(name = "<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>") Integer <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>) {
        LogUtil.logApplicationInfo("查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}请求参数<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>=" + <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);
        AjaxResult<${tableClass.shortClassName}ContentDtoResponse> result = new AjaxResult<>();
        ${tableClass.shortClassName}ContentDtoResponse dtoResponse =
                preStoredInfoService.select${tableClass.shortClassName}Content(<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);
        result.setMessage("查询成功");
        result.setData(dtoResponse);
        return result;
    }

    @ApiOperation("查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表")
    @PostMapping("/query/info/list")
    public AjaxResult<PageResponse<${tableClass.shortClassName}DtoResponse>> queryInfoList(@RequestBody @Valid ${tableClass.shortClassName}ListDtoRequest ${tableClass.variableName}ListDtoRequest) {
        LogUtil.logApplicationInfo("查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表请求参数" + ${tableClass.variableName}ListDtoRequest.toString());
        AjaxResult<PageResponse<${tableClass.shortClassName}DtoResponse>> result = new AjaxResult<>();
        PageResponse<${tableClass.shortClassName}DtoResponse> response = ${tableClass.variableName}Service.select${tableClass.shortClassName}(${tableClass.variableName}ListDtoRequest);
        result.setMessage("查询成功");
        result.setData(response);
        return result;
    }

}
