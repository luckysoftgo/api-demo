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

import com.javabase.demo.entity.test.TestTeacher;
import com.javabase.demo.dao.test.TestTeacherDao;

/**
 * TestTeacherDaoImpl实现
 * @author 孤狼
 */
@Repository("testTeacherDao")
public class TestTeacherDaoImpl extends MultiCacheStrutsBaseDaoImpl<TestTeacher> implements TestTeacherDao {
	
	
	@Override
	public synchronized TestTeacher saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestTeacher testTeacher = CommonBeanUtils.transMap2BasePO(param, TestTeacher.class);
		return factory.getCacheWriteDataSession().saveObject(TestTeacher.class,testTeacher);
	}
	


	@Override
	public synchronized TestTeacher saveObject(TestTeacher testTeacher,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheWriteDataSession().saveObject(TestTeacher.class,testTeacher);
	}		

	
	
	@Override
	public synchronized boolean saveBatchObject(List<TestTeacher> testTeachers,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheWriteDataSession().saveBatchObject(TestTeacher.class,testTeachers);
	}	

	
	
	@Override
	public synchronized TestTeacher getObjectById(Object objId,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().querySingleResultById(TestTeacher.class,"id", objId);
	}		


	
    @Override
	public synchronized TestTeacher getObjectByUUId(String uuid,String factoryTag) throws BusinessException {
    	//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().querySingleResultByUUId(TestTeacher.class, uuid);
	}

	
	
	
	@Override
	public synchronized int updateObjectById(Map<String, Object> param, TestTeacher object,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestTeacher testTeacher = new TestTeacher();
		testTeacher.setId(object.getId());
		testTeacher.setCreateTime(object.getCreateTime());
		testTeacher = CommonBeanUtils.transMap2BasePO(param, testTeacher);
		return factory.getCacheWriteDataSession().updateObjectById(TestTeacher.class, testTeacher);
	}		
	
	
    @Override
	public synchronized int updateObjectByUUId(Map<String, Object> param, TestTeacher object,String factoryTag) throws BusinessException {
    	//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestTeacher testTeacher = new TestTeacher();
		testTeacher.setUuid(object.getUuid());
		testTeacher.setCreateTime(object.getCreateTime());
		testTeacher = CommonBeanUtils.transMap2BasePO(param, testTeacher);
		return factory.getCacheWriteDataSession().updateObjectByUUId(TestTeacher.class, testTeacher);
	}
	
	
	@Override
	public synchronized int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		//根据实际情况填写 ; SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestTeacher.class, params, where);
	}		

	
	@Override
	public synchronized int updateObjectByWhere(TestTeacher testTeacher,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam();
		//根据实际情况填写. //whereStr //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestTeacher.class, params, where);
	}		
	
	
	@Override
	public synchronized int deleteObjectById(Object objId,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("id",objId));
		return factory.getCacheWriteDataSession().logicDelete(TestTeacher.class, param);
	}		

	
    @Override
	public synchronized int deleteObjectByUUId(String uuid,String factoryTag) throws BusinessException {
    	//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("uuid",uuid));
		return factory.getCacheWriteDataSession().logicDelete(TestTeacher.class, param);
	}


	@Override
	public synchronized int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己设置访问的数据库.
		factory.setFactoryTag(factoryTag);
		//根据实际情况填写要查的列和对应的值.
		//根据实际情况填写. //whereStr //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().logicWhereDelete(TestTeacher.class, where);
	}		


	
	
	@Override
	public synchronized int deleteObjectByWhere(TestTeacher testTeacher,String factoryTag) throws BusinessException {
		//根据实际情况自己设置访问的数据库.
		factory.setFactoryTag(factoryTag);
		//根据实际情况填写要查的列和对应的值. //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().logicWhereDelete(TestTeacher.class, where);
	}	

	
	@Override
	public synchronized Pagination<TestTeacher> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(TestTeacher.class, params, pageNo, pageSize, factoryTag);
	}	


	@Override
	public synchronized Pagination<TestTeacher> paginationObjects(TestTeacher testTeacher, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testTeacher));
		return queryClassPagination(TestTeacher.class, params, pageNo, pageSize, factoryTag);
	}		
	
	
	@Override
	public synchronized TestTeacher findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(TestTeacher.class, params);
	}		

	
	@Override
	public synchronized TestTeacher findObjectByPros(TestTeacher testTeacher,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testTeacher));
		return factory.getCacheReadDataSession().querySingleResultByParams(TestTeacher.class, params);
	}		

	
	
	@Override
	public synchronized List<TestTeacher> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(TestTeacher.class, params);
	}	
	
	
	@Override
	public synchronized List<TestTeacher> findObjectListByPros(TestTeacher testTeacher,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testTeacher));
		return factory.getCacheReadDataSession().queryListResult(TestTeacher.class, params);
	}		
	
	
	
	@Override
	public synchronized int getObjectCount(String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().queryListResultCount(TestTeacher.class, ParamBuilder.getInstance().getParam());
	}	

	
	@Override
	public synchronized int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(TestTeacher.class, params);
	}		

	
	@Override
	public synchronized int getObjectCount(TestTeacher testTeacher,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testTeacher));
		return factory.getCacheReadDataSession().queryListResultCount(TestTeacher.class, params);
	}


}
