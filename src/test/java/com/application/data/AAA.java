package com.application.data;

import com.application.base.core.BaseJunit4Test;
import com.application.base.utils.json.JsonConvertUtils;
import com.javabase.demo.entity.test.TestSchool;
import com.javabase.demo.entity.test.TestScore;
import com.javabase.demo.entity.test.TestStudent;
import com.javabase.demo.entity.test.TestTeacher;
import com.javabase.demo.service.test.TestSchoolService;
import com.javabase.demo.service.test.TestScoreService;
import com.javabase.demo.service.test.TestStudentService;
import com.javabase.demo.service.test.TestTeacherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @desc 测试
 * @author 孤狼
 */
public class AAA extends BaseJunit4Test {
	
	@Autowired
	private TestTeacherService testTeacherService;
	@Autowired
	private TestStudentService testStudentService;
	@Autowired
	private TestScoreService testScoreService;
	@Autowired
	private TestSchoolService testSchoolService;
	
	@Test
	public void test1(){
	 	TestTeacher teacher = testTeacherService.getObjectById(1);
		System.out.println(JsonConvertUtils.toJson(teacher));
		
		TestStudent student = testStudentService.getObjectById(1);
		System.out.println(JsonConvertUtils.toJson(student));
		
		TestScore score = testScoreService.getObjectById("BBB");
		System.out.println(JsonConvertUtils.toJson(score));
		
		TestSchool school = testSchoolService.getObjectById("aaaa");
		System.out.println(JsonConvertUtils.toJson(school));
		
	}
}
