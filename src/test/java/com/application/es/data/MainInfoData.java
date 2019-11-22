package com.application.es.data;

import com.application.base.elastic.entity.ElasticData;
import com.application.base.utils.json.JsonConvertUtils;
import com.application.es.bean.MainInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @NAME: PlanGuideData
 * @DESC: 項目汇总表数据
 * @USER: 孤狼
 **/
public class MainInfoData extends Data {
	
	
	/**
	 * 测试.
	 * @param args
	 */
	public static void main(String[] args) {
		List<ElasticData> list = getData();
		System.out.println("size="+list.size()+ JsonConvertUtils.toJson(list));
	}
	
	/**
	 * 获取数据.
	 * @return
	 */
	public static List<ElasticData> getData() {
		List<ElasticData> list = new ArrayList<>();
		for (int i = 0; i <projectCount ; i++) {
			ElasticData data = getInstance();
			Map<String,Object> map = new HashMap<>();
			MainInfo main=new MainInfo();
			main.setInfoId(getId());
			main.setProjId(ProjectInfoData.projectIdList.get(i));
			main.setProjName(ProjectInfoData.projectNameList.get(i));
			
			//计划
			List<String> planIdList=PlanInfoData.planIdList;
			List<String> planNameList=PlanInfoData.planNameList;
			main.setPlanId(getOne(getArr(planIdList)));
			main.setPlanName(getOne(getArr(planNameList)));
			
			//指导
			List<String> guidIdList =PlanGuideData.guidIdList;
			List<String> guidNameList=PlanGuideData.guidNameList;
			main.setGuidId(getOne(getArr(guidIdList)));
			main.setGuidName(getOne(getArr(guidNameList)));
			main.setGuidYear("2020");
			
			//項目
			List<String> projectIdList=BasicAppData.projectIdList;
			List<String> projectNameList=BasicAppData.projectNameList;
			main.setApplyId(getOne(getArr(projectIdList)));
			main.setApplyType(getOne(Constant.applyArray));
			main.setApplyMainName(getOne(getArr(projectNameList)));
			main.setZoneCode(getOne(Constant.addressArray));
			String busCode=getOne(Constant.busFirstScopArray);
			
			main.setBusinCode(busCode);
			main.setItemCode(DataUtil.getType(busCode));
			
			main.setMoney(DataUtil.getNumber(8));
			main.setSocialCode("2020KJJXMSBN"+i);
			main.setJudeStatus(1);
			main.setApproveStatus(1);
			main.setPassStatus(1);
			
			main.setAlreadyMoney(DataUtil.getNumber(6));
			main.setLastMoney(DataUtil.getNumber(8));
			main.setTotalMoney(DataUtil.getNumber(8));
			main.setItemCount(DataUtil.getNumber(2));
			main.setFinalCount(DataUtil.getNumber(2));
			main.setCreateTime(new Date());
			
			data.setMapData(bean2map(main));
			data.setId(getId());
			data.setType("main_info");
			data.setIndex("main_info");
			list.add(data);
		}
		return list;
	}
}
