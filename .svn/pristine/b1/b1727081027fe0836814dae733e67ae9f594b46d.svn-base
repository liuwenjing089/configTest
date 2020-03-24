package com.andon.service;

import java.util.Date;
import java.util.List;

import com.andon.bean.Beltline;

public interface BeltlineService {
    void add(Beltline beltline);
    List<Beltline> getLine(int beginIndex, int pageSize, String factory);
    int getLineCount(String factory);
    List<Beltline> getLineByFirm(int lineType,String beltlineName,String beltlineDescription,int beginIndex, int pageSize, String factory);
    int getLineByFirmCount(int lineType, String beltlineName,String beltlineDescription, String factory);
    void updateLine(int id, int factory, String beltlineName, String beltlineDescription, String updateUser, Date updateTime,int lineType);
    void deleteLine(int id);
    Beltline getLineByid(int id);
    
    //查询模具的成型机
    public List<Beltline> selectLineByMould();
    //查询设备生产线
    public List<Beltline> selectEquipLine(String factory) throws Exception;
}
