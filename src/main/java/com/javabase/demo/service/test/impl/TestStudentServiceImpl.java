package com.javabase.demo.service.test.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.base.core.constant.CoreConstants;
import com.application.base.core.exception.CommonException;
import com.application.base.utils.page.Pagination;

import com.application.base.core.utils.CommonBeanUtils;

import com.javabase.demo.entity.test.TestStudent;
import com.javabase.demo.dao.test.TestStudentDao;
import com.javabase.demo.service.test.TestStudentService;

/**
 * TestStudentServiceImpl实现
 * @author 孤狼
 */
@Service("testStudentService")
public class TestStudentServiceImpl implements TestStudentService {

	/**
	 *Dao 注入.
	 */
	@Autowired
	private TestStudentDao testStudentDao;

	@Override
	public TestStudent saveObject(Map<String, Object> param) {
		try {
			String factoryTag = "A" ;
			TestStudent object = testStudentDao.saveObject(param,factoryTag);
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}


	@Override
	public TestStudent saveObject(TestStudent object) {
		try {
			String factoryTag = "A" ;
			TestStudent result = testStudentDao.saveObject(object,factoryTag);
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}
	
	
	@Override
	public boolean saveBatchObject(List<TestStudent> objs) {
		try {
			String factoryTag = "A" ;
			boolean result = testStudentDao.saveBatchObject(objs,factoryTag);
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}

	@Override
	public TestStudent getObjectById(Object objId) {
		TestStudent object = null;
		try {
			String factoryTag = "A" ;
			object = testStudentDao.getObjectById(objId,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.SELECT_DATA_FROM_DB_FAIL);
		}
		if(object==null){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}
		return object;
	}


    @Override
	public TestStudent getObjectByUUId(String uuid) {
		return null;
	}

	
	@Override
	public int updateObjectById(Map<String, Object> param, Object objId) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			TestStudent object = getObjectById(objId); 
			count = testStudentDao.updateObjectById(param, object,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.DATA_OPERATE_EXCEPTION_MSG);
		}
		if(count < 1 ){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.UPDATE_DATA_TO_DB_FAIL);
		}
		return count;
	}


	@Override
	public int updateObjectById(TestStudent object, Object objId) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			TestStudent existObj = getObjectById(objId);
			Map<String, Object> param = CommonBeanUtils.transBean2Map(object);
			param = CommonBeanUtils.getValueMap(param);
			count = testStudentDao.updateObjectById(param,existObj,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.DATA_OPERATE_EXCEPTION_MSG);
		}
		if(count < 1 ){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.UPDATE_DATA_TO_DB_FAIL);
		}
		return count;
	}
	
	
    @Override
	public int updateObjectByUUId(Map<String, Object> param, String uuid) {
		return 0;
	}
	
	
    @Override
	public int updateObjectByUUId(TestStudent object, String uuid) {
		return 0;
	}
	
	
	@Override
	public int updateObjectByWhere(Map<String, Object> param) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testStudentDao.updateObjectByWhere(param,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.DATA_OPERATE_EXCEPTION_MSG);
		}
		if(count < 1 ){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.UPDATE_DATA_TO_DB_FAIL);
		}
		return count;
	}
	
	
	@Override
	public int updateObjectByWhere(TestStudent object) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testStudentDao.updateObjectByWhere(object,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.DATA_OPERATE_EXCEPTION_MSG);
		}
		if(count < 1 ){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.UPDATE_DATA_TO_DB_FAIL);
		}
		return count;
	}
	
	
	@Override
	public int deleteObjectById(Object objId) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testStudentDao.deleteObjectById(objId,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.DATA_OPERATE_EXCEPTION_MSG);
		}
		if(count < 1 ){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.DELETE_DATA_TO_DB_FAIL);
		}
		return count;
	}

    @Override
	public int deleteObjectByUUId(String uuid) {
		return 0;
	}
	
	
	@Override
	public int deleteObjectByWhere(Map<String, Object> param) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testStudentDao.deleteObjectByWhere(param,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.DATA_OPERATE_EXCEPTION_MSG);
		}
		if(count < 1 ){
			throw new CommonException(CoreConstants.CommonMsgResult.DELETE_DATA_TO_DB_FAIL);
		}
		return count;
	}


	@Override
	public int deleteObjectByWhere(TestStudent object) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testStudentDao.deleteObjectByWhere(object,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.DATA_OPERATE_EXCEPTION_MSG);
		}
		if(count < 1 ){
			throw new CommonException(CoreConstants.CommonMsgResult.DELETE_DATA_TO_DB_FAIL);
		}
		return count;
	}

	@Override
	public Pagination<TestStudent> paginationObjects(Map<String, Object> param, int pageNo, int pageSize){
		Pagination<TestStudent> pagination = null;
		try {
			String factoryTag = "A" ;
			pagination = testStudentDao.paginationObjects(param, pageNo, pageSize,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_PAGE_DATA_FAIL);
		}
		if(pagination==null){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}
		return pagination;
	}

	@Override
	public Pagination<TestStudent> paginationObjects(TestStudent object, int pageNo, int pageSize){
		Pagination<TestStudent> pagination = null;
		try {
			String factoryTag = "A" ;
			pagination = testStudentDao.paginationObjects(object, pageNo, pageSize,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_PAGE_DATA_FAIL);
		}
		if(pagination==null){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}
		return pagination;
	}

	@Override
	public TestStudent findObjectByPros(Map<String, Object> param) {
		TestStudent object = null;
		try {
			String factoryTag = "A" ;
			object = testStudentDao.findObjectByPros(param,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.SELECT_DATA_FROM_DB_FAIL);
		}
		if(object==null){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}
		return object;
	}

	@Override
	public TestStudent findObjectByPros(TestStudent object) {
		TestStudent result = null;
		try {
			String factoryTag = "A" ;
			result = testStudentDao.findObjectByPros(object,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.SELECT_DATA_FROM_DB_FAIL);
		}
		if(result==null){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}
		return result;
	}

	@Override
	public List<TestStudent> findObjectListByPros(Map<String, Object> param) {
		List<TestStudent> objects = null;
		try {
			String factoryTag = "A" ;
			objects = testStudentDao.findObjectListByPros(param,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.SELECT_DATA_FROM_DB_FAIL);
		}
		if(objects==null || objects.size()==0 ){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}
		return objects;
	}

	@Override
	public List<TestStudent> findObjectListByPros(TestStudent object) {
		List<TestStudent> objects = null;
		try {
			String factoryTag = "A" ;
			objects = testStudentDao.findObjectListByPros(object,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.SELECT_DATA_FROM_DB_FAIL);
		}
		if(objects==null || objects.size()==0 ){
			//异常信息由自己去配置文件中编写
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}
		return objects;
	}

	@Override
	public int getObjectCount() {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testStudentDao.getObjectCount(factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_TOTAL_DATA_FAIL);
		}
		if(count < 1 ){
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}
		return count;
	}

	@Override
	public int getObjectCount(Map<String, Object> param) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testStudentDao.getObjectCount(param,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_TOTAL_DATA_FAIL);
		}
		if(count < 1){
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}	
		return count;		
	}

	@Override
	public int getObjectCount(TestStudent object) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testStudentDao.getObjectCount(object,factoryTag);
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_TOTAL_DATA_FAIL);
		}
		if(count < 1){
			throw new CommonException(CoreConstants.CommonMsgResult.QUERY_DB_NO_DATA);
		}	
		return count;		
	}
}
