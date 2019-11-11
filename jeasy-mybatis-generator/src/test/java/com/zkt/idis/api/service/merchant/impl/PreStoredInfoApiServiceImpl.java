package com.zkt.idis.api.service.merchant.impl;

import com.zkt.common.model.AjaxResult;
//import com.zkt.idis.api.dao.client.IdisSvcClient;
import com.zkt.idis.api.request.PreStoredInfoListRequest;
import com.zkt.idis.api.request.PreStoredInfoRequest;
import com.zkt.idis.api.response.PreStoredInfoContentResponse;
import com.zkt.idis.api.response.PreStoredInfoResponse;
import com.zkt.idis.api.service.merchant.PreStoredInfoApiService;
import com.zkt.idis.common.dto.request.PreStoredInfoDtoRequest;
import com.zkt.idis.common.dto.request.PreStoredInfoListDtoRequest;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.idis.common.dto.response.PreStoredInfoContentDtoResponse;
import com.zkt.idis.common.dto.response.PreStoredInfoDtoResponse;
import com.zkt.idis.common.util.AjaxResultParseUtil;
import com.zkt.log.LogUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述:
 *
 * @className:PreStoredInfoServiceImpl
 * @projectName:20190808-idis-api
 * @author:Dayu
 * @date: 2019/10/24 15:57
 */
@Service
public class PreStoredInfoApiServiceImpl implements PreStoredInfoApiService {

    //@Autowired
    //private IdisSvcClient idisSvcClient;

    @Override
    public void createOrModifyPreStoredInfo(PreStoredInfoRequest preStoredInfoRequest) {
        LogUtil.logApplicationInfo("进入创建或更新预存信息API服务请求参数" + preStoredInfoRequest.toString());
        PreStoredInfoDtoRequest preStoredInfoDtoRequest = new PreStoredInfoDtoRequest();
        BeanUtils.copyProperties(preStoredInfoRequest, preStoredInfoDtoRequest);
        //AjaxResult ajaxResult = idisSvcClient.creationOrModify(preStoredInfoDtoRequest);
        //AjaxResultParseUtil.getData(ajaxResult, preStoredInfoRequest);

    }

    @Override
    public PreStoredInfoContentResponse queryPreStoredInfoContent(Integer preStoredInfoId) {
        LogUtil.logApplicationInfo("进入查询预存信息API服务请求参数preStoredInfoId=" + preStoredInfoId);
        //AjaxResult<PreStoredInfoContentDtoResponse> ajaxResult = idisSvcClient.queryInfo(preStoredInfoId);
        //PreStoredInfoContentDtoResponse data = AjaxResultParseUtil.getData(ajaxResult);
        PreStoredInfoContentResponse storedInfoContentResponse = new PreStoredInfoContentResponse();
        //if (null != data) {
        //    BeanUtils.copyProperties(data, storedInfoContentResponse);
        //}
        return storedInfoContentResponse;
    }

    @Override
    public PageResponse<PreStoredInfoResponse> queryPreStoredInfo(PreStoredInfoListRequest preStoredInfoListRequest) {
        LogUtil.logApplicationInfo("进入查询预存信息列表API服务请求参数" + preStoredInfoListRequest.toString());
        PreStoredInfoListDtoRequest preStoredInfoListDtoRequest = new PreStoredInfoListDtoRequest();
        BeanUtils.copyProperties(preStoredInfoListRequest, preStoredInfoListDtoRequest);
        //AjaxResult<PageResponse<PreStoredInfoDtoResponse>> ajaxResult = idisSvcClient.queryInfoList(preStoredInfoListDtoRequest);
        //PageResponse<PreStoredInfoDtoResponse> data = AjaxResultParseUtil.getData(ajaxResult);
        PageResponse<PreStoredInfoResponse> response = new PageResponse<>();
        //if (null != data) {
        //   BeanUtils.copyProperties(data, response);
        //}
        return response;
    }

}
