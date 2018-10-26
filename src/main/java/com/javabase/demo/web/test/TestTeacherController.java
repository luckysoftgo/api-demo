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


import com.javabase.demo.entity.test.TestTeacher;
import com.javabase.demo.service.test.TestTeacherService;

/**
 * TestTeacherController实现
 * @author 孤狼
 */
@RestController
@RequestMapping("/testTeacher")
public class TestTeacherController extends BaseController {
	
	@Autowired
	private TestTeacherService testTeacherService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addTestTeacher")
    public void addTestTeacher(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,null,null);
        TestTeacher object = testTeacherService.saveObject(param);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestTeacherById")
    public void getTestTeacherById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"id");
		Integer id = BaseStringUtil.intValue(param.get("id"));
        //根据实际情况去验证 objId 的类型的合法性。
        TestTeacher object = testTeacherService.getObjectById(id);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过UUId获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestTeacherByUUId")
    public void getTestTeacherByUUId(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"uuid");
        String uuid = BaseStringUtil.stringValue(param.get("uuid"));
        TestTeacher object = testTeacherService.getObjectByUUId(uuid);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestTeacherById")
    public void updateTestTeacherById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"id");
		Integer id = BaseStringUtil.intValue(param.get("id"));
        //根据实际情况去验证 objId 的类型的合法性。
        testTeacherService.updateObjectById(param, id);
        printJsonWriter(response, successResultJSON("通过Id="+id+"修改成功"));
    }
    
    /**
     * 通过uuid修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestTeacherByUUId")
    public void updateTestTeacherByUUId(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"uuid");
        String uuid = BaseStringUtil.stringValue(param.get("uuid"));
        testTeacherService.updateObjectByUUId(param, uuid);
        printJsonWriter(response, successResultJSON("通过UUId="+uuid+"修改成功"));
    }
    
    /**
     * 通过Id删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestTeacherById")
    public void delTestTeacherById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"id");
		Integer id = BaseStringUtil.intValue(param.get("id"));
        //根据实际情况去验证 objId 的类型的合法性。
        testTeacherService.deleteObjectById(id);
        printJsonWriter(response, successResultJSON("通过Id="+id+"删除成功"));
    }
    
    /**
     * 通过UUId删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestTeacherByUUId")
    public void delTestTeacherByUUId(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"uuid");
        String uuid = BaseStringUtil.stringValue(param.get("uuid"));
        testTeacherService.deleteObjectByUUId(uuid);
        printJsonWriter(response, successResultJSON("通过UUId="+uuid+"删除成功"));
    }
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestTeachers")
    public void getTestTeachers(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,null,null);
        String resultStr = successResultJSON(testTeacherService.findObjectByPros(param));
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestTeachersByPage")
    public void getTestTeachersByPage(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
      	Map<String, Object> param = nullAbleValidation(request,null,null);
        int pageNo = BaseStringUtil.intValue(param.get(PAGE_NO));
        int pageSize = BaseStringUtil.intValue(param.get(PAGE_SIZE));
        Pagination<TestTeacher> result = testTeacherService.paginationObjects(param, pageNo, pageSize);
        printJsonWriter(response, successResultJSON(result));
    }
    
}


