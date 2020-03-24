package com.andon.service.impl;

import com.andon.bean.MouldFaultParts;
import com.andon.dao.MouldFaultPartsDao;
import com.andon.dao.MouldPartsInfoDao;
import com.andon.service.MouldFaultPartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MouldFaultPartsServiceImpl implements MouldFaultPartsService {
    @Autowired
    private MouldFaultPartsDao mouldFaultPartsDao;
    @Autowired
    private MouldPartsInfoDao mouldPartsInfoDao;
    
    @Override
    public void add(List<MouldFaultParts> partsList) {
        mouldFaultPartsDao.insert(partsList);
    }

    @Override
    public List<MouldFaultParts> selectParts(String reId) {
        return mouldPartsInfoDao.selectMouldFaultPartsDetail(String.valueOf(reId));
    }

    @Override
    public void delete(int reId) {
        mouldFaultPartsDao.delete(String.valueOf(reId));
    }
}
