package com.zkt.idis.svc.service;

import com.zkt.idis.common.dto.request.PreStoredInfoDtoRequest;
import com.zkt.idis.common.dto.request.PreStoredInfoListDtoRequest;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.idis.common.dto.response.PreStoredInfoContentDtoResponse;
import com.zkt.idis.common.dto.response.PreStoredInfoDtoResponse;

/**
 * 功能描述:
 *
 * @className:PreStoredInfoService
 * @projectName:20190808-idis-svc
 * @author:Dayu
 * @date: 2019/10/24 14:24
 */
public interface PreStoredInfoService {

    /**
     * 新增预存信息
     *
     * @param preStoredInfoDtoRequest
     */
    void insertPreStoredInfo(PreStoredInfoDtoRequest preStoredInfoDtoRequest);

    /**
     * 更新预存信息
     *
     * @param preStoredInfoDtoRequest
     */
    void updatePreStoredInfo(PreStoredInfoDtoRequest preStoredInfoDtoRequest);


    /**
     * 查询预存信息
     *
     * @param preStoredInfoId
     * @return
     */
    PreStoredInfoContentDtoResponse selectPreStoredInfoContent(Integer preStoredInfoId);


    /**
     * 查询预存信息列表
     *
     * @param preStoredInfoListDtoRequest
     * @return
     */
    PageResponse<PreStoredInfoDtoResponse> selectPreStoredInfo(PreStoredInfoListDtoRequest preStoredInfoListDtoRequest);

    /**
     * 删除预存信息
     *
     * @param preStoredInfoId
     */
    void deletePreStoredInfo(Integer preStoredInfoId);
}
