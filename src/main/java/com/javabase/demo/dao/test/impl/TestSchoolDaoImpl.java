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

import com.javabase.demo.entity.test.TestSchool;
import com.javabase.demo.dao.test.TestSchoolDao;

/**
 * TestSchoolDaoImpl实现
 * @author 孤狼
 */
@Repository("testSchoolDao")
public class TestSchoolDaoImpl extends MultiCacheStrutsBaseDaoImpl<TestSchool> implements TestSchoolDao {
	
	
	@Override
	public synchronized TestSchool saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestSchool testSchool = CommonBeanUtils.transMap2BasePO(param, TestSchool.class);
		return factory.getCacheWriteDataSession().saveObject(TestSchool.class,testSchool);
	}
	


	@Override
	public synchronized TestSchool saveObject(TestSchool testSchool,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheWriteDataSession().saveObject(TestSchool.class,testSchool);
	}		

	
	
	@Override
	public synchronized boolean saveBatchObject(List<TestSchool> testSchools,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheWriteDataSession().saveBatchObject(TestSchool.class,testSchools);
	}	

	
	
	@Override
	public synchronized TestSchool getObjectById(Object objId,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().querySingleResultById(TestSchool.class,"schoolId", objId);
	}		


	
    @Override
	public synchronized TestSchool getObjectByUUId(String uuid,String factoryTag) throws BusinessException {
    	//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().querySingleResultByUUId(TestSchool.class, uuid);
	}

	
	
	
	@Override
	public synchronized int updateObjectById(Map<String, Object> param, TestSchool object,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestSchool testSchool = new TestSchool();
		testSchool.setSchoolId(object.getSchoolId());
		testSchool.setCreateTime(object.getCreateTime());
		testSchool = CommonBeanUtils.transMap2BasePO(param, testSchool);
		return factory.getCacheWriteDataSession().updateObjectById(TestSchool.class, testSchool);
	}		
	
	
    @Override
	public synchronized int updateObjectByUUId(Map<String, Object> param, TestSchool object,String factoryTag) throws BusinessException {
    	//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestSchool testSchool = new TestSchool();
		testSchool.setUuid(object.getUuid());
		testSchool.setCreateTime(object.getCreateTime());
		testSchool = CommonBeanUtils.transMap2BasePO(param, testSchool);
		return factory.getCacheWriteDataSession().updateObjectByUUId(TestSchool.class, testSchool);
	}
	
	
	@Override
	public synchronized int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		//根据实际情况填写 ; SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestSchool.class, params, where);
	}		

	
	@Override
	public synchronized int updateObjectByWhere(TestSchool testSchool,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam();
		//根据实际情况填写. //whereStr //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestSchool.class, params, where);
	}		
	
	
	@Override
	public synchronized int deleteObjectById(Object objId,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("schoolId",objId));
		return factory.getCacheWriteDataSession().logicDelete(TestSchool.class, param);
	}		

	
    @Override
	public synchronized int deleteObjectByUUId(String uuid,String factoryTag) throws BusinessException {
    	//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("uuid",uuid));
		return factory.getCacheWriteDataSession().logicDelete(TestSchool.class, param);
	}


	@Override
	public synchronized int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己设置访问的数据库.
		factory.setFactoryTag(factoryTag);
		//根据实际情况填写要查的列和对应的值.
		//根据实际情况填写. //whereStr //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().logicWhereDelete(TestSchool.class, where);
	}		


	
	
	@Override
	public synchronized int deleteObjectByWhere(TestSchool testSchool,String factoryTag) throws BusinessException {
		//根据实际情况自己设置访问的数据库.
		factory.setFactoryTag(factoryTag);
		//根据实际情况填写要查的列和对应的值. //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().logicWhereDelete(TestSchool.class, where);
	}	

	
	@Override
	public synchronized Pagination<TestSchool> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(TestSchool.class, params, pageNo, pageSize, factoryTag);
	}	


	@Override
	public synchronized Pagination<TestSchool> paginationObjects(TestSchool testSchool, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testSchool));
		return queryClassPagination(TestSchool.class, params, pageNo, pageSize, factoryTag);
	}		
	
	
	@Override
	public synchronized TestSchool findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(TestSchool.class, params);
	}		

	
	@Override
	public synchronized TestSchool findObjectByPros(TestSchool testSchool,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testSchool));
		return factory.getCacheReadDataSession().querySingleResultByParams(TestSchool.class, params);
	}		

	
	
	@Override
	public synchronized List<TestSchool> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(TestSchool.class, params);
	}	
	
	
	@Override
	public synchronized List<TestSchool> findObjectListByPros(TestSchool testSchool,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testSchool));
		return factory.getCacheReadDataSession().queryListResult(TestSchool.class, params);
	}		
	
	
	
	@Override
	public synchronized int getObjectCount(String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().queryListResultCount(TestSchool.class, ParamBuilder.getInstance().getParam());
	}	

	
	@Override
	public synchronized int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(TestSchool.class, params);
	}		

	
	@Override
	public synchronized int getObjectCount(TestSchool testSchool,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testSchool));
		return factory.getCacheReadDataSession().queryListResultCount(TestSchool.class, params);
	}


}
