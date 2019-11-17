<#assign dateTime = .now>
package ${package};

<#if mapperSuffix?contains("Api")>
import com.zkt.idis.api.request.${tableClass.shortClassName}ListRequest;
import com.zkt.idis.api.request.${tableClass.shortClassName}Request;
import com.zkt.idis.api.response.${tableClass.shortClassName}ContentResponse;
import com.zkt.idis.api.response.${tableClass.shortClassName}Response;
import com.zkt.idis.api.service.merchant.${tableClass.shortClassName}ApiService;
import com.zkt.idis.common.dto.request.${tableClass.shortClassName}DtoRequest;
import com.zkt.idis.common.dto.request.${tableClass.shortClassName}ListDtoRequest;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.log.LogUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
<#else>
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.zkt.idis.common.dto.request.${tableClass.shortClassName}DtoRequest;
import com.zkt.idis.common.dto.request.${tableClass.shortClassName}ListDtoRequest;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.idis.common.dto.response.${tableClass.shortClassName}ContentDtoResponse;
import com.zkt.idis.common.dto.response.${tableClass.shortClassName}DtoResponse;
import com.zkt.idis.svc.dao.db.auto.${tableClass.shortClassName}Mapper;
import com.zkt.idis.svc.model.${tableClass.shortClassName};
import com.zkt.idis.svc.model.${tableClass.shortClassName}Example;
import com.zkt.idis.svc.service.${tableClass.shortClassName}Service;
import com.zkt.log.LogUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

