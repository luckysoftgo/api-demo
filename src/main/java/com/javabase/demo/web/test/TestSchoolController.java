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


import com.javabase.demo.entity.test.TestSchool;
import com.javabase.demo.service.test.TestSchoolService;

/**
 * TestSchoolController实现
 * @author 孤狼
 */
@RestController
@RequestMapping("/testSchool")
public class TestSchoolController extends BaseController {
	
	@Autowired
	private TestSchoolService testSchoolService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addTestSchool")
    public void addTestSchool(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,null,null);
        TestSchool object = testSchoolService.saveObject(param);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestSchoolById")
    public void getTestSchoolById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"schoolId");
        String schoolId = BaseStringUtil.stringValue(param.get("schoolId"));
        //根据实际情况去验证 objId 的类型的合法性。
        TestSchool object = testSchoolService.getObjectById(schoolId);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过UUId获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestSchoolByUUId")
    public void getTestSchoolByUUId(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"uuid");
        String uuid = BaseStringUtil.stringValue(param.get("uuid"));
        TestSchool object = testSchoolService.getObjectByUUId(uuid);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestSchoolById")
    public void updateTestSchoolById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"schoolId");
        String schoolId = BaseStringUtil.stringValue(param.get("schoolId"));
        //根据实际情况去验证 objId 的类型的合法性。
        testSchoolService.updateObjectById(param, schoolId);
        printJsonWriter(response, successResultJSON("通过Id="+schoolId+"修改成功"));
    }
    
    /**
     * 通过uuid修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestSchoolByUUId")
    public void updateTestSchoolByUUId(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"uuid");
        String uuid = BaseStringUtil.stringValue(param.get("uuid"));
        testSchoolService.updateObjectByUUId(param, uuid);
        printJsonWriter(response, successResultJSON("通过UUId="+uuid+"修改成功"));
    }
    
    /**
     * 通过Id删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestSchoolById")
    public void delTestSchoolById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"schoolId");
        String schoolId = BaseStringUtil.stringValue(param.get("schoolId"));
        //根据实际情况去验证 objId 的类型的合法性。
        testSchoolService.deleteObjectById(schoolId);
        printJsonWriter(response, successResultJSON("通过Id="+schoolId+"删除成功"));
    }
    
    /**
     * 通过UUId删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestSchoolByUUId")
    public void delTestSchoolByUUId(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"uuid");
        String uuid = BaseStringUtil.stringValue(param.get("uuid"));
        testSchoolService.deleteObjectByUUId(uuid);
        printJsonWriter(response, successResultJSON("通过UUId="+uuid+"删除成功"));
    }
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestSchools")
    public void getTestSchools(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,null,null);
        String resultStr = successResultJSON(testSchoolService.findObjectByPros(param));
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestSchoolsByPage")
    public void getTestSchoolsByPage(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
      	Map<String, Object> param = nullAbleValidation(request,null,null);
        int pageNo = BaseStringUtil.intValue(param.get(PAGE_NO));
        int pageSize = BaseStringUtil.intValue(param.get(PAGE_SIZE));
        Pagination<TestSchool> result = testSchoolService.paginationObjects(param, pageNo, pageSize);
        printJsonWriter(response, successResultJSON(result));
    }
    
}


