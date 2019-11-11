package com.zkt.idis.api.controller.merchant;

//import com.zkt.business.annotation.NotLogin;
import com.zkt.common.model.AjaxResult;
import com.zkt.idis.api.request.PreStoredInfoListRequest;
import com.zkt.idis.api.request.PreStoredInfoRequest;
import com.zkt.idis.api.response.PreStoredInfoContentResponse;
import com.zkt.idis.api.response.PreStoredInfoResponse;
import com.zkt.idis.api.service.merchant.PreStoredInfoApiService;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.log.LogUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 功能描述:
 *
 * @className:PreStoredInfoController
 * @projectName:20190808-idis-svc
 * @author:Dayu
 * @date: 2019/10/24 14:18
 */
@RestController
@RequestMapping(value = "/mis/pre/stored")
@Api(tags = "MIS-预存信息控制类")
@Validated
public class PreStoredInfoApiMisController {

    @Autowired
    private PreStoredInfoApiService preStoredApiInfoService;


    @ApiOperation("创建或更新预存信息")
    @PostMapping("/createOrUpdate")
    public AjaxResult creationOrModify(@RequestBody @Valid PreStoredInfoRequest preStoredInfoRequest) {
        LogUtil.logApplicationInfo("创建或更新预存信息API请求参数" + preStoredInfoRequest.toString());
        AjaxResult result = new AjaxResult();
        preStoredApiInfoService.createOrModifyPreStoredInfo(preStoredInfoRequest);
        result.setMessage("操作成功");
        return result;
    }


    @ApiOperation("查询预存信息")
    @GetMapping("/info")
    public AjaxResult<PreStoredInfoContentResponse> queryInfo(@NotNull @RequestParam(name = "preStoredInfoId") Integer preStoredInfoId) {
        LogUtil.logApplicationInfo("查询预存信息API请求参数preStoredInfoId=" + preStoredInfoId);
        AjaxResult<PreStoredInfoContentResponse> result = new AjaxResult<>();
        PreStoredInfoContentResponse preStoredInfoContentResponse =
                preStoredApiInfoService.queryPreStoredInfoContent(preStoredInfoId);
        result.setMessage("操作成功");
        result.setData(preStoredInfoContentResponse);
        return result;
    }


    @ApiOperation("查询预存信息列表")
    @PostMapping("/info/list")
    public AjaxResult<PageResponse<PreStoredInfoResponse>> queryInfoList(@RequestBody @Valid PreStoredInfoListRequest preStoredInfoListRequest) {
        LogUtil.logApplicationInfo("查询预存信息列表API请求参数" + preStoredInfoListRequest.toString());
        AjaxResult<PageResponse<PreStoredInfoResponse>> result = new AjaxResult<>();
        PageResponse<PreStoredInfoResponse> response = preStoredApiInfoService.queryPreStoredInfo(preStoredInfoListRequest);
        result.setMessage("操作成功");
        result.setData(response);
        return result;
    }

}
