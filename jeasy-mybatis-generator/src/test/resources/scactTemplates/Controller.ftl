<#assign dateTime = .now>
package ${package};

import com.sc.act.api.request.${tableClass.shortClassName}Request;
import com.sc.act.api.commons.web.base.BaseController;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.commons.web.base.Result;
import com.sc.act.api.request.${tableClass.shortClassName}ListRequest;
import com.sc.act.api.response.${tableClass.shortClassName}ContentResponse;
import com.sc.act.api.response.${tableClass.shortClassName}Response;
import com.sc.act.api.service.${tableClass.shortClassName}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述: ${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}控制类
 *
 * @className:${tableClass.shortClassName}${mapperSuffix}
 * @projectName:
 * @author: generater-code
 * @date: ${dateTime?string("yyyy-MM-dd HH:mm:ss")}
 */
@RestController
@RequestMapping(value = "/mis<#list tableClass.tableName?split("_") as uri><#if uri_index != 0 && uri != 'info'>/${uri}</#if></#list>")
@Api(value = "${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}控制类", tags = "${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}控制类")
public class ${tableClass.shortClassName}${mapperSuffix} extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(${tableClass.shortClassName}Controller.class);

    @Autowired
    private ${tableClass.shortClassName}Service ${tableClass.variableName}Service;

    @ApiOperation("创建或更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}")
    @PostMapping("/create_modify")
    public Result creationOrModify(@RequestBody @Valid ${tableClass.shortClassName}Request ${tableClass.variableName}Request) {
        LOG.info("创建或更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}请求参数{}", ${tableClass.variableName}Request.toString());
        Result result = new Result();

        if (null != <#list tableClass.pkFields as fieldNames>${tableClass.variableName}Request.get${fieldNames.fieldName?cap_first}</#list>()) {
            ${tableClass.variableName}Service.update${tableClass.shortClassName}(${tableClass.variableName}Request);
        } else {
            ${tableClass.variableName}Service.insert${tableClass.shortClassName}(${tableClass.variableName}Request);
        }
        result.setRetMsg("操作成功");
        return result;
    }

    @ApiOperation("查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}")
    @GetMapping("/info")
    public Result<${tableClass.shortClassName}ContentResponse> queryInfo(@NotNull @RequestParam(name = "<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>") Integer <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>) {
        LOG.info("查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}请求参数<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>={}", <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);
        Result<${tableClass.shortClassName}ContentResponse> result = new Result<>();
        ${tableClass.shortClassName}ContentResponse response =
                ${tableClass.variableName}Service.select${tableClass.shortClassName}Content(<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表")
    @PostMapping("/info/list")
    public Result<PageResponse<${tableClass.shortClassName}Response>> queryInfoList(@RequestBody @Valid ${tableClass.shortClassName}ListRequest ${tableClass.variableName}ListRequest) {
        LOG.info("查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表请求参数{}", ${tableClass.variableName}ListRequest.toString());
        Result<PageResponse<${tableClass.shortClassName}Response>> result = new Result<>();
        PageResponse<${tableClass.shortClassName}Response> response = ${tableClass.variableName}Service.select${tableClass.shortClassName}(${tableClass.variableName}ListRequest);
        result.setRetMsg("查询成功");
        result.setData(response);
        return result;
    }


}
