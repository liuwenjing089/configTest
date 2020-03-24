package com.andon.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.TestMould;
import com.andon.commons.Constant;
import com.andon.dao.TestMouldDao;
import com.andon.service.TestMouldService;
import com.andon.utils.DateUtils;

@Service
@Transactional
public class TestMouldServiceImpl implements TestMouldService {
    @Autowired
    private TestMouldDao testMouldDao;

	
	/**
	 * 试模列表
	 * @param TestMould 
	 * @return List
	 */	
	@Override
	public List<TestMould> getTestMouldList(String deId) throws Exception{

		return testMouldDao.getTestMouldList(deId, Constant.ACTIVE_VALID);
	}

	/**
	 * 新建试模
	 * 
	 * @param TestMould
	 */
	@Override
	public void insertTestMould(TestMould testMould) throws Exception{
		
//		if(StringUtils.isNotBlank(testMould.getPredictedTestMouldTime()) &&  StringUtils.isNotBlank(testMould.getTestMouldEndTime()) 
//				&& testMould.getTestMouldResult() != 0){
//			testMould.setCompleteness(1);
//			//testMould.setTestMouldEndTime(DateUtils.getCurrentDateMinute());
//			
//		}else{
//			testMould.setCompleteness(0);
//		}
//		
//		
//		if(testMould.getId() == 0){			
//			//新增
//			testMould.setCreateTime(DateUtils.getNowDate()); 
//			testMould.setUpdateTime(DateUtils.getNowDate());
//			testMould.setIsActive(Constant.ACTIVE_VALID);
//			testMouldDao.insertTestMould(testMould);
//			
//		}else{
//			//修改
//			testMould.setUpdateTime(DateUtils.getNowDate());
//			testMould.setIsActive(Constant.ACTIVE_VALID);
//			testMouldDao.updateTestMould(testMould);
//		}
		
		
		//新增
		testMould.setCompleteness(1);
		testMould.setCreateTime(DateUtils.getNowDate()); 
		testMould.setUpdateTime(DateUtils.getNowDate());
		testMould.setIsActive(Constant.ACTIVE_VALID);
		testMouldDao.insertTestMould(testMould);
		
	}

	/**
	 * 根据模具表Id查询该条记录所有信息
	 * @param id
	 */
	@Override
	public TestMould selectTestMouldById(String deId) throws Exception{

		return testMouldDao.selectTestMouldById(deId, Constant.ACTIVE_VALID);
	}

	/**
	 * 查询试模记录中是否已经存在成功的记录
	 * 
	 * @param TestMould
	 */
	@Override
	public int selectTestMouldToSucceedById(String deId) {

		return testMouldDao.selectTestMouldToSucceedById(deId, Constant.ACTIVE_VALID);
	}
	

}
