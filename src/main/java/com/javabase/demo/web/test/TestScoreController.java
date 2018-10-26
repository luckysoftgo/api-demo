package com.javabase.demo.web.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.application.base.utils.page.Pagination;
import com.application.base.utils.common.BaseStringUtil;
import com.application.base.core.common.BaseController;


import com.javabase.demo.entity.test.TestScore;
import com.javabase.demo.service.test.TestScoreService;

/**
 * TestScoreController实现
 * @author 孤狼
 */
@RestController
@RequestMapping("/testScore")
public class TestScoreController extends BaseController {
	
	@Autowired
	private TestScoreService testScoreService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addTestScore")
    public void addTestScore(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,null,null);
        TestScore object = testScoreService.saveObject(param);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestScoreById")
    public void getTestScoreById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"scoreId");
        String scoreId = BaseStringUtil.stringValue(param.get("scoreId"));
        //根据实际情况去验证 objId 的类型的合法性。
        TestScore object = testScoreService.getObjectById(scoreId);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过UUId获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestScoreByUUId")
    public void getTestScoreByUUId(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"uuid");
        String uuid = BaseStringUtil.stringValue(param.get("uuid"));
        TestScore object = testScoreService.getObjectByUUId(uuid);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestScoreById")
    public void updateTestScoreById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"scoreId");
        String scoreId = BaseStringUtil.stringValue(param.get("scoreId"));
        //根据实际情况去验证 objId 的类型的合法性。
        testScoreService.updateObjectById(param, scoreId);
        printJsonWriter(response, successResultJSON("通过Id="+scoreId+"修改成功"));
    }
    
    /**
     * 通过uuid修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestScoreByUUId")
    public void updateTestScoreByUUId(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"uuid");
        String uuid = BaseStringUtil.stringValue(param.get("uuid"));
        testScoreService.updateObjectByUUId(param, uuid);
        printJsonWriter(response, successResultJSON("通过UUId="+uuid+"修改成功"));
    }
    
    /**
     * 通过Id删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestScoreById")
    public void delTestScoreById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"scoreId");
        String scoreId = BaseStringUtil.stringValue(param.get("scoreId"));
        //根据实际情况去验证 objId 的类型的合法性。
        testScoreService.deleteObjectById(scoreId);
        printJsonWriter(response, successResultJSON("通过Id="+scoreId+"删除成功"));
    }
    
    /**
     * 通过UUId删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestScoreByUUId")
    public void delTestScoreByUUId(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"uuid");
        String uuid = BaseStringUtil.stringValue(param.get("uuid"));
        testScoreService.deleteObjectByUUId(uuid);
        printJsonWriter(response, successResultJSON("通过UUId="+uuid+"删除成功"));
    }
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestScores")
    public void getTestScores(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,null,null);
        String resultStr = successResultJSON(testScoreService.findObjectByPros(param));
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestScoresByPage")
    public void getTestScoresByPage(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
      	Map<String, Object> param = nullAbleValidation(request,null,null);
        int pageNo = BaseStringUtil.intValue(param.get(PAGE_NO));
        int pageSize = BaseStringUtil.intValue(param.get(PAGE_SIZE));
        Pagination<TestScore> result = testScoreService.paginationObjects(param, pageNo, pageSize);
        printJsonWriter(response, successResultJSON(result));
    }
    
}


