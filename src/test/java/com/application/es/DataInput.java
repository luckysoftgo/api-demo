package com.application.es;

import com.application.elastic.DBHelper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @NAME: DataInput
 * @DESC:
 * @USER: 孤狼
 **/
public class DataInput {
	
	/**
	 * 运行程序.
	 * @param args
	 */
	public static void main(String[] args) {
		DBHelper dbHelper =new DBHelper();
		try {
			Connection conn = dbHelper.getConn();
			Statement smt =conn.createStatement();
			List<String> sqls =getSqlList();
			for (String str:sqls ) {
				smt.addBatch(str);
			}
			int[] count =smt.executeBatch();
			System.out.println("count="+count.length);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取sql集合.
	 */
	private static List<String> getSqlList(){
		List<String> list = new ArrayList<>();
		try {
			FileReader rd = new FileReader("E:\\data\\dataAll.txt");
			BufferedReader reader = new BufferedReader(rd);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] array = line.split("\t");
				StringBuffer buffer = new StringBuffer("insert into level_statistics_data(projectId,projectName,planCategory,planCategoryName,planItemId,planItemName,guideId,guideName,guideYear,declaratSubId,declaratSubType,declaratSubName,declaratSubArea,industCode,industCategory,registerDate,registerYears,registCapital,reviewTag,reviewNum,approvedTag,approvedNum,passedTag,passedNum,subsidiedAmt,allowanceAmt,totalAmt,projectConvertCount,resultConvertAmt) values ( ");
				//获得实例.
				Map<String, Object> map = new HashMap<>();
				
				//项目ID
				buffer.append(addPrix(array[0])).append(",");
				//项目名称
				buffer.append(addPrix(array[1])).append(",");
				//计划类别
				buffer.append(addPrix(array[2])).append(",");
				//计划类别名称
				buffer.append(addPrix(array[3])).append(",");
				//计划项目ID
				buffer.append(addPrix(array[4])).append(",");
				//计划项目名称
				buffer.append(addPrix(array[5])).append(",");
				//所属指南ID
				buffer.append(addPrix(array[6])).append(",");
				//所属指南名称
				buffer.append(addPrix(array[7])).append(",");
				//所属指南年度
				buffer.append(doubleFormat(array[8])).append(",");
				//申报主体ID
				buffer.append(addPrix(array[9])).append(",");
				//申报主体类别
				buffer.append(addPrix(array[10])).append(",");
				//申报主体名称
				buffer.append(addPrix(array[11])).append(",");
				//申报主体所属区域
				buffer.append(addPrix(array[12])).append(",");
				//行业编码
				buffer.append(doubleFormat(array[13])).append(",");
				//行业大类
				buffer.append(addPrix(array[14])).append(",");
				//注册日期
				//buffer.append(DateUtils.getDateObjByDateStr(array[15].replaceAll("/" , "-"), DateUtils.YEAR_MONTH_DAY, null)).append(",");
				String registerDate=array[15]+"";
				if (registerDate.isEmpty()){
					buffer.append(addPrix("2017-9-28")).append(",");
				}else{
					buffer.append(addPrix(array[15].replaceAll("/" , "-"))).append(",");
				}
				//注册年限
				buffer.append(doubleFormat(array[16])).append(",");
				//注册资本额
				buffer.append(doubleFormat(array[17])).append(",");
				//评审是否通过
				buffer.append(addPrix(array[18])).append(",");
				buffer.append(IntegerFormat(array[19])).append(",");
				//立项是否通过
				buffer.append(addPrix(array[20])).append(",");
				buffer.append(IntegerFormat(array[21])).append(",");
				//验收是否通过
				buffer.append(addPrix(array[22])).append(",");
				buffer.append(IntegerFormat(array[23])).append(",");
				//已补助金额(万元)
				buffer.append(doubleFormat(array[24])).append(",");
				//剩余补助金额
				buffer.append(doubleFormat(array[25])).append(",");
				//总补助金额
				buffer.append(doubleFormat(array[26])).append(",");
				//项目成果转化数量
				buffer.append(doubleFormat(array[27])).append(",");
				//成果转化收益(万元)
				buffer.append(doubleFormat(array[28]));
				buffer.append(");");
				
				list.add(buffer.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**
	 * 处理值.
	 * @param str
	 * @return
	 */
	private static String addPrix(String str){
		return "'"+str+"'";
	}
	
	/**
	 * 格式化数据
	 * @param str
	 */
	private static Integer IntegerFormat(String str){
		if (str.equals("0.0") || str.isEmpty()){
			return 0;
		}else{
			return Integer.parseInt(str);
		}
	}
	
	/**
	 * 格式化数据
	 * @param str
	 */
	private static Double doubleFormat(String str){
		if (str.equals("0.0") || str.isEmpty()){
			return 0.0;
		}else{
			return Double.parseDouble(str);
		}
	}
	
}
