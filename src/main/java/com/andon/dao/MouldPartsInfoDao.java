package com.andon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.andon.bean.MouldFaultParts;
import com.andon.bean.MouldPartsInfo;

public interface MouldPartsInfoDao {

    //获取部品分类
    public List<MouldPartsInfo> selectMouldPartsInfoToPartsType(MouldPartsInfo mouldPartsInfo);
    //根据部品分类获取部品名称
    public List<MouldPartsInfo> selectMouldPartsInfoToPartsName(MouldPartsInfo mouldPartsInfo);
    //根据部品分类和部品名称获取部品品番或型号
    public List<MouldPartsInfo> selectMouldPartsInfoToPartsNum(MouldPartsInfo mouldPartsInfo);
    
    //根据报修编号获取模具损坏部件维修详情
    public List<MouldFaultParts> selectMouldFaultPartsDetail(@Param("reId")String reId);

}
