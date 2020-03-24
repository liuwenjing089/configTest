package com.andon.dao;

import com.andon.bean.Picture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PictureDao {
    //批量插入
    public void insert(List<Picture> pictures);
    //删除图片
    public void delete(@Param("uuid")String uuid);
    //获取图片
    public List<Picture> selectPicture(List<String> stringList);
    //批量删除图片
    public void batchDeletePicture(List<String> uuids);
}
