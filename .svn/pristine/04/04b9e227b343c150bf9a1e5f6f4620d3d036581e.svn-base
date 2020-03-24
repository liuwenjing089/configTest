package com.andon.service.impl;

import com.andon.bean.Parts;
import com.andon.dao.PartsDao;
import com.andon.service.PartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartsServiceImpl implements PartsService {
    @Autowired
    private PartsDao partsDao;
    @Override
    public void add(List<Parts> partsList) {
        if(partsList!= null && partsList.size()>0){
            partsDao.insert(partsList);
        }
    }

    @Override
    public List<Parts> selectParts(int reId) {
        return partsDao.selectParts(reId);
    }

    @Override
    public void delete(int reId) {
        partsDao.delete(reId);
    }
}
