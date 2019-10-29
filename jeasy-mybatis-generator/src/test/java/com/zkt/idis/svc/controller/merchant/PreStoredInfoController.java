package com.zkt.idis.svc.controller.merchant;

import com.zkt.common.model.AjaxResult;
import com.zkt.idis.common.dto.request.PreStoredInfoDtoRequest;
import com.zkt.idis.common.dto.request.PreStoredInfoListDtoRequest;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.idis.common.dto.response.PreStoredInfoContentDtoResponse;
import com.zkt.idis.common.dto.response.PreStoredInfoDtoResponse;
import com.zkt.idis.svc.service.PreStoredInfoService;
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
@Api(tags = "预存信息控制类")
@Validated
public class PreStoredInfoController {

    @Autowired
    private PreStoredInfoService preStoredInfoService;

    @ApiOperation("创建或更新预存信息")
    @PostMapping("/createOrModify")
    public AjaxResult creationOrModify(@RequestBody @Valid PreStoredInfoDtoRequest preStoredInfoDtoRequest) {
        LogUtil.logApplicationInfo("创建或更新预存信息请求参数" + preStoredInfoDtoRequest.toString());
        AjaxResult result = new AjaxResult();
        if (null != preStoredInfoDtoRequest.getPreStoredInfoId()) {
            preStoredInfoService.updatePreStoredInfo(preStoredInfoDtoRequest);
        } else {
            preStoredInfoService.insertPreStoredInfo(preStoredInfoDtoRequest);
        }
        result.setMessage("操作成功");
        return result;
    }

    @ApiOperation("查询预存信息")
    @GetMapping("/query/info")
    public AjaxResult<PreStoredInfoContentDtoResponse> queryInfo(@NotNull @RequestParam(name = "preStoredInfoId") Integer preStoredInfoId) {
        LogUtil.logApplicationInfo("查询预存信息请求参数preStoredInfoId=" + preStoredInfoId);
        AjaxResult<PreStoredInfoContentDtoResponse> result = new AjaxResult<>();
        PreStoredInfoContentDtoResponse preStoredInfoContentDtoResponse =
                preStoredInfoService.selectPreStoredInfoContent(preStoredInfoId);
        result.setMessage("操作成功");
        result.setData(preStoredInfoContentDtoResponse);
        return result;
    }

    @ApiOperation("查询预存信息列表")
    @PostMapping("/query/info/list")
    public AjaxResult<PageResponse<PreStoredInfoDtoResponse>> queryInfoList(@RequestBody @Valid PreStoredInfoListDtoRequest preStoredInfoListDtoRequest) {
        LogUtil.logApplicationInfo("查询预存信息列表请求参数" + preStoredInfoListDtoRequest.toString());
        AjaxResult<PageResponse<PreStoredInfoDtoResponse>> result = new AjaxResult<>();
        PageResponse<PreStoredInfoDtoResponse> response = preStoredInfoService.selectPreStoredInfo(preStoredInfoListDtoRequest);
        result.setMessage("操作成功");
        result.setData(response);
        return result;
    }

    @ApiOperation("删除预存信息")
    @GetMapping("/del/info")
    public AjaxResult deleteInfo(@NotNull @RequestParam(name = "preStoredInfoId") Integer preStoredInfoId) {
        LogUtil.logApplicationInfo("删除预存信息请求参数preStoredInfoId=" + preStoredInfoId);
        AjaxResult result = new AjaxResult<>();
        preStoredInfoService.deletePreStoredInfo(preStoredInfoId);
        result.setMessage("操作成功");
        return result;
    }
}
