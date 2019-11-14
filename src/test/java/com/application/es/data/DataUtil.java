package com.application.es.data;

import java.util.List;

/**
 * @NAME: DataUtil
 * @DESC:
 * @USER: 孤狼
 * @DATE: 2019年7月15日
 **/
public class DataUtil {
	
	/**
	 * 测试结果.
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i <100 ; i++) {
			System.out.println("612326198509"+DataUtil.getNumber(6));
		}
	}
	
	/**
	 * 获得数组
	 */
	public static String[] getArray(List<String> list){
		String[] array = new String[list.size()];
		for (int i = 0; i <list.size() ; i++) {
			array[i] = list.get(i);
		}
		return array;
	}
	
	/**
	 * 第一级分类.
	 * @param firstType
	 * @return
	 */
	public static String getType(String firstType){
		String[] datas=null;
		if (firstType.equalsIgnoreCase("科技")){
			datas=new String[]{"科技","信息技术","网络科技","科技发展"};
			return getOne(datas);
		}
		if (firstType.equalsIgnoreCase("文化")){
			datas=new String[]{"文化","文化传播","文化发展","影视文化"};
			return getOne(datas);
		}
		if (firstType.equalsIgnoreCase("传媒广告")){
			datas=new String[]{"传媒","文化传媒","广告传媒","广告","影视广告"};
			return getOne(datas);
		}
		if (firstType.equalsIgnoreCase("咨询")){
			datas=new String[]{"咨询","企业管理咨询","商务咨询","技术咨询","投资咨询","投资管理咨询","教育咨询","信息技术咨询","财务咨询"};
			return getOne(datas);
		}
		if (firstType.equalsIgnoreCase("管理")){
			datas=new String[]{"投资管理","企业管理","商务管理","资产管理","人力资源管理","餐饮管理"};
			return getOne(datas);
		}
		if (firstType.equalsIgnoreCase("商贸")){
			datas=new String[]{"贸易","科贸","商贸","工贸"};
			return getOne(datas);
		}
		return null;
	}
	
	/**
	 * 获得数组的摸个值.
	 * @param array
	 * @return
	 */
	public static String getOne(String[] array){
		int index=(int)(Math.random()* array.length);
		String result = array[index];
		return result;
	}
	
	/**
	 * 获得随机数
	 * @return
	 */
	public static Integer getNumber(int count){
		StringBuffer buffer = new StringBuffer("1");
		for (int i = 0; i <count-1 ; i++) {
			buffer.append("0");
		}
		int random = Integer.parseInt(buffer.toString());
		int number = (int)((Math.random()*9+1)*random);
		return number;
	}
	
}
