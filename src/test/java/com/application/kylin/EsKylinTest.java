package com.application.kylin;

import com.application.base.core.BaseJunit4Test;
import com.application.base.elastic.elastic.rest.factory.EsJestSessionPoolFactory;
import com.application.base.elastic.entity.ElasticData;
import com.application.base.kylin.jdbc.factory.KylinJdbcSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : 孤狼
 * @NAME: EsKylinTest
 * @DESC:
 **/
public class EsKylinTest extends BaseJunit4Test {
	
	@Autowired
	private EsJestSessionPoolFactory jestFactory;
	
	@Autowired
	private KylinJdbcSessionFactory sessionFactory;
	
	@Test
	public void testEs(){
		Long start = System.currentTimeMillis();
		String sql = "SELECT ZIP_CODE, GRADE, SUM(FUNDED_AMNT) AS SUM_FUNDED_AMNT, AVG(FUNDED_AMNT) AS AVG_FUNDED_AMNT, SUM(INSTALLMENT) AS SUM_INSTALLMENT, AVG(INSTALLMENT) AS AVG_INSTALLMENT FROM UNI_DL.LOAN_DATA_80W GROUP BY ZIP_CODE,GRADE,PROCESSING_DTTM";
		LinkedList<Map<String, Object>> finalList = sessionFactory.getJdbcSession().selectSQL("Kkklin",sql);
		List<ElasticData> esList = new ArrayList<>();
		System.out.println("数据条数是:"+finalList.size());
		int index = 1;
		for (Map<String, Object> data : finalList) {
			ElasticData esData = new ElasticData();
			esData.setIndex("loan_data_test");
			esData.setMapFlag(true);
			esData.setType("loan_data_test");
			esData.setId("loan"+index);
			esData.setMapData(data);
			esList.add(esData);
			index++;
		}
		jestFactory.getElasticSession().addEsDataListByProcessor(esList,true);
		System.out.println("执行完成!,时间:"+(System.currentTimeMillis()-start)/1000+"秒");
	}
}