</#if>
/**
 * 功能描述:${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务实现类
 *
 * @className:${tableClass.shortClassName}${mapperSuffix}
 * @projectName:idis-svc
 * @author: generater-code
 * @date: ${dateTime?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service
public class ${tableClass.shortClassName}${mapperSuffix} implements ${tableClass.shortClassName}<#if mapperSuffix?contains("Api")>Api</#if>Service {

<#if mapperSuffix?contains("Api")>
    //@Autowired
    //private IdisSvcClient idisSvcClient;

    @Override
    public void createOrModify${tableClass.shortClassName}(${tableClass.shortClassName}Request ${tableClass.variableName}Request) {
        LogUtil.logApplicationInfo("进入创建或更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}API服务请求参数" + ${tableClass.variableName}Request.toString());
        ${tableClass.shortClassName}DtoRequest ${tableClass.variableName}DtoRequest = new ${tableClass.shortClassName}DtoRequest();
        BeanUtils.copyProperties(${tableClass.variableName}Request, ${tableClass.variableName}DtoRequest);
        //TODO 补充相关逻辑

        //TODO 补充Feign调用逻辑
        //AjaxResult ajaxResult = idisSvcClient.creationOrModify(${tableClass.variableName}DtoRequest);
        //AjaxResultParseUtil.getData(ajaxResult, ${tableClass.variableName}Request);

    }

    @Override
    public ${tableClass.shortClassName}ContentResponse query${tableClass.shortClassName}Content(Integer <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>) {
        LogUtil.logApplicationInfo("进入查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}API服务请求参数<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>=" + <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);
        //TODO 补充相关逻辑

        //TODO 补充Feign调用逻辑
        //AjaxResult<${tableClass.shortClassName}ContentDtoResponse> ajaxResult = idisSvcClient.queryInfo(preStoredInfoId);
        //${tableClass.shortClassName}ContentDtoResponse data = AjaxResultParseUtil.getData(ajaxResult);
        ${tableClass.shortClassName}ContentResponse ${tableClass.variableName}ContentResponse = new ${tableClass.shortClassName}ContentResponse();
        //if (null != data) {
        //    BeanUtils.copyProperties(data, ${tableClass.variableName}ContentResponse);
        //}
        return ${tableClass.variableName}ContentResponse;
    }

    @Override
    public PageResponse<${tableClass.shortClassName}Response> query${tableClass.shortClassName}(${tableClass.shortClassName}ListRequest ${tableClass.variableName}ListRequest) {
        LogUtil.logApplicationInfo("进入查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表API服务请求参数" + ${tableClass.variableName}ListRequest.toString());
        ${tableClass.shortClassName}ListDtoRequest ${tableClass.variableName}ListDtoRequest = new ${tableClass.shortClassName}ListDtoRequest();
        BeanUtils.copyProperties(${tableClass.variableName}ListRequest, ${tableClass.variableName}ListDtoRequest);
        //TODO 补充相关逻辑

        //TODO 补充Feign调用逻辑
        //AjaxResult<PageResponse<${tableClass.shortClassName}DtoResponse>> ajaxResult = idisSvcClient.queryInfoList(${tableClass.variableName}ListDtoRequest);
        //PageResponse<${tableClass.shortClassName}DtoResponse> data = AjaxResultParseUtil.getData(ajaxResult);
        PageResponse<${tableClass.shortClassName}Response> response = new PageResponse<>();
        //if (null != data) {
        //   BeanUtils.copyProperties(data, response);
        //}
        return response;
    }
<#else>
    @Autowired
    private ${tableClass.shortClassName}Mapper ${tableClass.variableName}Mapper;

    @Override
    public void insert${tableClass.shortClassName}(${tableClass.shortClassName}DtoRequest ${tableClass.variableName}DtoRequest) {
        LogUtil.logApplicationInfo("进入创建${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务请求参数" + ${tableClass.variableName}DtoRequest.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ${tableClass.shortClassName} ${tableClass.variableName} = new ${tableClass.shortClassName}();
        BeanUtils.copyProperties(${tableClass.variableName}DtoRequest, ${tableClass.variableName});

        //TODO 必要的逻辑补充，如默认数据状态补充

        ${tableClass.variableName}Mapper.insertSelective(${tableClass.variableName});

    }

    @Override
    public void update${tableClass.shortClassName}(${tableClass.shortClassName}DtoRequest ${tableClass.variableName}DtoRequest) {
        LogUtil.logApplicationInfo("进入更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务请求参数" + ${tableClass.variableName}DtoRequest.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ${tableClass.shortClassName} ${tableClass.variableName} = new ${tableClass.shortClassName}();
        BeanUtils.copyProperties(${tableClass.variableName}DtoRequest, ${tableClass.variableName});

        //TODO 必要的逻辑补充，如默认数据状态补充

        ${tableClass.variableName}Mapper.updateByPrimaryKeySelective(${tableClass.variableName});
    }

    @Override
    public ${tableClass.shortClassName}ContentDtoResponse select${tableClass.shortClassName}Content(Integer <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>) {
        LogUtil.logApplicationInfo("进入查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务请求参数<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>=" + <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);
        ${tableClass.shortClassName}ContentDtoResponse ${tableClass.variableName}ContentDtoResponse = new ${tableClass.shortClassName}ContentDtoResponse();
        ${tableClass.shortClassName} ${tableClass.variableName} = ${tableClass.variableName}Mapper.selectByPrimaryKey(<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);
        if (null == ${tableClass.variableName}) {
            return ${tableClass.variableName}ContentDtoResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(${tableClass.variableName}, ${tableClass.variableName}ContentDtoResponse);

        return ${tableClass.variableName}ContentDtoResponse;
    }

    @Override
    public PageResponse<${tableClass.shortClassName}DtoResponse> select${tableClass.shortClassName}(${tableClass.shortClassName}ListDtoRequest ${tableClass.variableName}ListDtoRequest) {
        LogUtil.logApplicationInfo("进入查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表服务请求参数" + ${tableClass.variableName}ListDtoRequest.toString());
        ${tableClass.shortClassName}Example ${tableClass.variableName}Example = new ${tableClass.shortClassName}Example();
        ${tableClass.variableName}Example.setOrderByClause("<#list tableClass.pkFields as fieldNames>${fieldNames.columnName}</#list> desc");
        ${tableClass.shortClassName}Example.Criteria criteria = ${tableClass.variableName}Example.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != ${tableClass.variableName}ListDtoRequest.get<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName?cap_first}</#list>()) {
            criteria.and<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName?cap_first}</#list>EqualTo(${tableClass.variableName}ListDtoRequest.get<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName?cap_first}</#list>());
        }

        PageHelper.startPage(${tableClass.variableName}ListDtoRequest.getPageIndex(), ${tableClass.variableName}ListDtoRequest.getPageSize());
        List<${tableClass.shortClassName}> ${tableClass.variableName}List = ${tableClass.variableName}Mapper.selectByExample(${tableClass.variableName}Example);
        PageSerializable<${tableClass.shortClassName}> pageInfo = PageSerializable.of(${tableClass.variableName}List);
        PageResponse<${tableClass.shortClassName}DtoResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<${tableClass.shortClassName}DtoResponse> list = new ArrayList<>();
        response.setList(list);
        ${tableClass.variableName}List.forEach(${tableClass.variableName} -> {
            ${tableClass.shortClassName}DtoResponse ${tableClass.variableName}DtoResponse = new ${tableClass.shortClassName}DtoResponse();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(${tableClass.variableName}, ${tableClass.variableName}DtoResponse);

            list.add(${tableClass.variableName}DtoResponse);
        });
        return response;
    }
</#if>

}
