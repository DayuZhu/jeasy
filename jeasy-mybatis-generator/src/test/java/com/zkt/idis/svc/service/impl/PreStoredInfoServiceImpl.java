package com.zkt.idis.svc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import com.zkt.idis.common.constant.PreStoredInfoConstant;
import com.zkt.idis.common.dto.request.PreStoredInfoDtoRequest;
import com.zkt.idis.common.dto.request.PreStoredInfoListDtoRequest;
import com.zkt.idis.common.dto.response.PageResponse;
import com.zkt.idis.common.dto.response.PreStoredInfoContentDtoResponse;
import com.zkt.idis.common.dto.response.PreStoredInfoDtoResponse;
import com.zkt.idis.common.framework.ex.IdisBussinessException;
import com.zkt.idis.common.response.PreStoredInfoResultCode;
import com.zkt.idis.svc.dao.db.auto.PreStoredInfoMapper;
import com.zkt.idis.svc.model.PreStoredInfo;
import com.zkt.idis.svc.model.PreStoredInfoExample;
import com.zkt.idis.svc.service.PreStoredInfoService;
import com.zkt.log.LogUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能描述:
 *
 * @className:PreStoredInfoServiceImpl
 * @projectName:20190808-idis-svc
 * @author:Dayu
 * @date: 2019/10/24 14:25
 */
@Service
public class PreStoredInfoServiceImpl implements PreStoredInfoService {

    @Autowired
    private PreStoredInfoMapper preStoredInfoMapper;

    @Override
    public void insertPreStoredInfo(PreStoredInfoDtoRequest preStoredInfoDtoRequest) {
        LogUtil.logApplicationInfo("进入创建预存信息服务请求参数" + preStoredInfoDtoRequest.toString());

        PreStoredInfoExample preStoredInfoExample = new PreStoredInfoExample();
        preStoredInfoExample.createCriteria().andTitleEqualTo(preStoredInfoDtoRequest.getTitle());
        List<PreStoredInfo> preStoredInfos = preStoredInfoMapper.selectByExample(preStoredInfoExample);
        if (CollectionUtils.isNotEmpty(preStoredInfos)) {
            LogUtil.logApplicationWarn("进入创建预存信息服务已存在相同主题的草稿");
            throw new IdisBussinessException(PreStoredInfoResultCode.PRE_STORED_INFO_EXISTED);
        }
        Date currentTime = new Date();
        PreStoredInfo preStoredInfo = new PreStoredInfo();
        preStoredInfo.setRelationType(PreStoredInfoConstant.RelationType.RELATIONTYPE_1);
        preStoredInfo.setRelationTypeDesc(PreStoredInfoConstant.RelationType.RELATIONTYPE_1_DESC);
        preStoredInfo.setTitle(preStoredInfoDtoRequest.getTitle());
        preStoredInfo.setCreateTime(currentTime);
        preStoredInfo.setUpdateTime(currentTime);
        preStoredInfo.setUserId(preStoredInfoDtoRequest.getUserId());
        preStoredInfo.setUserName(preStoredInfoDtoRequest.getUserName());
        preStoredInfo.setContent(preStoredInfoDtoRequest.getContent());
        preStoredInfoMapper.insertSelective(preStoredInfo);

    }

    @Override
    public void updatePreStoredInfo(PreStoredInfoDtoRequest preStoredInfoDtoRequest) {
        LogUtil.logApplicationInfo("进入更新预存信息服务请求参数" + preStoredInfoDtoRequest.toString());
        Date currentTime = new Date();
        PreStoredInfo preStoredInfo = new PreStoredInfo();
        preStoredInfo.setPreStoredInfoId(preStoredInfoDtoRequest.getPreStoredInfoId());
        preStoredInfo.setTitle(preStoredInfoDtoRequest.getTitle());
        preStoredInfo.setUpdateTime(currentTime);
        preStoredInfo.setUserId(preStoredInfoDtoRequest.getUserId());
        preStoredInfo.setUserName(preStoredInfoDtoRequest.getUserName());
        preStoredInfo.setContent(preStoredInfoDtoRequest.getContent());
        preStoredInfoMapper.updateByPrimaryKeySelective(preStoredInfo);
    }

    @Override
    public PreStoredInfoContentDtoResponse selectPreStoredInfoContent(Integer preStoredInfoId) {
        LogUtil.logApplicationInfo("进入查询预存信息服务请求参数preStoredInfoId=" + preStoredInfoId);
        PreStoredInfoContentDtoResponse preStoredInfoContentDtoResponse = new PreStoredInfoContentDtoResponse();
        PreStoredInfo preStoredInfo = preStoredInfoMapper.selectByPrimaryKey(preStoredInfoId);
        if (null == preStoredInfo) {
            return preStoredInfoContentDtoResponse;
        }

        preStoredInfoContentDtoResponse.setPreStoredInfoId(preStoredInfo.getPreStoredInfoId());
        preStoredInfoContentDtoResponse.setContent(preStoredInfo.getContent());
        preStoredInfoContentDtoResponse.setTitle(preStoredInfo.getTitle());
        return preStoredInfoContentDtoResponse;
    }

    @Override
    public PageResponse<PreStoredInfoDtoResponse> selectPreStoredInfo(PreStoredInfoListDtoRequest preStoredInfoListDtoRequest) {
        LogUtil.logApplicationInfo("进入查询预存信息列表服务请求参数" + preStoredInfoListDtoRequest.toString());
        PreStoredInfoExample preStoredInfoExample = new PreStoredInfoExample();
        preStoredInfoExample.setOrderByClause("pre_stored_info_id desc");
        PreStoredInfoExample.Criteria criteria = preStoredInfoExample.createCriteria();
        if (StringUtils.isNotBlank(preStoredInfoListDtoRequest.getTitle())) {
            criteria.andTitleLike("%" + preStoredInfoListDtoRequest.getTitle() + "%");
        }
        PageHelper.startPage(preStoredInfoListDtoRequest.getPageIndex(), preStoredInfoListDtoRequest.getPageSize());
        List<PreStoredInfo> preStoredInfos = preStoredInfoMapper.selectByExample(preStoredInfoExample);
        PageSerializable<PreStoredInfo> pageInfo = PageSerializable.of(preStoredInfos);
        PageResponse<PreStoredInfoDtoResponse> response = new PageResponse<>();
        response.setTotal(pageInfo.getTotal());
        List<PreStoredInfoDtoResponse> list = new ArrayList<>();
        response.setList(list);
        preStoredInfos.forEach(preStoredInfo -> {
            PreStoredInfoDtoResponse preStoredInfoDtoResponse = new PreStoredInfoDtoResponse();
            preStoredInfoDtoResponse.setPreStoredInfoId(preStoredInfo.getPreStoredInfoId());
            preStoredInfoDtoResponse.setTitle(preStoredInfo.getTitle());
            preStoredInfoDtoResponse.setCreateTime(preStoredInfo.getCreateTime());
            preStoredInfoDtoResponse.setUpdateTime(preStoredInfo.getUpdateTime());
            preStoredInfoDtoResponse.setUserId(preStoredInfo.getUserId());
            preStoredInfoDtoResponse.setUserName(preStoredInfo.getUserName());
            list.add(preStoredInfoDtoResponse);
        });
        return response;
    }

}
