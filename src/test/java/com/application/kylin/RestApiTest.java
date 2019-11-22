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
		Integer offset=0;
		Integer limit = 20;
		String projectName="Kkklin";
		String result = sessionFactory.getRestSession().query(sql,offset,limit,true,projectName);
		System.out.println("query info:"+result);
	}
	
	@Test
	public void testTable(){
		String projectName="Kkklin";
		String result = sessionFactory.getRestSession().queryTables(projectName);
		System.out.println(result);
	}
	
	@Test
	public void testListCube(){
		int offset=0;
		int limit =50;
		String json = sessionFactory.getRestSession().listCubes(offset,limit,null,null);
		System.out.println("结果是:\n\t"+json);
	}
	
	@Test
	public void testCube(){
		String cubdesc_array = sessionFactory.getRestSession().getCube("Credit_Risk_Cube");
		System.out.println("result\n\t"+cubdesc_array);
	}
	
	@Test
	public void testCubeDesc(){
		String cubdesc_array = sessionFactory.getRestSession().getCubeDesc("Credit_Risk_Cube");
		System.out.println("result\n\t"+cubdesc_array);
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
