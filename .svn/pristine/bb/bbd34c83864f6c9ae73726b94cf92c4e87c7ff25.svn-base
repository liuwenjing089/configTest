package com.andon.service;

import com.andon.bean.SpotDetail;
import com.andon.bean.dto.SpotDetailEquip;
import com.andon.bean.dto.SpotDetailMould;

import java.util.List;

public interface SpotDetailService {
    List<SpotDetailMould> getSpotDetailMould(SpotDetailMould mould);
    int getSpotDetailMouldCount(SpotDetailMould mould);
    List<SpotDetailEquip> getSpotDetailEquip(SpotDetailEquip equip);
    int getSpotDetailEquipCount(SpotDetailEquip equip);

    //更新状态
    public void updateState(int id,String spotMan);
    public void updateConfirmState(SpotDetail spotDetail);

    public SpotDetail getSpotDetailById(int id);

    //日历
    List<SpotDetailMould> getDateSpotDetailMould(SpotDetailMould mould);
    int getDateSpotDetailMouldCount(SpotDetailMould mould);
    List<SpotDetailEquip> getDateSpotDetailEquip(SpotDetailEquip equip);
    int getDateSpotDetailEquipCount(SpotDetailEquip equip);
}
