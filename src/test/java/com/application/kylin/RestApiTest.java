package com.application.kylin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.application.base.core.BaseJunit4Test;
import com.application.base.kylin.rest.factory.KylinJestSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : 孤狼
 * @NAME: RestApiTest
 * @DESC:
 **/
public class RestApiTest extends BaseJunit4Test {

	@Autowired
	private KylinJestSessionFactory sessionFactory;
	
	@Test
	public void testLogin(){
		String result = sessionFactory.getRestSession().login();
		System.out.println("login info:"+result);
	}
	
	@Test
	public void testQuery(){
		String sql = "select * from LOAN_DATA_80W limit 10 ";
		int offset=0;
		int limit = 20;
		String projectName="Kkklin";
		String result = sessionFactory.getRestSession().query(sql,offset,limit,projectName);
		System.out.println("query info:"+result);
	}
	
	@Test
	public void testCubeDesc(){
		String cubdesc_array = sessionFactory.getRestSession().getCubeDesc("kylin_sales_cube");
		String cubdesc_str=cubdesc_array.substring(1, cubdesc_array.length()-1);
		System.out.println("cube 数组:"+cubdesc_array);
		System.out.println("cube 字符串:"+cubdesc_str);
	}
	
	@Test
	public void testDataModel(){
		String modelDesc = sessionFactory.getRestSession().getDataModel("kylin_sales_model");
		System.out.println("模块介绍:"+modelDesc);
		JSONObject json_Model = JSON.parseObject(modelDesc);
		String fact_table_str = json_Model.get("fact_table").toString();
		System.out.println("fact_table:"+fact_table_str);
	}
}
