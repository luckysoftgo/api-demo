package com.application.es.data;


import com.application.base.elastic.entity.ElasticData;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @NAME: Data
 * @DESC:
 * @USER: 孤狼
 **/
public abstract class Data {
	/**
	 * 指南多少.
	 */
	static Integer guidCount = 20;
	/**
	 * 计划多少.
	 */
	static Integer planCount = 60;
	/**
	 * 项目多少.
	 */
	static Integer projectCount = 16000;
	/**
	 * 申报多少.
	 */
	static Integer applyCount = 100;
	
	/**
	 * 把JavaBean转化为map
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> bean2map(Object bean){
		try {
			Map<String,Object> map = new HashMap<>();
			//获取JavaBean的描述器
			BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
			//获取属性描述器
			PropertyDescriptor[] pds = b.getPropertyDescriptors();
			//对属性迭代
			for (PropertyDescriptor pd : pds) {
				//属性名称
				String propertyName = pd.getName();
				//属性值,用getter方法获取
				Method m = pd.getReadMethod();
				//用对象执行getter方法获得属性值
				Object properValue = m.invoke(bean);
				//把属性名-属性值 存到Map中
				map.put(propertyName, properValue);
			}
			return map;
		}catch (Exception e){
			e.printStackTrace();
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
	 * 获得数组的摸个值.
	 * @param array
	 * @return
	 */
	public static Integer getOne(Integer[] array){
		int index=(int)(Math.random()* array.length);
		Integer result = array[index];
		return result;
	}
	
	/**
	 * 获得实例
	 * @return
	 */
	public static ElasticData getInstance(){
		ElasticData data =new ElasticData();
		data.setMapFlag(true);
		return data;
	}
	
	/**
	 * 获得数组
	 */
	public static String[] getArr(List<String> list){
		String[] array = new String[list.size()];
		for (int i = 0; i <list.size() ; i++) {
			array[i] = list.get(i);
		}
		return array;
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
	 * 获得id
	 * @return
	 */
	public static Date getTime(){
		return new Date();
	}
}
