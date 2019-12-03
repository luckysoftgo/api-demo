package com.application.kylin;

import com.application.base.core.BaseJunit4Test;
import com.application.base.elastic.elastic.restclient.factory.EsRestClientSessionPoolFactory;
import com.application.base.elastic.entity.ElasticData;
import com.application.base.kylin.jdbc.factory.KylinJdbcSessionFactory;
import com.application.base.utils.json.JsonConvertUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author : 孤狼
 * @NAME: RestApiTest
 * @DESC:
 **/
public class JdbcTest extends BaseJunit4Test {

	@Autowired
	private KylinJdbcSessionFactory sessionFactory;
	
	@Autowired
	private EsRestClientSessionPoolFactory jestFactory;
	
	@Test
	public void test1(){
		long start = System.currentTimeMillis();
		String sql = "SELECT MEMBER_ID,TERM,GRADE,SUB_GRADE,ZIP_CODE,ADDR_STATE,PROCESSING_DTTM , COUNT(1) AS _COUNT_,SUM(LOAN_AMNT) AS SUM_LOAN_AMNT,SUM(FUNDED_AMNT) AS SUM_FUNDED_AMNT,SUM(FUNDED_AMNT_INV) AS SUM_FUNDED_AMNT_INV,MAX(LOAN_AMNT) AS MAX_LOAN_AMNT,MAX(FUNDED_AMNT) AS MAX_FUNDED_AMNT,MAX(INT_RATE) AS MAX_INT_RATE,MAX(FUNDED_AMNT_INV) AS MAX_FUNDED_AMNT_INV,MIN(LOAN_AMNT) AS MIN_LOAN_AMNT,MIN(FUNDED_AMNT) AS MIN_FUNDED_AMNT,MIN(INT_RATE) AS MIN_INT_RATE,MIN(FUNDED_AMNT_INV) AS MIN_FUNDED_AMNT_INV,PERCENTILE_APPROX(INT_RATE,0.5) AS PERCENTILE_INT_RATE FROM USE_AND_DROP.CREDIT_RISK_DATA GROUP BY MEMBER_ID,TERM,GRADE,SUB_GRADE,ZIP_CODE,ADDR_STATE,PROCESSING_DTTM";
		LinkedList<Map<String, Object>>  datas = sessionFactory.getJdbcSession().selectSQL("Kkklin",sql);
		
		List<ElasticData> esList = new ArrayList<>();
		if (datas!=null && datas.size()>0){
			for (int i = 0; i < datas.size() ; i++) {
				Map<String, Object> data = datas.get(i);
				ElasticData elasticData = new ElasticData();
				elasticData.setMapFlag(true);
				elasticData.setMapData(data);
				elasticData.setId((i+1+""));
				elasticData.setType("credit_risk_cube");
				elasticData.setIndex("credit_risk_cube");
				esList.add(elasticData);
			}
			long end = System.currentTimeMillis();
			boolean result = jestFactory.getElasticSession().addEsDataListByProcessor(esList, true);
			System.out.println("执行结果是:"+result+",count = "+datas.size()+",time="+(end-start)/1000+"秒");
		}
	}
	
	
	@Test
	public void testQuery(){
		String sql = "select * from LOAN_DATA_80W limit 10 ";
		LinkedList<Map<String, Object>>  finalList = sessionFactory.getJdbcSession().selectSQL("Kkklin",sql);
		System.out.println(JsonConvertUtils.toJson(finalList));
		System.out.println(JsonConvertUtils.toJsonHasNull(finalList.get(0)));
	}
	
	@Test
	public void testData(){
		System.out.println("开始放入数据....");
		try {
			String sql="SELECT TERM, GRADE, SUB_GRADE, EMP_TITLE, EMP_LENGTH, HOME_OWNERSHIP, PURPOSE, ZIP_CODE, ADDR_STATE, APPLICATION_TYPE, ISSUE_DT_DATE, COUNT(1) AS _COUNT_, SUM(LOAN_AMNT) AS SUM_LOAN_AMNT, SUM(FUNDED_AMNT) AS SUM_FUNDED_AMNT, MAX(LOAN_AMNT) AS MAX_LOAN_AMNT, MAX(FUNDED_AMNT) AS MAX_FUNDED_AMNT, MAX(INT_RATE) AS MAX_INT_RATE, MAX(INSTALLMENT) AS MAX_INSTALLMENT, MAX(ANNUAL_INC) AS MAX_ANNUAL_INC, MAX(LAST_PYMNT_AMNT) AS MAX_LAST_PYMNT_AMNT, MIN(LOAN_AMNT) AS MIN_LOAN_AMNT, MIN(FUNDED_AMNT) AS MIN_FUNDED_AMNT, MIN(INT_RATE) AS MIN_INT_RATE, MIN(INSTALLMENT) AS MIN_INSTALLMENT, MIN(ANNUAL_INC) AS MIN_ANNUAL_INC, MIN(LAST_PYMNT_AMNT) AS MIN_LAST_PYMNT_AMNT, PERCENTILE_APPROX (LOAN_AMNT, 0.5) AS PERCENTILE_LOAN_AMNT, PERCENTILE_APPROX (FUNDED_AMNT, 0.5) AS PERCENTILE_FUNDED_AMNT, PERCENTILE_APPROX (INT_RATE, 0.5) AS PERCENTILE_INT_RATE, PERCENTILE_APPROX (INSTALLMENT, 0.5) AS PERCENTILE_INSTALLMENT, PERCENTILE_APPROX (ANNUAL_INC, 0.5) AS PERCENTILE_ANNUAL_INC FROM UNI_DL.KLLDATA GROUP BY TERM, GRADE, SUB_GRADE, EMP_TITLE, EMP_LENGTH, HOME_OWNERSHIP, PURPOSE, ZIP_CODE, ADDR_STATE, APPLICATION_TYPE, ISSUE_DT_DATE";
			LinkedList<Map<String, Object>> datas = sessionFactory.getJdbcSession().selectSQL("Kkklin",sql);
			List<ElasticData> esList = new ArrayList<>();
			if (datas!=null && datas.size()>0) {
				System.out.println("count = "+datas.size());
				for (int i = 0; i < datas.size(); i++) {
					Map<String, Object> data = datas.get(i);
					ElasticData elasticData = new ElasticData();
					elasticData.setMapFlag(true);
					//elasticData.setData(new Gson().toJson(data));
					elasticData.setMapData(data);
					elasticData.setId((i + 1 + ""));
					elasticData.setType("kll_cube");
					elasticData.setIndex("kll_cube");
					esList.add(elasticData);
					if(i!=0 && i%20000==0){
						jestFactory.getElasticSession().addEsDataListByProcessor(esList,true);
						System.out.println("执行了 20000 条数据!!! ");
						esList.clear();
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("完成！！！！！！！！！！！！！！！！！！！！！！！！");
	}
	
}
