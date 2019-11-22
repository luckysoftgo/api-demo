package com.application.es.data;

import com.application.base.elastic.entity.ElasticData;
import com.application.base.utils.json.JsonConvertUtils;
import com.application.es.bean.PlanInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @NAME: PlanGuideData
 * @DESC: 计划表数据
 * @USER: 孤狼
 **/
public class PlanInfoData extends Data {
	
	static List<String> planNameList = new ArrayList<>();
	static List<String> planIdList = new ArrayList<>();
	static List<String> planCodeList = new ArrayList<>();
	
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
		for (int i = 1; i <=planCount ; i++) {
			planNameList.add("2020科技创新计划"+i);
			planIdList.add("2019PLAN000"+i);
			planCodeList.add("2020SXSJKJXMBH000"+i);
		}
	}
	
	/**
	 * 获取数据.
	 * @return
	 */
	public static List<ElasticData> getData() {
		List<ElasticData> list = new ArrayList<>();
		for (int i = 0; i <planCount ; i++) {
			ElasticData data = getInstance();
			PlanInfo plan=new PlanInfo();
			plan.setPlanId(planIdList.get(i));
			plan.setPlanName(planNameList.get(i));
			List<String> guidIdList =PlanGuideData.guidIdList;
			plan.setGuidId(getOne(getArr(guidIdList)));
			plan.setpId("");
			plan.setDesc("2020年陕西科技局计划项目"+i);
			plan.setCreateSect("陕西省科技局计划处室部");
			plan.setCreateMan(getOne(Constant.projectApplyManArray));
			plan.setCreateTime(getTime());
			plan.setLeaf(1);
			data.setMapData(bean2map(plan));
			data.setId(getId());
			data.setType("plan_info");
			data.setIndex("plan_info");
			list.add(data);
		}
		return list;
	}
}
