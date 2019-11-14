package com.application.kylin;

import com.application.base.core.BaseJunit4Test;
import com.application.base.kylin.jdbc.factory.KylinJdbcSessionFactory;
import com.application.base.utils.json.JsonConvertUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.Map;

/**
 * @author : 孤狼
 * @NAME: RestApiTest
 * @DESC:
 **/
public class JdbcTest extends BaseJunit4Test {

	@Autowired
	private KylinJdbcSessionFactory sessionFactory;
	
	@Test
	public void testQuery(){
		String sql = "select * from LOAN_DATA_80W limit 10 ";
		LinkedList<Map<String, Object>>  finalList = sessionFactory.getJdbcSession().selectSQL("Kkklin",sql);
		System.out.println(JsonConvertUtils.toJson(finalList));
		System.out.println(JsonConvertUtils.toJsonHasNull(finalList.get(0)));
	}
	
}
