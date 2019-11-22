package com.application.es.data;

import com.application.base.elastic.entity.ElasticData;
import com.application.base.utils.json.JsonConvertUtils;
import com.application.es.bean.PlanGuide;

import java.util.ArrayList;
import java.util.List;

/**
 * @NAME: PlanGuideData
 * @DESC: 计划指南数据
 * @USER: 孤狼
 * @DATE: 2019年7月15日
 **/
public class PlanGuideData extends Data {
	
	/**
	 * 指南名称
	 */
	static List<String> guidNameList = new ArrayList<>();
	/**
	 * 指南id
	 */
	static List<String> guidIdList = new ArrayList<>();
	
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
		for (int i = 1; i <=guidCount ; i++) {
			guidNameList.add("2020年度西安市科技计划项目申报指南"+i);
			guidIdList.add("2019GUIDPLAN000"+i);
		}
	}
	
	/**
	 * 获取数据.
	 * @return
	 */
	public static List<ElasticData> getData() {
		List<ElasticData> list = new ArrayList<>();
		for (int i = 0; i <10 ; i++) {
			ElasticData data = getInstance();
			PlanGuide guide=new PlanGuide();
			guide.setGuidId(guidIdList.get(i));
			guide.setGuidName(guidNameList.get(i));
			guide.setGuidDesc("2019年度西安市科技计划项目申报指南");
			guide.setGuidYear("2020");
			guide.setTypeCount(DataUtil.getNumber(2));
			guide.setItemCount(DataUtil.getNumber(2));
			guide.setPublishSect("陝西省科技局规划处室");
			guide.setPublishMan(getOne(Constant.projectApplyManArray));
			guide.setPublishTime(getTime());
			data.setMapData(bean2map(guide));
			data.setId(getId());
			data.setType("plan_guid");
			data.setIndex("plan_guid");
			list.add(data);
		}
		return list;
	}
}
