package com.application.es;

import com.application.base.core.BaseJunit4Test;
import com.application.base.elastic.elastic.restclient.factory.EsRestClientSessionPoolFactory;
import com.application.base.elastic.elastic.transport.factory.EsTransportSessionPoolFactory;
import com.application.base.elastic.entity.ElasticData;
import com.application.base.utils.date.DateUtils;
import com.application.elastic.DBHelper;
import com.application.es.data.BasicAppData;
import com.application.es.data.GuideFileData;
import com.application.es.data.MainInfoData;
import com.application.es.data.PlanGuideData;
import com.application.es.data.PlanInfoData;
import com.application.es.data.ProjectInfoData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @NAME: Entrance
 * @DESC:
 * @USER: 孤狼
 **/
public class Entrance extends BaseJunit4Test {
	
	@Autowired
	private EsTransportSessionPoolFactory transportFactory;
	@Autowired
	private EsRestClientSessionPoolFactory jestFactory;
	
	@Test
	public void test1(){
		List<ElasticData> finalList = new ArrayList<>();
		finalList=PlanGuideData.getData();
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
		
		finalList=GuideFileData.getData();
		result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
		
		finalList=PlanInfoData.getData();
		result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
		
		finalList=ProjectInfoData.getData();
		result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
		
		finalList=BasicAppData.getData();
		 result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
		
		finalList=MainInfoData.getData();
		result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	@Test
	public void test2(){
		List<ElasticData> finalList = new ArrayList<>();
		finalList=BasicAppData.getData();
		boolean result=jestFactory.getElasticSession().addEsDataListByProcessor(finalList,false);
		System.out.println("result="+result);
		finalList=GuideFileData.getData();
		result=jestFactory.getElasticSession().addEsDataListByProcessor(finalList,false);
		System.out.println("result="+result);
		finalList=MainInfoData.getData();
		result=jestFactory.getElasticSession().addEsDataListByProcessor(finalList,false);
		System.out.println("result="+result);
		finalList=PlanGuideData.getData();
		result=jestFactory.getElasticSession().addEsDataListByProcessor(finalList,false);
		System.out.println("result="+result);
		finalList=PlanInfoData.getData();
		result=jestFactory.getElasticSession().addEsDataListByProcessor(finalList,false);
		System.out.println("result="+result);
		finalList=ProjectInfoData.getData();
		result=jestFactory.getElasticSession().addEsDataListByProcessor(finalList,false);
		System.out.println("result="+result);
	}
	
	
	@Test
	public void test3(){
		String[] filePaths = new String[]{"E:\\data\\data2017.txt","E:\\data\\data2018.txt","E:\\data\\data2019.txt"};
		List<ElasticData> finalList = new ArrayList<>();
		for (String filePath : filePaths) {
			finalList.addAll(getDataFromFile(filePath));
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	@Test
	public void test4(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		try {
			ResultSet set = dbHelper.stQuery("SELECT projectId,projectName,planCategory,planCategoryName,planItemId,planItemName,guideId,guideName,guideYear,declaratSubId,declaratSubType,declaratSubName,declaratSubArea,industCode,industCategory,registerDate,registerYears,registCapital,reviewTag,reviewNum,approvedTag,approvedNum,passedTag,passedNum,subsidiedAmt,allowanceAmt,totalAmt,projectConvertCount,resultConvertAmt From level_statistics_data");
			while (set.next()){
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				//项目ID
				map.put("projectId",set.getString("projectId"));
				//项目名称
				map.put("projectName",set.getString("projectName"));
				//计划类别
				map.put("planCategory",set.getString("planCategory"));
				//计划类别名称
				map.put("planCategoryName",set.getString("planCategoryName"));
				//计划项目ID
				map.put("planItemId",set.getString("planItemId"));
				//计划项目名称
				map.put("planItemName",set.getString("planItemName"));
				//所属指南ID
				map.put("guideId",set.getString("guideId"));
				//所属指南名称
				map.put("guideName",set.getString("guideName"));
				//所属指南年度
				map.put("guideYear",set.getInt("guideYear"));
				//申报主体ID
				map.put("declaratSubId",set.getString("declaratSubId"));
				//申报主体类别
				map.put("declaratSubType",set.getString("declaratSubType"));
				//申报主体名称
				map.put("declaratSubName",set.getString("declaratSubName"));
				//申报主体所属区域
				map.put("declaratSubArea",set.getString("declaratSubArea"));
				//行业编码
				map.put("industCode",set.getInt("industCode"));
				//行业大类
				map.put("industCategory",set.getString("industCategory"));
				//注册日期
				map.put("registerDate", DateUtils.getDateObjByDateStr(set.getString("registerDate"),DateUtils.YEAR_MONTH_DAY,null));
				//注册年限
				map.put("registerYears",set.getInt("registerYears"));
				//注册资本额
				map.put("registCapital",set.getDouble("registCapital"));
				//评审是否通过
				map.put("reviewTag",set.getString("reviewTag"));
				map.put("reviewNum",set.getInt("reviewNum"));
				//立项是否通过
				map.put("approvedTag",set.getString("approvedTag"));
				map.put("approvedNum",set.getInt("approvedNum"));
				//验收是否通过
				map.put("passedTag",set.getString("passedTag"));
				map.put("passedNum",set.getInt("passedNum"));
				//已补助金额(万元)
				map.put("subsidiedAmt",set.getDouble("subsidiedAmt"));
				//剩余补助金额
				map.put("allowanceAmt",set.getDouble("allowanceAmt"));
				//总补助金额
				map.put("totalAmt",set.getDouble("totalAmt"));
				//项目成果转化数量
				map.put("projectConvertCount",set.getInt("projectConvertCount"));
				//成果转化收益(万元)
				map.put("resultConvertAmt",set.getDouble("resultConvertAmt"));
				
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_statistics_data");
				data.setIndex("level_statistics_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	
	@Test
	public void test5(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		try {
			ResultSet set = dbHelper.stQuery("select a.guideName as guideName ,SUM(a.resultConvertAmt) as resultConvertAmt ,SUM(a.totalAmt) as totalAmt,SUM(a.projectConvertCount) as projectConvertCount FROM cboard.level_statistics_data a GROUP BY a.guideName ");
			while (set.next()){
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				
				//所属指南名称
				map.put("guideName",set.getString("guideName"));
				//总补助金额
				map.put("totalAmt",set.getDouble("totalAmt"));
				//项目成果转化数量
				map.put("projectConvertCount",set.getInt("projectConvertCount"));
				//成果转化收益(万元)
				map.put("resultConvertAmt",set.getDouble("resultConvertAmt"));
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_collect_data");
				data.setIndex("level_collect_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	@Test
	public void test6(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		try {
			ResultSet set = dbHelper.stQuery("select a.declaratSubType,a.guideName, COUNT(a.declaratSubName) as subNameCount FROM cboard.level_statistics_data a GROUP BY a.declaratSubType,a.guideName ");
			while (set.next()){
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				
				//所属指南名称.
				map.put("guideName",set.getString("guideName"));
				//类型.
				map.put("declaratSubType",set.getString("declaratSubType"));
				//數量統計.
				map.put("subNameCount",set.getInt("subNameCount"));
				
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_type_data");
				data.setIndex("level_type_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	@Test
	public void test7(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		try {
			ResultSet set = dbHelper.stQuery("select * FROM cboard.level_guide_data ");
			while (set.next()){
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				map.put("guideName",set.getString("guideName"));
				map.put("enterprise",set.getInt("enterprise"));
				map.put("space",set.getInt("space"));
				map.put("school",set.getInt("school"));
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_guide_data");
				data.setIndex("level_guide_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	@Test
	public void test8(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		try {
			ResultSet set = dbHelper.stQuery("select * FROM cboard.level_category_data ");
			while (set.next()){
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				map.put("planCategoryName",set.getString("planCategoryName"));
				map.put("enterprise",set.getInt("enterprise"));
				map.put("space",set.getInt("space"));
				map.put("school",set.getInt("school"));
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_category_data");
				data.setIndex("level_category_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	@Test
	public void test9(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		try {
			ResultSet set = dbHelper.stQuery("select * FROM cboard.level_planItem_data ");
			while (set.next()){
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				map.put("planItemName",set.getString("planItemName"));
				map.put("enterprise",set.getInt("enterprise"));
				map.put("space",set.getInt("space"));
				map.put("school",set.getInt("school"));
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_planitem_data");
				data.setIndex("level_planitem_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	@Test
	public void test10(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		try {
			ResultSet set = dbHelper.stQuery("select * FROM cboard.level_planItemJedge_data ");
			while (set.next()){
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				map.put("planItemName",set.getString("planItemName"));
				map.put("planCount",set.getInt("planCount"));
				map.put("reviewNum",set.getInt("reviewNum"));
				map.put("approvedNum",set.getInt("approvedNum"));
				map.put("passedNum",set.getInt("passedNum"));
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_planitemjedge_data");
				data.setIndex("level_planitemjedge_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	@Test
	public void test11(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		try {
			ResultSet set = dbHelper.stQuery("select * FROM cboard.level_categorycount_data ");
			while (set.next()){
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				map.put("industCategory",set.getString("industCategory"));
				map.put("subsidiedAmt",set.getInt("subsidiedAmt"));
				map.put("allowanceAmt",set.getInt("allowanceAmt"));
				map.put("totalAmt",set.getInt("totalAmt"));
				map.put("projectConvertCount",set.getInt("projectConvertCount"));
				map.put("resultConvertAmt",set.getInt("resultConvertAmt"));
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_categorycount_data");
				data.setIndex("level_categorycount_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	@Test
	public void test12(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		try {
			ResultSet set = dbHelper.stQuery("select * FROM cboard.level_categoryamt_data ");
			while (set.next()){
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				map.put("industCategory",set.getString("industCategory"));
				map.put("subsidiedAmt",set.getDouble("subsidiedAmt"));
				map.put("allowanceAmt",set.getDouble("allowanceAmt"));
				map.put("totalAmt",set.getDouble("totalAmt"));
				map.put("projectConvertCount",set.getInt("projectConvertCount"));
				map.put("resultConvertAmt",set.getDouble("resultConvertAmt"));
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_categoryamt_data");
				data.setIndex("level_categoryamt_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	
	@Test
	public void test51(){
		List<ElasticData> finalList = new ArrayList<>();
		DBHelper dbHelper =new DBHelper();
		//获得实例.
		ElasticData data = new ElasticData();
		Map<String,Object> map = new HashMap<>();
		
		//所属指南名称
		map.put("guideName","总计");
		//总补助金额
		map.put("totalAmt",163167.00);
		//项目成果转化数量
		map.put("projectConvertCount",7749);
		//成果转化收益(万元)
		map.put("resultConvertAmt",76468);
		data.setMapData(map);
		data.setId(getId());
		data.setMapFlag(true);
		data.setType("level_collect_data");
		data.setIndex("level_collect_data");
		finalList.add(data);
		
		System.out.println("size ="+finalList.size());
		boolean result=transportFactory.getElasticSession().addEsDataList(finalList,false);
		System.out.println("result="+result);
	}
	
	
	/**
	 * 获得数据.
	 * @return
	 */
	private static List<ElasticData> getDataFromFile(String filePath){
		List<ElasticData> finalList = new ArrayList<>();
		try {
			FileReader rd = new FileReader(filePath);
			BufferedReader reader = new BufferedReader(rd);
			String line=null;
			while ((line=reader.readLine())!=null){
				String[] array = line.split("\t");
				//获得实例.
				ElasticData data = new ElasticData();
				Map<String,Object> map = new HashMap<>();
				//项目ID
				map.put("projectId",array[0]);
				//项目名称
				map.put("projectName",array[1]);
				//计划类别
				map.put("planCategory",array[2]);
				//计划类别名称
				map.put("planCategoryName",array[3]);
				//计划项目ID
				map.put("planItemId",array[4]);
				//计划项目名称
				map.put("planItemName",array[5]);
				//所属指南ID
				map.put("guideId",array[6]);
				//所属指南名称
				map.put("guideName",array[7]);
				//所属指南年度
				map.put("guideYear",format(array[8]));
				//申报主体ID
				map.put("declaratSubId",array[9]);
				//申报主体类别
				map.put("declaratSubType",array[10]);
				//申报主体名称
				map.put("declaratSubName",array[11]);
				//申报主体所属区域
				map.put("declaratSubArea",array[12]);
				//行业编码
				map.put("industCode",format(array[13]));
				//行业大类
				map.put("industCategory",array[14]);
				//注册日期
				map.put("registerDate", DateUtils.getDateObjByDateStr(array[15].replaceAll("/","-"),DateUtils.YEAR_MONTH_DAY,null));
				//注册年限
				map.put("registerYears",format(array[16]));
				//注册资本额
				map.put("registCapital",format(array[17]));
				//评审是否通过
				map.put("reviewTag",array[18]);
				map.put("reviewNum",format(array[19]));
				//立项是否通过
				map.put("approvedTag",array[20]);
				map.put("approvedNum",format(array[21]));
				//验收是否通过
				map.put("passedTag",array[22]);
				map.put("passedNum",format(array[23]));
				//已补助金额(万元)
				map.put("subsidiedAmt",format(array[24]));
				//剩余补助金额
				map.put("allowanceAmt",format(array[25]));
				//总补助金额
				map.put("totalAmt",format(array[26]));
				//项目成果转化数量
				map.put("projectConvertCount",format(array[27]));
				//成果转化收益(万元)
				map.put("resultConvertAmt",format(array[28]));
				
				data.setMapData(map);
				data.setId(getId());
				data.setMapFlag(true);
				data.setType("level_statistics_data");
				data.setIndex("level_statistics_data");
				finalList.add(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return finalList;
	}
	
	
	/**
	 * 获得id
	 * @return
	 */
	public static String getId(){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid.toUpperCase();
	}
	
	/**
	 * 格式化数据
	 * @param str
	 */
	public static Double format(String str){
		if (str.equals("0.0") || str.isEmpty()){
			return 0.0;
		}else{
			return Double.parseDouble(str);
		}
	}
}
