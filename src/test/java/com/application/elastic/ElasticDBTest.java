package com.application.elastic;

import com.application.base.all.elastic.elastic.rest.factory.EsJestSessionPoolFactory;
import com.application.base.all.elastic.entity.ElasticData;
import com.application.base.core.BaseJunit4Test;
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
	private EsJestSessionPoolFactory operateFactory;
	//private EsTransportSessionPoolFactory operateFactory;
	
	@Test
	public void testfromDb(){
		DBHelper dbHelper=new DBHelper();
		String sql = "SELECT first_level_name, second_level_name, data_volume, data_type, class_name, sort_no, id FROM elk_test.sum_data";
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
				esData.setIndex("screen");
				esData.setType("screen");
				esData.setId(result.getString("id"));
				esData.setMapFlag(true);
				esData.setMapData(data);
				dataList.add(esData);
			}
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
		data.setIndex("bruce1");
		data.setType("brucetest1");
		data.setId("000000");
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