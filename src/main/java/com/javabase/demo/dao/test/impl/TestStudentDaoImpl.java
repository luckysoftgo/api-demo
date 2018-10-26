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

import com.javabase.demo.entity.test.TestStudent;
import com.javabase.demo.dao.test.TestStudentDao;

/**
 * TestStudentDaoImpl实现
 * @author 孤狼
 */
@Repository("testStudentDao")
public class TestStudentDaoImpl extends MultiCacheStrutsBaseDaoImpl<TestStudent> implements TestStudentDao {
	
	
	@Override
	public synchronized TestStudent saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestStudent testStudent = CommonBeanUtils.transMap2BasePO(param, TestStudent.class);
		return factory.getCacheWriteDataSession().saveObject(TestStudent.class,testStudent);
	}
	


	@Override
	public synchronized TestStudent saveObject(TestStudent testStudent,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheWriteDataSession().saveObject(TestStudent.class,testStudent);
	}		

	
	
	@Override
	public synchronized boolean saveBatchObject(List<TestStudent> testStudents,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheWriteDataSession().saveBatchObject(TestStudent.class,testStudents);
	}	

	
	
	@Override
	public synchronized TestStudent getObjectById(Object objId,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().querySingleResultById(TestStudent.class,"id", objId);
	}		


	
    @Override
	public TestStudent getObjectByUUId(String uuid,String factoryTag) throws BusinessException {
		return null;
	}

	
	
	
	@Override
	public synchronized int updateObjectById(Map<String, Object> param, TestStudent object,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		TestStudent testStudent = new TestStudent();
		testStudent.setId(object.getId());
		testStudent.setCreateTime(object.getCreateTime());
		testStudent = CommonBeanUtils.transMap2BasePO(param, testStudent);
		return factory.getCacheWriteDataSession().updateObjectById(TestStudent.class, testStudent);
	}		
	
	
    @Override
	public int updateObjectByUUId(Map<String, Object> param, TestStudent testStudent,String factoryTag) throws BusinessException {
		return 0;
	}
	
	
	@Override
	public synchronized int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		//根据实际情况填写 ; SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestStudent.class, params, where);
	}		

	
	@Override
	public synchronized int updateObjectByWhere(TestStudent testStudent,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam();
		//根据实际情况填写. //whereStr //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestStudent.class, params, where);
	}		
	
	
	@Override
	public synchronized int deleteObjectById(Object objId,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("id",objId));
		return factory.getCacheWriteDataSession().logicDelete(TestStudent.class, param);
	}		

	
    @Override
	public int deleteObjectByUUId(String uuid,String factoryTag) throws BusinessException {
    	//没有uuid,该方法就为不可用
		return 0;
	}


	@Override
	public synchronized int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己设置访问的数据库.
		factory.setFactoryTag(factoryTag);
		//根据实际情况填写要查的列和对应的值.
		//根据实际情况填写. //whereStr //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().logicWhereDelete(TestStudent.class, where);
	}		


	
	
	@Override
	public synchronized int deleteObjectByWhere(TestStudent testStudent,String factoryTag) throws BusinessException {
		//根据实际情况自己设置访问的数据库.
		factory.setFactoryTag(factoryTag);
		//根据实际情况填写要查的列和对应的值. //SqlCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA");
		CustomSql where = null;
		return factory.getCacheWriteDataSession().logicWhereDelete(TestStudent.class, where);
	}	

	
	@Override
	public synchronized Pagination<TestStudent> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(TestStudent.class, params, pageNo, pageSize, factoryTag);
	}	


	@Override
	public synchronized Pagination<TestStudent> paginationObjects(TestStudent testStudent, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testStudent));
		return queryClassPagination(TestStudent.class, params, pageNo, pageSize, factoryTag);
	}		
	
	
	@Override
	public synchronized TestStudent findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(TestStudent.class, params);
	}		

	
	@Override
	public synchronized TestStudent findObjectByPros(TestStudent testStudent,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testStudent));
		return factory.getCacheReadDataSession().querySingleResultByParams(TestStudent.class, params);
	}		

	
	
	@Override
	public synchronized List<TestStudent> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(TestStudent.class, params);
	}	
	
	
	@Override
	public synchronized List<TestStudent> findObjectListByPros(TestStudent testStudent,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testStudent));
		return factory.getCacheReadDataSession().queryListResult(TestStudent.class, params);
	}		
	
	
	
	@Override
	public synchronized int getObjectCount(String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		return factory.getCacheReadDataSession().queryListResultCount(TestStudent.class, ParamBuilder.getInstance().getParam());
	}	

	
	@Override
	public synchronized int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(TestStudent.class, params);
	}		

	
	@Override
	public synchronized int getObjectCount(TestStudent testStudent,String factoryTag) throws BusinessException {
		//根据实际情况自己添加
		factory.setFactoryTag(factoryTag);
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(testStudent));
		return factory.getCacheReadDataSession().queryListResultCount(TestStudent.class, params);
	}


}
