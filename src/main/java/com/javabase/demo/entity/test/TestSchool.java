package com.javabase.demo.entity.test;

import com.application.base.utils.common.BaseEntity;


/**
 * TestSchool实体
 * @author 孤狼
 */
public class TestSchool extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String TABLE_NAME = "test_school";
	
	/**学校对应的ID*/
	private String schoolId;
	/**学校对应的ID 对应的静态变量值*/
	public static final String FIELD_SCHOOL_ID = "schoolId";
	/**学校名字*/
	private String name;
	/**学校名字 对应的静态变量值*/
	public static final String FIELD_NAME = "name";
	/**学校地址*/
	private String address;
	/**学校地址 对应的静态变量值*/
	public static final String FIELD_ADDRESS = "address";
	/**联系方式*/
	private String phone;
	/**联系方式 对应的静态变量值*/
	public static final String FIELD_PHONE = "phone";
	/**地区*/
	private String area;
	/**地区 对应的静态变量值*/
	public static final String FIELD_AREA = "area";

	public TestSchool () {
		super();
	}
	
	public TestSchool (String schoolId ,String name ,String address ,String phone ,String area ) {
		super();
		 this.schoolId = schoolId;
		 this.name = name;
		 this.address = address;
		 this.phone = phone;
		 this.area = area;
	}
	
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
