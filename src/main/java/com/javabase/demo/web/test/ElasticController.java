package com.javabase.demo.web.test;

import com.application.base.core.common.BaseController;
import com.application.base.core.result.ResultDataVO;
import com.application.base.elastic.elastic.transport.factory.EsTransportSessionPoolFactory;
import com.application.base.elastic.entity.ElasticData;
import com.application.base.utils.json.JsonConvertUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @NAME: ElasticController
 * @DESC: Elastic
 * @USER: admin
 **/
@RestController
@RequestMapping("/elastic")
public class ElasticController extends BaseController {
	
	
	@Autowired(required = false)
	private EsTransportSessionPoolFactory sessionPoolFactory;
	
	
	/**
	 * 通过配置文件获得信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/settingElastic")
	public void settingElastic(HttpServletRequest request, HttpServletResponse response) {
		ElasticData data = new ElasticData();
		data.setIndex("elastic");
		data.setType("apitest");
		data.setId("999999999999");
		Map<String,Object> info=new HashMap<>();
		info.put("info1","es测试");
		info.put("info2","es调试");
		info.put("info3","es上线");
		info.put("info4","es读写");
		info.put("info5","es分库");
		data.setData(JsonConvertUtils.toJson(info));
		boolean flag = sessionPoolFactory.getElasticSession().addEsData(data);
		System.out.printf("flag="+flag);
	}
	
	/**
	 * 通过配置文件获得信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/settingElastics")
	public void settingElastics(HttpServletRequest request, HttpServletResponse response) {
		List<ElasticData> datas=new ArrayList<>();
		for (int i = 0; i <100 ; i++) {
			ElasticData data = new ElasticData();
			data.setIndex("elastic");
			data.setType("apitest");
			data.setId("setting"+i);
			Map<String,Object> info=new HashMap<>();
			info.put("info1","es测试");
			info.put("info2","es调试");
			info.put("info3","es上线");
			info.put("info4","es读写");
			info.put("info5","es分库");
			data.setData(JsonConvertUtils.toJson(info));
			datas.add(data);
		}
		boolean flag = sessionPoolFactory.getElasticSession().addEsDataList(datas,false);
		System.out.printf("flag="+flag);
	}
	
	/**
	 * 通过参数传入的方式获得.
	 * @param request
	 * @param response
	 */
	@RequestMapping("/paramElastic")
	public void paramElastic(HttpServletRequest request, HttpServletResponse response) {
		ElasticData data = new ElasticData();
		data.setIndex("elastic");
		data.setType("apitest");
		data.setId("8888888888888");
		Map<String,Object> info=new HashMap<>();
		info.put("info1","es测试1");
		info.put("info2","es调试2");
		info.put("info3","es上线3");
		info.put("info4","es读写4");
		info.put("info5","es分库5");
		data.setData(JsonConvertUtils.toJson(info));
		boolean flag = sessionPoolFactory.getElasticSession().addEsData(data);
		
		System.out.printf("flag="+flag);
	}
	
	/**
	 * 通过参数传入的方式获得.
	 * @param request
	 * @param response
	 */
	@RequestMapping("/paramElastics")
	public void paramElastics(HttpServletRequest request, HttpServletResponse response) {
		List<ElasticData> datas=new ArrayList<>();
		for (int i = 0; i <100 ; i++) {
			ElasticData data = new ElasticData();
			data.setIndex("elastic");
			data.setType("apitest");
			data.setId("param"+i);
			Map<String,Object> info=new HashMap<>();
			info.put("info1","es测试");
			info.put("info2","es调试");
			info.put("info3","es上线");
			info.put("info4","es读写");
			info.put("info5","es分库");
			data.setData(JsonConvertUtils.toJson(info));
			datas.add(data);
		}
		boolean flag = sessionPoolFactory.getElasticSession().addEsDataList(datas,false);
		System.out.printf("flag="+flag);
	}
	
	
	/************************************************************************select info ***************************************************************************************/
	/**
	 * 获得对象.
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getInfo",method = RequestMethod.GET, produces =  "application/json;charset=UTF-8")
	public ResultDataVO getInfo(HttpServletRequest request, HttpServletResponse response) {
		ElasticData data = new ElasticData();
		data.setIndex("elastic");
		data.setType("apitest");
		data.setId("8888888888888");
		String result=sessionPoolFactory.getElasticSession().getDataById(data);
		System.out.println("esde 的结果是:"+result);
		ResultDataVO dataVO=new ResultDataVO("200","OK");
		dataVO.setData(result);
		return dataVO;
	}
	
	
	/**
	 * 获得对象.
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getInfos",method = RequestMethod.GET, produces =  "application/json;charset=UTF-8")
	public ResultDataVO getInfos(HttpServletRequest request, HttpServletResponse response) {
		QueryBuilder query = QueryBuilders.matchQuery("user", "bruce");
		List<ElasticData> data = sessionPoolFactory.getElasticSession().searcher(query,"bruce1","11111111111");
		ResultDataVO dataVO=new ResultDataVO("200","OK");
		dataVO.setData(data);
		return dataVO;
	}
}
