<#assign dateTime = .now>
package ${package};

import com.sc.act.api.request.${tableClass.shortClassName}Request;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.request.${tableClass.shortClassName}ListRequest;
import com.sc.act.api.response.${tableClass.shortClassName}ContentResponse;
import com.sc.act.api.response.${tableClass.shortClassName}Response;

/**
 * 功能描述:${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务类
 *
 * @className:${tableClass.shortClassName}${mapperSuffix}
 * @projectName:
 * @author:generater-code
 * @date: ${dateTime?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${tableClass.shortClassName}${mapperSuffix} {

    /**
     * 新增${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}
     *
     * @param ${tableClass.variableName}Request
     */
    void insert${tableClass.shortClassName}(${tableClass.shortClassName}Request ${tableClass.variableName}Request);


    /**
     * 更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}
     *
     * @param ${tableClass.variableName}Request
     */
    void update${tableClass.shortClassName}(${tableClass.shortClassName}Request ${tableClass.variableName}Request);


    /**
     * 查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}
     *
     * @param <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>
     * @return
     */
    ${tableClass.shortClassName}ContentResponse select${tableClass.shortClassName}Content(Integer <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);


    /**
     * 查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表
     *
     * @param ${tableClass.variableName}ListRequest
     * @return
     */
    PageResponse<${tableClass.shortClassName}Response> select${tableClass.shortClassName}(${tableClass.shortClassName}ListRequest ${tableClass.variableName}ListRequest);


}
