package com.andon.dao;

import com.andon.bean.Parts;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartsDao {
    //添加维修用部品
    public void insert(List<Parts> parts);
    //获取维修用部品
    public List<Parts> selectParts(@Param("reId")int reId);
    //删除维修用部品
    public void delete(@Param("reId")int reId);
}
