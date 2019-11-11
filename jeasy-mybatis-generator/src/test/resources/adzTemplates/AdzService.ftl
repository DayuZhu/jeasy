<#assign dateTime = .now>
package ${package};

import com.zkt.idis.common.dto.request.${tableClass.shortClassName}DtoRequest;
import com.zkt.idis.common.dto.request.${tableClass.shortClassName}ListDtoRequest;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.idis.common.dto.response.${tableClass.shortClassName}ContentDtoResponse;
import com.zkt.idis.common.dto.response.${tableClass.shortClassName}DtoResponse;

/**
 * 功能描述:${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务类
 *
 * @className:${tableClass.shortClassName}${mapperSuffix}
 * @projectName:idis-svc
 * @author:generater-code
 * @date: ${dateTime?string("yyyy-MM-dd HH:mm:ss")}
 */
public interface ${tableClass.shortClassName}Service {

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


}
