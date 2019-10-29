package com.zkt.idis.svc.dao.db.auto;

import com.zkt.idis.svc.model.PreStoredInfo;
import com.zkt.idis.svc.model.PreStoredInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PreStoredInfoMapper {
    long countByExample(PreStoredInfoExample example);

    int deleteByExample(PreStoredInfoExample example);

    int deleteByPrimaryKey(Integer preStoredInfoId);

    int insert(PreStoredInfo record);

    int insertSelective(PreStoredInfo record);

    List<PreStoredInfo> selectByExampleWithBLOBs(PreStoredInfoExample example);

    List<PreStoredInfo> selectByExample(PreStoredInfoExample example);

    PreStoredInfo selectByPrimaryKey(Integer preStoredInfoId);

    int updateByExampleSelective(@Param("record") PreStoredInfo record, @Param("example") PreStoredInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") PreStoredInfo record, @Param("example") PreStoredInfoExample example);

    int updateByExample(@Param("record") PreStoredInfo record, @Param("example") PreStoredInfoExample example);

    int updateByPrimaryKeySelective(PreStoredInfo record);

    int updateByPrimaryKeyWithBLOBs(PreStoredInfo record);

    int updateByPrimaryKey(PreStoredInfo record);
}