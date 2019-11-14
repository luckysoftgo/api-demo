package com.application.kylin;

import com.application.base.core.BaseJunit4Test;
import com.application.base.kylin.jdbc.factory.KylinJdbcSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;

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
		ResultSet resultSet = sessionFactory.getJdbcSession().selectSQL("Kkklin",sql);
		try {
			while (resultSet.next()) {
				System.out.println("values:"+resultSet.getString("GRADE")+","+resultSet.getString("ZIP_CODE")+","+resultSet.getString("APPLICATION_TYPE"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			sessionFactory.getJdbcSession().close("Kkklin");
		}
	}
	
}
