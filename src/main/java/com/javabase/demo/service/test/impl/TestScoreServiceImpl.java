package com.javabase.demo.service.test.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.base.core.constant.CoreConstants;
import com.application.base.core.exception.CommonException;
import com.application.base.utils.page.Pagination;

import com.application.base.core.utils.CommonBeanUtils;

import com.javabase.demo.entity.test.TestScore;
import com.javabase.demo.dao.test.TestScoreDao;
import com.javabase.demo.service.test.TestScoreService;

/**
 * TestScoreServiceImpl实现
 * @author 孤狼
 */
@Service("testScoreService")
public class TestScoreServiceImpl implements TestScoreService {

	/**
	 *Dao 注入.
	 */
	@Autowired
	private TestScoreDao testScoreDao;

	@Override
	public TestScore saveObject(Map<String, Object> param) {
		try {
			String factoryTag = "B" ;
			TestScore object = testScoreDao.saveObject(param,factoryTag);
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}


	@Override
	public TestScore saveObject(TestScore object) {
		try {
			String factoryTag = "B" ;
			TestScore result = testScoreDao.saveObject(object,factoryTag);
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}
	
	
	@Override
	public boolean saveBatchObject(List<TestScore> objs) {
		try {
			String factoryTag = "B" ;
			boolean result = testScoreDao.saveBatchObject(objs,factoryTag);
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CoreConstants.CommonMsgResult.ADD_DATA_TO_DB_FAIL);
		}
	}

	@Override
	public TestScore getObjectById(Object objId) {
		TestScore object = null;
		try {
			String factoryTag = "B" ;
			object = testScoreDao.getObjectById(objId,factoryTag);
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
	public TestScore getObjectByUUId(String uuid) {
		TestScore object = null;
		try {
			String factoryTag = "B" ;
			object = testScoreDao.getObjectByUUId(uuid,factoryTag);
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
			TestScore object = getObjectById(objId); 
			count = testScoreDao.updateObjectById(param, object,factoryTag);
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
	public int updateObjectById(TestScore object, Object objId) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			TestScore existObj = getObjectById(objId);
			Map<String, Object> param = CommonBeanUtils.transBean2Map(object);
			param = CommonBeanUtils.getValueMap(param);
			count = testScoreDao.updateObjectById(param,existObj,factoryTag);
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
			TestScore object = getObjectByUUId(uuid);
			count = testScoreDao.updateObjectByUUId(param, object,factoryTag);
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
	public int updateObjectByUUId(TestScore object, String uuid) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			TestScore existObj = getObjectByUUId(uuid);
			Map<String, Object> param = CommonBeanUtils.transBean2Map(object);
			param = CommonBeanUtils.getValueMap(param);
			count = testScoreDao.updateObjectByUUId(param,existObj,factoryTag);
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
			count = testScoreDao.updateObjectByWhere(param,factoryTag);
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
	public int updateObjectByWhere(TestScore object) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			count = testScoreDao.updateObjectByWhere(object,factoryTag);
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
			count = testScoreDao.deleteObjectById(objId,factoryTag);
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
			count = testScoreDao.deleteObjectByUUId(uuid,factoryTag);
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
			count = testScoreDao.deleteObjectByWhere(param,factoryTag);
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
	public int deleteObjectByWhere(TestScore object) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			count = testScoreDao.deleteObjectByWhere(object,factoryTag);
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
	public Pagination<TestScore> paginationObjects(Map<String, Object> param, int pageNo, int pageSize){
		Pagination<TestScore> pagination = null;
		try {
			String factoryTag = "B" ;
			pagination = testScoreDao.paginationObjects(param, pageNo, pageSize,factoryTag);
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
	public Pagination<TestScore> paginationObjects(TestScore object, int pageNo, int pageSize){
		Pagination<TestScore> pagination = null;
		try {
			String factoryTag = "B" ;
			pagination = testScoreDao.paginationObjects(object, pageNo, pageSize,factoryTag);
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
	public TestScore findObjectByPros(Map<String, Object> param) {
		TestScore object = null;
		try {
			String factoryTag = "B" ;
			object = testScoreDao.findObjectByPros(param,factoryTag);
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
	public TestScore findObjectByPros(TestScore object) {
		TestScore result = null;
		try {
			String factoryTag = "B" ;
			result = testScoreDao.findObjectByPros(object,factoryTag);
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
	public List<TestScore> findObjectListByPros(Map<String, Object> param) {
		List<TestScore> objects = null;
		try {
			String factoryTag = "B" ;
			objects = testScoreDao.findObjectListByPros(param,factoryTag);
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
	public List<TestScore> findObjectListByPros(TestScore object) {
		List<TestScore> objects = null;
		try {
			String factoryTag = "B" ;
			objects = testScoreDao.findObjectListByPros(object,factoryTag);
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
			count = testScoreDao.getObjectCount(factoryTag);
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
			count = testScoreDao.getObjectCount(param,factoryTag);
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
	public int getObjectCount(TestScore object) {
		int count = 0 ;
		try {
			String factoryTag = "B" ;
			count = testScoreDao.getObjectCount(object,factoryTag);
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
