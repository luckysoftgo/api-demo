package com.javabase.demo.entity.test;

import com.application.base.utils.common.BaseEntity;


/**
 * TestTeacher实体
 * @author 孤狼
 */
public class TestTeacher extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String TABLE_NAME = "test_teacher";
	
	/**老师的名字*/
	private String name;
	/**老师的名字 对应的静态变量值*/
	public static final String FIELD_NAME = "name";
	/**联系方式*/
	private String mobile;
	/**联系方式 对应的静态变量值*/
	public static final String FIELD_MOBILE = "mobile";
	/**1男,0女*/
	private Integer gender;
	/**1男,0女 对应的静态变量值*/
	public static final String FIELD_GENDER = "gender";
	/**年龄*/
	private Integer age;
	/**年龄 对应的静态变量值*/
	public static final String FIELD_AGE = "age";
	/**教授专业*/
	private String major;
	/**教授专业 对应的静态变量值*/
	public static final String FIELD_MAJOR = "major";
	/**得分*/
	private Double score;
	/**得分 对应的静态变量值*/
	public static final String FIELD_SCORE = "score";

	public TestTeacher () {
		super();
	}
	
	public TestTeacher (String name ,String mobile ,Integer gender ,Integer age ,String major ,Double score ) {
		super();
		 this.name = name;
		 this.mobile = mobile;
		 this.gender = gender;
		 this.age = age;
		 this.major = major;
		 this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
}
