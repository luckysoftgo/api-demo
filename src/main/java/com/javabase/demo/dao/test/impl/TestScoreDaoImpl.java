package com.javabase.demo.dao.test.impl;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

import org.springframework.stereotype.Repository;
import com.application.base.core.apisupport.impl.MultiCacheStrutsBaseDaoImpl;

import com.application.base.core.datasource.param.CustomSql;
import com.application.base.core.datasource.param.ESQLOperator;
import com.application.base.core.datasource.param.Param;
import com.application.base.core.datasource.param.ParamBuilder;
import com.application.base.core.datasource.param.SqlCreator;
import com.application.base.core.exception.BusinessException;
import com.application.base.utils.page.Pagination;
import com.application.base.core.utils.CommonBeanUtils;

import com.javabase.demo.entity.test.TestScore;
import com.javabase.demo.dao.test.TestScoreDao;

/**
 * TestScoreDaoImpl实现
 * @author 孤狼
 */
@Repository("testScoreDao")
public class TestScoreDaoImpl extends MultiCacheStrutsBaseDaoImpl<TestScore> implements TestScoreDao {
	
	
	@Override
	public synchronized TestScore saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestScore testScore = CommonBeanUtils.transMap2BasePO(param, TestScore.class);
		return factory.getCacheWriteDataSession().saveObject(TestScore.class,testScore);
	}
	


	@Override
	public synchronized TestScore saveObject(TestScore testScore,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheWriteDataSession().saveObject(TestScore.class,testScore);
	}		

	
	
	@Override
	public synchronized boolean saveBatchObject(List<TestScore> testScores,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheWriteDataSession().saveBatchObject(TestScore.class,testScores);
	}	

	
	
	@Override
	public synchronized TestScore getObjectById(Object objId,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().querySingleResultById(TestScore.class,"scoreId", objId);
	}		


	
    @Override
	public synchronized TestScore getObjectByUUId(String uuid,String factoryTag) throws BusinessException {
    	//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().querySingleResultByUUId(TestScore.class, uuid);
	}

	
	
	
	@Override
	public synchronized int updateObjectById(Map<String, Object> param, TestScore object,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestScore testScore = new TestScore();
		testScore.setScoreId(object.getScoreId());
		testScore.setCreateTime(object.getCreateTime());
		testScore = CommonBeanUtils.transMap2BasePO(param, testScore);
		return factory.getCacheWriteDataSession().updateObjectById(TestScore.class, testScore);
	}		
	
	
    @Override
	public synchronized int updateObjectByUUId(Map<String, Object> param, TestScore object,String factoryTag) throws BusinessException {
    	//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestScore testScore = new TestScore();
		testScore.setUuid(object.getUuid());
		testScore.setCreateTime(object.getCreateTime());
		testScore = CommonBeanUtils.transMap2BasePO(param, testScore);
		return factory.getCacheWriteDataSession().updateObjectByUUId(TestScore.class, testScore);
	}
	
	
	@Override
	public synchronized int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		//根据实际情况填写 ; SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestScore.class, params, where);
	}		

	
	@Override
	public synchronized int updateObjectByWhere(TestScore testScore,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam();
		//根据实际情况填写. //whereStr //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestScore.class, params, where);
	}		
	
	
	@Override
	public synchronized int deleteObjectById(Object objId,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheWriteDataSession().physicalDelete(TestScore.class,"scoreId", objId);
	}		

	
    @Override
	public synchronized int deleteObjectByUUId(String uuid,String factoryTag) throws BusinessException {
    	//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
        //根据实际情况填写.
		CustomSql where = SqlCreator.where().cloumn("uuid").operator(ESQLOperator.EQ).value(uuid);
		return factory.getCacheWriteDataSession().physicalWhereDelete(TestScore.class,where);
	}


	@Override
	public synchronized int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己设置访问的数据库.
		factory.setFactoryTag(factoryTag);
		//根据实际情况填写要查的列和对应的值.
		//根据实际情况填写. //whereStr //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().physicalWhereDelete(TestScore.class, where);
	}		


	
	
	@Override
	public synchronized int deleteObjectByWhere(TestScore testScore,String factoryTag) throws BusinessException {
		//根据实际情况自己设置访问的数据库.
		factory.setFactoryTag(factoryTag);
		//根据实际情况填写要查的列和对应的值. //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().physicalWhereDelete(TestScore.class, where);
	}	

	
	@Override
	public synchronized Pagination<TestScore> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(TestScore.class, params, pageNo, pageSize, factoryTag);
	}	


	@Override
	public synchronized Pagination<TestScore> paginationObjects(TestScore testScore, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testScore));
		return queryClassPagination(TestScore.class, params, pageNo, pageSize, factoryTag);
	}		
	
	
	@Override
	public synchronized TestScore findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(TestScore.class, params);
	}		

	
	@Override
	public synchronized TestScore findObjectByPros(TestScore testScore,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testScore));
		return factory.getCacheReadDataSession().querySingleResultByParams(TestScore.class, params);
	}		

	
	
	@Override
	public synchronized List<TestScore> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(TestScore.class, params);
	}	
	
	
	@Override
	public synchronized List<TestScore> findObjectListByPros(TestScore testScore,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testScore));
		return factory.getCacheReadDataSession().queryListResult(TestScore.class, params);
	}		
	
	
	
	@Override
	public synchronized int getObjectCount(String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().queryListResultCount(TestScore.class, ParamBuilder.getInstance().getParam());
	}	

	
	@Override
	public synchronized int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(TestScore.class, params);
	}		

	
	@Override
	public synchronized int getObjectCount(TestScore testScore,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testScore));
		return factory.getCacheReadDataSession().queryListResultCount(TestScore.class, params);
	}


}
