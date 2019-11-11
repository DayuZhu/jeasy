package com.zkt.idis.api.service.merchant;

import com.zkt.idis.api.request.PreStoredInfoListRequest;
import com.zkt.idis.api.request.PreStoredInfoRequest;
import com.zkt.idis.api.response.PreStoredInfoContentResponse;
import com.zkt.idis.api.response.PreStoredInfoResponse;
import com.zkt.idis.common.dto.response.PageResponse;

/**
 * 功能描述:
 *
 * @className:PreStoredInfoService
 * @projectName:20190808-idis-api
 * @author:Dayu
 * @date: 2019/10/24 15:48
 */
public interface PreStoredInfoApiService {

    /**
     * 新增或者更新预存信息
     *
     * @param preStoredInfoRequest
     */
    void createOrModifyPreStoredInfo(PreStoredInfoRequest preStoredInfoRequest);


    /**
     * 查询预存信息
     *
     * @param preStoredInfoId
     * @return
     */
    PreStoredInfoContentResponse queryPreStoredInfoContent(Integer preStoredInfoId);


    /**
     * 查询预存信息列表
     *
     * @param preStoredInfoListRequest
     * @return
     */
    PageResponse<PreStoredInfoResponse> queryPreStoredInfo(PreStoredInfoListRequest preStoredInfoListRequest);

}
