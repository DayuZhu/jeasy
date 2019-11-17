<#assign dateTime = .now>
package ${package};

import com.com.sc.act.api.request.${tableClass.shortClassName}Request;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.sc.act.api.commons.web.base.PageResponse;
import com.sc.act.api.mapper.auto.${tableClass.shortClassName}Mapper;
import com.sc.act.api.model.auto.${tableClass.shortClassName};
import com.sc.act.api.model.auto.${tableClass.shortClassName}Example;
import com.sc.act.api.request.${tableClass.shortClassName}ListRequest;
import com.sc.act.api.response.${tableClass.shortClassName}ContentResponse;
import com.sc.act.api.response.${tableClass.shortClassName}Response;
import com.sc.act.api.service.${tableClass.shortClassName}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务实现类
 *
 * @className:${tableClass.shortClassName}${mapperSuffix}
 * @projectName:
 * @author: generater-code
 * @date: ${dateTime?string("yyyy-MM-dd HH:mm:ss")}
 */
@Service
public class ${tableClass.shortClassName}${mapperSuffix} implements ${tableClass.shortClassName}<#if mapperSuffix?contains("Api")>Api</#if>Service {

    private static final Logger LOG = LoggerFactory.getLogger(${tableClass.shortClassName}ServiceImpl.class);

    @Autowired
    private ${tableClass.shortClassName}Mapper ${tableClass.variableName}Mapper;

    @Override
    public void insert${tableClass.shortClassName}(${tableClass.shortClassName}Request ${tableClass.variableName}Request) {
        LOG.info("进入创建${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务请求参数{}", ${tableClass.variableName}Request.toString());

        //TODO 必要的校验，如去重校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ${tableClass.shortClassName} ${tableClass.variableName} = new ${tableClass.shortClassName}();
        BeanUtils.copyProperties(${tableClass.variableName}Request, ${tableClass.variableName});

        //TODO 必要的逻辑补充，如默认数据状态补充

        ${tableClass.variableName}Mapper.insertSelective(${tableClass.variableName});

    }

    @Override
    public void update${tableClass.shortClassName}(${tableClass.shortClassName}Request ${tableClass.variableName}Request) {
        LOG.info("进入更新${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务请求参数{}", ${tableClass.variableName}Request.toString());

        //TODO 必要的业务校验

        //TODO 统一入库时间
        Date currentTime = new Date();


        ${tableClass.shortClassName} ${tableClass.variableName} = new ${tableClass.shortClassName}();
        BeanUtils.copyProperties(${tableClass.variableName}Request, ${tableClass.variableName});

        //TODO 必要的逻辑补充，如默认数据状态补充

        ${tableClass.variableName}Mapper.updateByPrimaryKeySelective(${tableClass.variableName});
    }

    @Override
    public ${tableClass.shortClassName}ContentDtoResponse select${tableClass.shortClassName}Content(Integer <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>) {
        LOG.info("进入查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}服务请求参数<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>{}", <#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);
        ${tableClass.shortClassName}ContentResponse ${tableClass.variableName}ContentResponse = new ${tableClass.shortClassName}ContentResponse();
        ${tableClass.shortClassName} ${tableClass.variableName} = ${tableClass.variableName}Mapper.selectByPrimaryKey(<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName}</#list>);
        if (null == ${tableClass.variableName}) {
            return ${tableClass.variableName}ContentResponse;
        }

        //TODO 必要业务逻辑补充

        //TODO 有些不需要的字段，可以不用 bean copy
        BeanUtils.copyProperties(${tableClass.variableName}, ${tableClass.variableName}ContentResponse);

        return ${tableClass.variableName}ContentResponse;
    }

    @Override
    public PageResponse<${tableClass.shortClassName}Response> select${tableClass.shortClassName}(${tableClass.shortClassName}ListRequest ${tableClass.variableName}ListRequest) {
        LOG.info("进入查询${tableClass.tableComment?substring(0,(tableClass.tableComment)?length-1)}列表服务请求参数{}", ${tableClass.variableName}ListRequest.toString());
        ${tableClass.shortClassName}Example ${tableClass.variableName}Example = new ${tableClass.shortClassName}Example();
        ${tableClass.variableName}Example.setOrderByClause("<#list tableClass.pkFields as fieldNames>${fieldNames.columnName}</#list> desc");
        ${tableClass.shortClassName}Example.Criteria criteria = ${tableClass.variableName}Example.createCriteria();

        //TODO 必要的业务查询条件补充
        if (null != ${tableClass.variableName}ListRequest.get<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName?cap_first}</#list>()) {
            criteria.and<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName?cap_first}</#list>EqualTo(${tableClass.variableName}ListRequest.get<#list tableClass.pkFields as fieldNames>${fieldNames.fieldName?cap_first}</#list>());
        }

        PageHelper.startPage(${tableClass.variableName}ListRequest.getPageIndex(), ${tableClass.variableName}ListRequest.getPageSize());
        List<${tableClass.shortClassName}> ${tableClass.variableName}List = ${tableClass.variableName}Mapper.selectByExample(${tableClass.variableName}Example);
        PageSerializable<${tableClass.shortClassName}> pageInfo = PageSerializable.of(${tableClass.variableName}List);
        PageResponse<${tableClass.shortClassName}DtoResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<${tableClass.shortClassName}DtoResponse> list = new ArrayList<>();
        response.setList(list);
        ${tableClass.variableName}List.forEach(${tableClass.variableName} -> {
            ${tableClass.shortClassName}Response ${tableClass.variableName}DtoResponse = new ${tableClass.shortClassName}Response();

            //TODO 有些不需要的字段，可以不用 bean copy
            BeanUtils.copyProperties(${tableClass.variableName}, ${tableClass.variableName}Response);

            list.add(${tableClass.variableName}Response);
        });
        return response;
    }


}
