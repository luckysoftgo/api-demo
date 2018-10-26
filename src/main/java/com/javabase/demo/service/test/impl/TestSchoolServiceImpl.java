package com.javabase.demo.service.test.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.base.core.constant.CoreConstants;
import com.application.base.core.exception.CommonException;
import com.application.base.utils.page.Pagination;

import com.application.base.core.utils.CommonBeanUtils;

import com.javabase.demo.entity.test.TestSchool;
import com.javabase.demo.dao.test.TestSchoolDao;
import com.javabase.demo.service.test.TestSchoolService;

/**
 * TestSchoolServiceImpl实现
 * @author 孤狼
 */
@Service("testSchoolService")
public class TestSchoolServiceImpl implements TestSchoolService {

	/**
	 *Dao 注入.
	 */
	@Autowired
	private TestSchoolDao testSchoolDao;

	@Override
	public TestSchool saveObject(Map<String, Object> param) {
		try {
			String factoryTag = "B" ;
			TestSchool object = testSchoolDao.saveObject(param,factoryTag);
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}


	@Override
	public TestSchool saveObject(TestSchool object) {
		try {
			String factoryTag = "B" ;
			TestSchool result = testSchoolDao.saveObject(object,factoryTag);
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}
	
	
	@Override
	public boolean saveBatchObject(List<TestSchool> objs) {
		try {
			String factoryTag = "B" ;
			boolean result = testSchoolDao.saveBatchObject(objs,factoryTag);
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}

	@Override
	public TestSchool getObjectById(Object objId) {
		TestSchool object = null;
		try {
			String factoryTag = "B" ;
			object = testSchoolDao.getObjectById(objId,factoryTag);
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
	public TestSchool getObjectByUUId(String uuid) {
		TestSchool object = null;
		try {
			String factoryTag = "B" ;
			object = testSchoolDao.getObjectByUUId(uuid,factoryTag);
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
			String factoryTag = "B" ;
			TestSchool object = getObjectById(objId); 
			count = testSchoolDao.updateObjectById(param, object,factoryTag);
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
	public int updateObjectById(TestSchool object, Object objId) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			TestSchool existObj = getObjectById(objId);
			Map<String, Object> param = CommonBeanUtils.transBean2Map(object);
			param = CommonBeanUtils.getValueMap(param);
			count = testSchoolDao.updateObjectById(param,existObj,factoryTag);
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
			String factoryTag = "B" ;
			TestSchool object = getObjectByUUId(uuid);
			count = testSchoolDao.updateObjectByUUId(param, object,factoryTag);
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
	public int updateObjectByUUId(TestSchool object, String uuid) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			TestSchool existObj = getObjectByUUId(uuid);
			Map<String, Object> param = CommonBeanUtils.transBean2Map(object);
			param = CommonBeanUtils.getValueMap(param);
			count = testSchoolDao.updateObjectByUUId(param,existObj,factoryTag);
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
			String factoryTag = "B" ;
			count = testSchoolDao.updateObjectByWhere(param,factoryTag);
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
	public int updateObjectByWhere(TestSchool object) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			count = testSchoolDao.updateObjectByWhere(object,factoryTag);
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
			String factoryTag = "B" ;
			count = testSchoolDao.deleteObjectById(objId,factoryTag);
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
			String factoryTag = "B" ;
			count = testSchoolDao.deleteObjectByUUId(uuid,factoryTag);
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
			String factoryTag = "B" ;
			count = testSchoolDao.deleteObjectByWhere(param,factoryTag);
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
	public int deleteObjectByWhere(TestSchool object) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			count = testSchoolDao.deleteObjectByWhere(object,factoryTag);
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
	public Pagination<TestSchool> paginationObjects(Map<String, Object> param, int pageNo, int pageSize){
		Pagination<TestSchool> pagination = null;
		try {
			String factoryTag = "B" ;
			pagination = testSchoolDao.paginationObjects(param, pageNo, pageSize,factoryTag);
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
	public Pagination<TestSchool> paginationObjects(TestSchool object, int pageNo, int pageSize){
		Pagination<TestSchool> pagination = null;
		try {
			String factoryTag = "B" ;
			pagination = testSchoolDao.paginationObjects(object, pageNo, pageSize,factoryTag);
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
	public TestSchool findObjectByPros(Map<String, Object> param) {
		TestSchool object = null;
		try {
			String factoryTag = "B" ;
			object = testSchoolDao.findObjectByPros(param,factoryTag);
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
	public TestSchool findObjectByPros(TestSchool object) {
		TestSchool result = null;
		try {
			String factoryTag = "B" ;
			result = testSchoolDao.findObjectByPros(object,factoryTag);
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
	public List<TestSchool> findObjectListByPros(Map<String, Object> param) {
		List<TestSchool> objects = null;
		try {
			String factoryTag = "B" ;
			objects = testSchoolDao.findObjectListByPros(param,factoryTag);
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
	public List<TestSchool> findObjectListByPros(TestSchool object) {
		List<TestSchool> objects = null;
		try {
			String factoryTag = "B" ;
			objects = testSchoolDao.findObjectListByPros(object,factoryTag);
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
			String factoryTag = "B" ;
			count = testSchoolDao.getObjectCount(factoryTag);
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
			String factoryTag = "B" ;
			count = testSchoolDao.getObjectCount(param,factoryTag);
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
	public int getObjectCount(TestSchool object) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			count = testSchoolDao.getObjectCount(object,factoryTag);
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
