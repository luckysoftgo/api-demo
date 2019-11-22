package com.application.es.data;

import com.application.base.elastic.entity.ElasticData;
import com.application.base.utils.json.JsonConvertUtils;
import com.application.es.bean.GuideFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @NAME: PlanGuideData
 * @DESC: 指南文件表数据
 * @USER: 孤狼
 **/
public class GuideFileData extends Data {
	
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
		for (int i = 0; i <guidCount ; i++) {
			ElasticData data = getInstance();
			GuideFile file=new GuideFile();
			file.setFileId(getId());
			file.setGuidId(PlanGuideData.guidIdList.get(i));
			file.setFilePath("/home/bruce/data/");
			file.setVersion("V1.0");
			file.setStatus(1);
			file.setCreateSect("陝西省科技局规划处室");
			file.setCreateMan(getOne(Constant.projectApplyManArray));
			file.setCreateTime(getTime());
			data.setMapData(bean2map(file));
			data.setId(getId());
			data.setType("guid_file");
			data.setIndex("guid_file");
			list.add(data);
		}
		return list;
	}
}
