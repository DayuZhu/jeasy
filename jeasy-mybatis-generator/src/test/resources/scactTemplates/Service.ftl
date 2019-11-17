<#assign dateTime = .now>
package ${package};

<#if mapperSuffix?contains("Api")>
import com.zkt.idis.api.request.${tableClass.shortClassName}ListRequest;
import com.zkt.idis.api.request.${tableClass.shortClassName}Request;
import com.zkt.idis.api.response.${tableClass.shortClassName}ContentResponse;
import com.zkt.idis.api.response.${tableClass.shortClassName}Response;
import com.zkt.idis.common.dto.response.PageResponse;
<#else>
import com.zkt.idis.common.dto.request.${tableClass.shortClassName}DtoRequest;
import com.zkt.idis.common.dto.request.${tableClass.shortClassName}ListDtoRequest;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.idis.common.dto.response.${tableClass.shortClassName}ContentDtoResponse;
import com.zkt.idis.common.dto.response.${tableClass.shortClassName}DtoResponse;
</#if>

/**
 * 功能描述:${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务类
 *
 * @className:${tableClass.shortClassName}${mapperSuffix}
 * @projectName:idis-svc
 * @author:generater-code
 * @date: ${dateTime?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${tableClass.shortClassName}${mapperSuffix} {

<#if mapperSuffix?contains("Api")>
    /**
     * 新增或更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}
     *
     * @param ${tableClass.variableName}Request
     */
    void createOrModify${tableClass.shortClassName}(${tableClass.shortClassName}Request ${tableClass.variableName}Request);


    /**
     * 查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}
     *
     * @param <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>
     * @return
     */
    ${tableClass.shortClassName}ContentResponse query${tableClass.shortClassName}Content(Integer <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);


    /**
     * 查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表
     *
     * @param ${tableClass.variableName}ListRequest
     * @return
     */
    PageResponse<${tableClass.shortClassName}Response> query${tableClass.shortClassName}(${tableClass.shortClassName}ListRequest ${tableClass.variableName}ListRequest);

<#else>
    /**
     * 新增${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}
     *
     * @param ${tableClass.variableName}DtoRequest
     */
    void insert${tableClass.shortClassName}(${tableClass.shortClassName}DtoRequest ${tableClass.variableName}DtoRequest);


    /**
     * 更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}
     *
     * @param ${tableClass.variableName}DtoRequest
     */
    void update${tableClass.shortClassName}(${tableClass.shortClassName}DtoRequest ${tableClass.variableName}DtoRequest);


    /**
     * 查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}
     *
     * @param <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>
     * @return
     */
    ${tableClass.shortClassName}ContentDtoResponse select${tableClass.shortClassName}Content(Integer <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);


    /**
     * 查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表
     *
     * @param ${tableClass.variableName}ListDtoRequest
     * @return
     */
    PageResponse<${tableClass.shortClassName}DtoResponse> select${tableClass.shortClassName}(${tableClass.shortClassName}ListDtoRequest ${tableClass.variableName}ListDtoRequest);

</#if>

}
