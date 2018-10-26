package com.javabase.demo.service.test.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.base.core.constant.CoreConstants;
import com.application.base.core.exception.CommonException;
import com.application.base.utils.page.Pagination;

import com.application.base.core.utils.CommonBeanUtils;

import com.javabase.demo.entity.test.TestTeacher;
import com.javabase.demo.dao.test.TestTeacherDao;
import com.javabase.demo.service.test.TestTeacherService;

/**
 * TestTeacherServiceImpl实现
 * @author 孤狼
 */
@Service("testTeacherService")
public class TestTeacherServiceImpl implements TestTeacherService {

	/**
	 *Dao 注入.
	 */
	@Autowired
	private TestTeacherDao testTeacherDao;

	@Override
	public TestTeacher saveObject(Map<String, Object> param) {
		try {
			String factoryTag = "A" ;
			TestTeacher object = testTeacherDao.saveObject(param,factoryTag);
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}


	@Override
	public TestTeacher saveObject(TestTeacher object) {
		try {
			String factoryTag = "A" ;
			TestTeacher result = testTeacherDao.saveObject(object,factoryTag);
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}
	
	
	@Override
	public boolean saveBatchObject(List<TestTeacher> objs) {
		try {
			String factoryTag = "A" ;
			boolean result = testTeacherDao.saveBatchObject(objs,factoryTag);
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}

	@Override
	public TestTeacher getObjectById(Object objId) {
		TestTeacher object = null;
		try {
			String factoryTag = "A" ;
			object = testTeacherDao.getObjectById(objId,factoryTag);
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
	public TestTeacher getObjectByUUId(String uuid) {
		TestTeacher object = null;
		try {
			String factoryTag = "A" ;
			object = testTeacherDao.getObjectByUUId(uuid,factoryTag);
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
	public int updateObjectById(Map<String, Object> param, Object objId) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			TestTeacher object = getObjectById(objId); 
			count = testTeacherDao.updateObjectById(param, object,factoryTag);
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
	public int updateObjectById(TestTeacher object, Object objId) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			TestTeacher existObj = getObjectById(objId);
			Map<String, Object> param = CommonBeanUtils.transBean2Map(object);
			param = CommonBeanUtils.getValueMap(param);
			count = testTeacherDao.updateObjectById(param,existObj,factoryTag);
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
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			TestTeacher object = getObjectByUUId(uuid);
			count = testTeacherDao.updateObjectByUUId(param, object,factoryTag);
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
	public int updateObjectByUUId(TestTeacher object, String uuid) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			TestTeacher existObj = getObjectByUUId(uuid);
			Map<String, Object> param = CommonBeanUtils.transBean2Map(object);
			param = CommonBeanUtils.getValueMap(param);
			count = testTeacherDao.updateObjectByUUId(param,existObj,factoryTag);
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
	public int updateObjectByWhere(Map<String, Object> param) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testTeacherDao.updateObjectByWhere(param,factoryTag);
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
	public int updateObjectByWhere(TestTeacher object) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testTeacherDao.updateObjectByWhere(object,factoryTag);
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
			count = testTeacherDao.deleteObjectById(objId,factoryTag);
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
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testTeacherDao.deleteObjectByUUId(uuid,factoryTag);
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
	public int deleteObjectByWhere(Map<String, Object> param) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testTeacherDao.deleteObjectByWhere(param,factoryTag);
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
	public int deleteObjectByWhere(TestTeacher object) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testTeacherDao.deleteObjectByWhere(object,factoryTag);
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
	public Pagination<TestTeacher> paginationObjects(Map<String, Object> param, int pageNo, int pageSize){
		Pagination<TestTeacher> pagination = null;
		try {
			String factoryTag = "A" ;
			pagination = testTeacherDao.paginationObjects(param, pageNo, pageSize,factoryTag);
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
	public Pagination<TestTeacher> paginationObjects(TestTeacher object, int pageNo, int pageSize){
		Pagination<TestTeacher> pagination = null;
		try {
			String factoryTag = "A" ;
			pagination = testTeacherDao.paginationObjects(object, pageNo, pageSize,factoryTag);
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
	public TestTeacher findObjectByPros(Map<String, Object> param) {
		TestTeacher object = null;
		try {
			String factoryTag = "A" ;
			object = testTeacherDao.findObjectByPros(param,factoryTag);
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
	public TestTeacher findObjectByPros(TestTeacher object) {
		TestTeacher result = null;
		try {
			String factoryTag = "A" ;
			result = testTeacherDao.findObjectByPros(object,factoryTag);
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
	public List<TestTeacher> findObjectListByPros(Map<String, Object> param) {
		List<TestTeacher> objects = null;
		try {
			String factoryTag = "A" ;
			objects = testTeacherDao.findObjectListByPros(param,factoryTag);
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
	public List<TestTeacher> findObjectListByPros(TestTeacher object) {
		List<TestTeacher> objects = null;
		try {
			String factoryTag = "A" ;
			objects = testTeacherDao.findObjectListByPros(object,factoryTag);
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
			count = testTeacherDao.getObjectCount(factoryTag);
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
			count = testTeacherDao.getObjectCount(param,factoryTag);
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
	public int getObjectCount(TestTeacher object) {
		int count = 0 ;
		try {
			String factoryTag = "A" ;
			count = testTeacherDao.getObjectCount(object,factoryTag);
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
