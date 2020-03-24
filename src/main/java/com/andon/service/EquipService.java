package com.andon.service;

import com.andon.bean.Equip;
import com.andon.bean.dto.EquipSeeOutput;

import java.util.List;

public interface EquipService {
    //添加
    void add(Equip equip);
    //一览
    List<Equip> getEquip(int beginIndex, int pageSize);
    int getEquipCount();
    List<Equip> getEquipByFirm(int factory,String equipName,String equipDescription,String useBeginTime,String equipModel,String standard,int beginIndex, int pageSize);
    int getEquipByFirmCount(int factory,String equipName,String equipDescription,String useBeginTime,String equipModel,String standard);
    //详细信息
    Equip getByid(int id) ;
    //修改
    void update(Equip equip) throws Exception;
    //删除
    void deleteEq(int id);
    //删除生产线清空lineId
    void updateEqLine(int id,int lineId);
    List<Equip> getEqBylineID(int lineId);
    void deleteEqLine(List<Equip> equip);
    EquipSeeOutput getEquipSee(int id);
}
