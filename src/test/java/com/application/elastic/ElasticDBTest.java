package com.application.elastic;

import com.application.base.core.BaseJunit4Test;
import com.application.base.elastic.elastic.restclient.factory.EsRestClientSessionPoolFactory;
import com.application.base.elastic.elastic.transport.factory.EsTransportSessionPoolFactory;
import com.application.base.elastic.entity.ElasticData;
import com.application.base.utils.date.DateUtils;
import com.application.base.utils.json.JsonConvertUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @NAME: ElasticDBTest.
 * @DESC: 通过数据库导入数据.
 * @USER: 孤狼.
 **/
public class ElasticDBTest extends BaseJunit4Test {
	
	@Autowired
	private EsRestClientSessionPoolFactory operateFactory;
	
	@Autowired
	private EsTransportSessionPoolFactory transportFactory;
	
	@Test
	public void testCreateDB(){
		for (int i = 0; i <1000 ; i++) {
			String kettle="kettle";
			boolean result=operateFactory.getElasticSession().judgeIndexExist(kettle);
			System.out.println("index="+i+",创建的结果 1 是:"+result);
			result=operateFactory.getElasticSession().addEsType(kettle,"test1");
			System.out.println("index="+i+",创建的结果 2 是:"+result);
		}
		/*String kettle="kettle";
		boolean result=operateFactory.getElasticSession().addEsIndex(kettle);
		System.out.println("创建的结果 1 是:"+result);
		result=operateFactory.getElasticSession().addEsType(kettle,"test1");
		System.out.println("创建的结果 2 是:"+result);*/
	}
	
	@Test
	public void testfromDb(){
		DBHelper dbHelper=new DBHelper();
		String sql = "SELECT FIRST_LEVEL_NAME, SECOND_LEVEL_NAME, DATA_VOLUME, DATA_TYPE, CLASS_NAME, SORT_NO, ID FROM SUM_DATA_DIR";
		List<ElasticData> dataList=new ArrayList<ElasticData>();
		ResultSet result = dbHelper.stQuery(sql);
		try {
			while(result.next()){
				Map<String,Object> data = new HashMap<>();
				data.put("first_level_name",result.getString("first_level_name"));
				data.put("second_level_name",result.getString("second_level_name"));
				data.put("data_volume",result.getInt("data_volume"));
				data.put("data_type",result.getString("data_type"));
				data.put("class_name",result.getString("class_name"));
				data.put("sort_no",result.getInt("sort_no"));
				data.put("id",result.getString("id"));
				ElasticData esData=new ElasticData();
				esData.setIndex("test-screen");
				esData.setType("test-screen");
				esData.setId(result.getString("id"));
				esData.setMapFlag(true);
				esData.setMapData(data);
				dataList.add(esData);
			}
			System.out.println("数据条数是:"+dataList.size());
			boolean flag = operateFactory.getElasticSession().addEsDataList(dataList,true);
			if (flag){
				System.out.println("成功");
			}else{
				System.out.println("失败");
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				result.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	@Test
	public void testfromDb1(){
		ElasticData data=new ElasticData();
		data.setIndex("kettle");
		data.setType("test1");
		data.setId("1000000");
		data.setData(JsonConvertUtils.toJson(createJson()));
		data.setMapFlag(false);
		boolean flag = operateFactory.getElasticSession().addEsData(data);
		if (flag){
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}
	
	
	/**
	 * test data
	 * @return
	 */
	public  Map<String, Object> createJson() {
		HashMap<String, Object> map = new HashMap<String, Object>(16);
		map.put("user", "bruce");
		map.put("date", DateUtils.getCurrentTimeStr());
		map.put("msg", " trying out Elasticsearch index ");
		return map;
	}
	
	/**
	 * test data
	 * @return
	 */
	public  Map<String, Object>[] createJson2(int count) {
		HashMap<String, Object>[] list = new HashMap[count];
		for (int i = 0; i < count; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>(16);
			map.put("user", "bruce"+i);
			map.put("date", DateUtils.getCurrentTimeStr());
			map.put("msg", " trying out Elasticsearch index "+i);
			list[i]=map;
		}
		return list;
	}
}
