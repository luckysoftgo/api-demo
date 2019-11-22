package com.application.elastic;

import com.application.base.core.BaseJunit4Test;
import com.application.base.elastic.elastic.query.EsQueryBuilderInstance;
import com.application.base.elastic.elastic.query.EsQueryBuilders;
import com.application.base.elastic.elastic.rest.factory.EsJestSessionPoolFactory;
import com.application.base.elastic.entity.ElasticData;
import com.application.util.GenerateColumn;
import com.application.util.GenerateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @NAME: ZxyDataToEsMain
 * @DESC:
 * @USER: 孤狼
 **/
public class ZxyDataToEsMain extends BaseJunit4Test {
	
	@Autowired
	private EsJestSessionPoolFactory operateFactory;
	//private EsTransportSessionPoolFactory operateFactory;
	
	@Test
	public void cj_company_tags(){
		String index = "cj_company_tags";
		operateData(index,true);
	}
	
	@Test
	public void base_legal_identity(){
		String index = "base_legal_identity";
		operateData(index,true);
	}
	
	/**
	 * 插入数据.
	 * @param tableName
	 * @param async
	 */
	private void operateData(String tableName, boolean async){
		long start=System.currentTimeMillis();
		DBHelper dbHelper=new DBHelper();
		String sql = "SELECT * FROM "+tableName;
		List<ElasticData> dataList=new ArrayList<ElasticData>();
		ResultSet result = dbHelper.stQuery(sql);
		//格式化.
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<GenerateColumn> columnList= null;
		try {
			columnList = GenerateUtils.columnToList(tableName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			while(result.next()){
				Map<String,Object> data = new HashMap<>();
				for (GenerateColumn column : columnList) {
					String type = column.getDbType().toLowerCase();
					if (type.contains("date") || type.contains("timestamp") ||type.contains("datetime")){
						Date date = result.getDate(column.getDbName());
						if (date!=null){
							data.put(column.getDbName(),format.format(date));
						}
					}else{
						data.put(column.getDbName(),result.getObject(column.getDbName()));
					}
				}
				ElasticData esData=new ElasticData();
				esData.setIndex(tableName);
				esData.setType(tableName);
				esData.setId(result.getString(columnList.get(0).getName()));
				esData.setMapFlag(true);
				esData.setMapData(data);
				dataList.add(esData);
			}
			long end=System.currentTimeMillis();
			System.out.println("=============================================================");
			System.out.println("cost time ="+(end-start)/1000);
			System.out.println("=============================================================");
			boolean flag = operateFactory.getElasticSession().addEsDataList(dataList,async);
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
				dbHelper.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取所有的表
	 * @return
	 */
	private List<String> getTables(){
		DBHelper dbHelper=new DBHelper();
		String sql = "select table_name from information_schema.tables where table_schema='sme_integrated_finance_ronghe'" ;
		List<String> columns=new ArrayList<>();
		ResultSet result = dbHelper.stQuery(sql);
		try {
			while(result.next()) {
				columns.add(result.getString("table_name"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				result.close();
				dbHelper.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return columns;
	}
	
	/**
	 * 获取所有的列.
	 * @param tableName
	 * @return
	 */
	private List<String> getColumns(String tableName){
		DBHelper dbHelper=new DBHelper();
		String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_name ='"+tableName+"'" ;
		List<String> columns=new LinkedList<>();
		ResultSet result = dbHelper.stQuery(sql);
		try {
			while(result.next()) {
				columns.add(result.getString("COLUMN_NAME"));
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try {
				result.close();
				dbHelper.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return columns;
	}
	
	
	@Test
	public void searchData(){
		EsQueryBuilderInstance instance = new EsQueryBuilderInstance();
		instance.must(new EsQueryBuilders().match("ent_state", "在业"));
		instance.must(new EsQueryBuilders().match("is_peat", "1"));
		List<ElasticData> resultList = operateFactory.getElasticSession().searcher(instance.listBuilders(),"base_legal_identity","base_legal_identity");
		for (ElasticData  data : resultList) {
			Map<String,Object> map = data.getMapData();
			for (Map.Entry<String,Object> entry:map.entrySet()){
				System.out.println("key:"+ entry.getKey()+",value:"+entry.getValue());
			}
		}
	}
}
