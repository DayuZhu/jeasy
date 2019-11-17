<#assign dateTime = .now>
package ${package};

import com.zkt.idis.common.dto.request.BaseDto;
import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

<#list tableClass.allFields as fieldNames>
    <#list tableClass.allFields as fieldNames2>
        <#if fieldNames.shortTypeName ==fieldNames2.shortTypeName>
            <#if fieldNames_index==fieldNames2_index>
                <#if fieldNames.shortTypeName == "Date">
import java.util.Date;
                </#if>
            <#else>
                <#break >
            </#if>
        </#if>
    </#list>
</#list>

/**
 * 功能描述: ${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}<#if mapperSuffix?contains("Dto")>DTO</#if>响应类
 *
 * @className:${tableClass.shortClassName}${mapperSuffix}
 * @projectName:idis-svc
 * @author: generater-code
 * @date: ${dateTime?string("yyyy-MM-dd HH:mm:ss")}
 */
@ApiModel(value = "${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}<#if mapperSuffix?contains("Dto")>DTO</#if>响应参数", description = "${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}<#if mapperSuffix?contains("Dto")>DTO</#if>响应参数")
public class ${tableClass.shortClassName}${mapperSuffix} extends BaseDto {

<#list tableClass.allFields as fieldNames>
    @ApiModelProperty("${fieldNames.remarks}")
    <#if fieldNames.shortTypeName == "Date">
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${fieldNames.shortTypeName} ${fieldNames.fieldName};

</#list>

<#list tableClass.allFields as fieldNames>
    public ${fieldNames.shortTypeName} get${fieldNames.fieldName?cap_first}() {
        return this.${fieldNames.fieldName};
    }

    public void set${fieldNames.fieldName?cap_first}(${fieldNames.shortTypeName} ${fieldNames.fieldName}) {
        this.${fieldNames.fieldName} = ${fieldNames.fieldName};
    }

</#list>

}
