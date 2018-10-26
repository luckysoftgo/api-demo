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


import com.javabase.demo.entity.test.TestStudent;
import com.javabase.demo.service.test.TestStudentService;

/**
 * TestStudentController实现
 * @author 孤狼
 */
@RestController
@RequestMapping("/testStudent")
public class TestStudentController extends BaseController {
	
	@Autowired
	private TestStudentService testStudentService;

	/**
     * 添加对象.
     *
     * @param request
     * @param response
     */
    @RequestMapping("/addTestStudent")
    public void addTestStudent(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,null,null);
        TestStudent object = testStudentService.saveObject(param);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 通过主键获得对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestStudentById")
    public void getTestStudentById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"id");
		Integer id = BaseStringUtil.intValue(param.get("id"));
        //根据实际情况去验证 objId 的类型的合法性。
        TestStudent object = testStudentService.getObjectById(id);
        String resultStr = successResultJSON(object);
        printJsonWriter(response, resultStr);
    }
    
    
    /**
     * 通过id修改对象.
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateTestStudentById")
    public void updateTestStudentById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"id");
		Integer id = BaseStringUtil.intValue(param.get("id"));
        //根据实际情况去验证 objId 的类型的合法性。
        testStudentService.updateObjectById(param, id);
        printJsonWriter(response, successResultJSON("通过Id="+id+"修改成功"));
    }
    
    
    /**
     * 通过Id删除消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delTestStudentById")
    public void delTestStudentById(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,"id");
		Integer id = BaseStringUtil.intValue(param.get("id"));
        //根据实际情况去验证 objId 的类型的合法性。
        testStudentService.deleteObjectById(id);
        printJsonWriter(response, successResultJSON("通过Id="+id+"删除成功"));
    }
    
    
    /**
     * 获得所有消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestStudents")
    public void getTestStudents(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
        Map<String, Object> param = nullAbleValidation(request,null,null);
        String resultStr = successResultJSON(testStudentService.findObjectByPros(param));
        printJsonWriter(response, resultStr);
    }
    
    /**
     * 分页获得消息
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getTestStudentsByPage")
    public void getTestStudentsByPage(HttpServletRequest request, HttpServletResponse response) {
        // 根据实际情况填写.
      	Map<String, Object> param = nullAbleValidation(request,null,null);
        int pageNo = BaseStringUtil.intValue(param.get(PAGE_NO));
        int pageSize = BaseStringUtil.intValue(param.get(PAGE_SIZE));
        Pagination<TestStudent> result = testStudentService.paginationObjects(param, pageNo, pageSize);
        printJsonWriter(response, successResultJSON(result));
    }
    
}


