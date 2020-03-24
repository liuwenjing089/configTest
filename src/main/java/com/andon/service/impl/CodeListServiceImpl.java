package com.andon.service.impl;

import com.andon.bean.CodeList;
import com.andon.dao.CodeListDao;
import com.andon.service.CodeListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CodeListServiceImpl implements CodeListService {
    @Autowired
    private CodeListDao codeListDao;
    @Override
    public CodeList getById(int id) {
        return codeListDao.selectById(id);
    }

    //查询上一级
    @Override
    public List<CodeList> getByType(int codeType,int parentId) throws Exception {
        return codeListDao.selectByType(codeType,parentId);
    }

	@Override
	public List<CodeList> getByType2(int codeType, int parentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
