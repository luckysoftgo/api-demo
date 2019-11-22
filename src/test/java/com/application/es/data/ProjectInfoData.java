package com.application.es.data;

import com.application.base.elastic.entity.ElasticData;
import com.application.base.utils.json.JsonConvertUtils;
import com.application.es.bean.ProjectInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @NAME: PlanGuideData
 * @DESC: 项目表数据
 * @USER: 孤狼
 **/
public class ProjectInfoData extends Data {
	
	static List<String> projectNameList = new ArrayList<>();
	static List<String> projectIdList = new ArrayList<>();
	
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
		for (int i = 1; i <=projectCount ; i++) {
			projectNameList.add("2019年陕西省申报项目"+i);
			projectIdList.add("2020SXSKJJPROJECT000"+i);
		}
	}
	
	/**
	 * 获取数据.
	 * @return
	 */
	public static List<ElasticData> getData() {
		List<ElasticData> list = new ArrayList<>();
		for (int i = 0; i <projectCount ; i++) {
			ElasticData data = getInstance();
			ProjectInfo project=new ProjectInfo();
			project.setProjId(projectIdList.get(i));
			project.setProjName(projectNameList.get(i));
			
			List<String> planIdList=PlanInfoData.planIdList;
			List<String> planCodeList=PlanInfoData.planCodeList;
			project.setPlanId(getOne(getArr(planIdList)));
			project.setProjNum(getOne(getArr(planCodeList)));
			
			project.setMainTag("SXSKJJ");
			project.setProjDesc("这个是申请陕西省科技局拨款的项目"+i);
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(2017, 1, 10, 0, 0, 0);
			project.setStartTime(calendar.getTime());
			calendar.set(2021, 1, 10, 0, 0, 0);
			project.setEndTime(calendar.getTime());
			project.setProjMan(getOne(Constant.projectManArray));
			project.setApplyMan(getOne(Constant.projectApplyManArray));
			calendar.set(2018, 10, 10, 0, 0, 0);
			project.setApplyDate(calendar.getTime());
			project.setStatus(1);
			
			data.setMapData(bean2map(project));
			data.setId(getId());
			data.setType("project_info");
			data.setIndex("project_info");
			list.add(data);
		}
		return list;
	}
}
