package com.javabase.demo.entity.test;

import com.application.base.utils.common.BaseEntity;

import  java.math.BigDecimal;

/**
 * TestStudent实体
 * @author 孤狼
 */
public class TestStudent extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String TABLE_NAME = "test_student";
	
	/**学生的名字*/
	private String name;
	/**学生的名字 对应的静态变量值*/
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
	/**总学费*/
	private BigDecimal totalMoney;
	/**总学费 对应的静态变量值*/
	public static final String FIELD_TOTAL_MONEY = "totalMoney";

	public TestStudent () {
		super();
	}
	
	public TestStudent (String name ,String mobile ,Integer gender ,Integer age ,BigDecimal totalMoney ) {
		super();
		 this.name = name;
		 this.mobile = mobile;
		 this.gender = gender;
		 this.age = age;
		 this.totalMoney = totalMoney;
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
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
}
