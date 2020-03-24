package com.andon.dao;

import com.andon.bean.CodeList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CodeListDao {
    public CodeList selectById(@Param("id")int id);
    public List<CodeList> selectByType(@Param("codeType")int codeType,@Param("parentId")int parentId );
}
