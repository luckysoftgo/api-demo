package com.application.es.data;

import com.application.base.elastic.entity.ElasticData;
import com.application.base.utils.json.JsonConvertUtils;
import com.application.es.bean.BasicAppInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @NAME: PlanGuideData
 * @DESC: 申报主体基本数据
 * @USER: 孤狼
 **/
public class BasicAppData extends Data {
	
	static List<String> projectNameList = new ArrayList<>();
	static List<String> projectIdList = new ArrayList<>();
	static List<String> projectCodeList = new ArrayList<>();
	/**
	 * 测试.
	 * @param args
	 */
	public static void main(String[] args) {
		List<ElasticData> list = getData();
		System.out.println("size="+list.size()+ JsonConvertUtils.toJson(list));
	}
	
	/**
	 * 存放数据.
	 */
	static {
		for (int i = 1; i <=applyCount ; i++) {
			projectNameList.add("2019年陕西省申报项目"+i);
			projectIdList.add("2020SXSKJJAPPLY000"+i);
			projectCodeList.add("2020KJJXMSBN"+i);
		}
	}
	
	/**
	 * 获取数据.
	 * @return
	 */
	public static List<ElasticData> getData() {
		List<ElasticData> list = new ArrayList<>();
		for (int i = 0; i <applyCount ; i++) {
			ElasticData data = getInstance();
			Map<String,Object> map = new HashMap<>();
			BasicAppInfo baisc=new BasicAppInfo();
			
			baisc.setApplyId(projectIdList.get(i));
			baisc.setSocialCode(projectCodeList.get(i));
			baisc.setApplyMainName(projectNameList.get(i));
			
			baisc.setAddress("陕西省高新区科技二路"+i+"号");
			String type = getOne(Constant.busFirstScopArray);
			baisc.setBusiScope(type);
			Calendar calendar = Calendar.getInstance();
			calendar.set(2012, 1, 10, 0, 0, 0);
			baisc.setRegTime(calendar.getTime());
			baisc.setLegalName(getOne(Constant.projectApplyManArray));
			baisc.setLegalNo(getOne(Constant.legalNoArray));
			baisc.setFirstType(type);
			baisc.setSecondType(DataUtil.getType(type));
			baisc.setApplyType(getOne(Constant.applyArray));
			
			baisc.setMoney(DataUtil.getNumber(7));
			calendar.set(2010, 8, 10, 0, 0, 0);
			baisc.setCreateTime(calendar.getTime());
			baisc.setCreater(getOne(Constant.projectManArray));
			calendar.set(2018, 8, 10, 0, 0, 0);
			baisc.setUpdateTime(calendar.getTime());
			baisc.setUpdater(getOne(Constant.projectApplyManArray));
			baisc.setDelete(0);
			baisc.setRemark("项目基本信息");
			
			data.setMapData(bean2map(baisc));
			data.setId(getId());
			data.setType("basic_apply_info");
			data.setIndex("basic_apply_info");
			list.add(data);
		}
		return list;
	}
}
