package com.application.elastic;

import com.application.base.all.elastic.elastic.rest.factory.EsJestSessionPoolFactory;
import com.application.base.all.elastic.entity.ElasticData;
import com.application.base.core.BaseJunit4Test;
import com.application.base.utils.json.JsonConvertUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @NAME: ElasticTest
 * @DESC:
 * @USER: 孤狼
 * @DATE: 2019年5月14日
 **/
public class ElasticTest extends BaseJunit4Test {
	
	@Autowired
	private EsJestSessionPoolFactory operateFactory;
	//private EsTransportSessionPoolFactory operateFactory;
	
	
	@Test
	public void factory(){
		ElasticData data = new ElasticData();
		data.setIndex("hahaha");
		data.setType("hahaha");
		data.setId("hahaha");
		Map<String,Object> info=new HashMap<>();
		info.put("info1","测试");
		info.put("info2","调试");
		info.put("info3","上线");
		info.put("info4","读写");
		info.put("info5","分库");
		data.setData(JsonConvertUtils.toJson(info));
		boolean flag = operateFactory.getElasticSession().addEsData(data);
		System.out.printf("flag="+flag);
	}
	
	@Test
	public void test1(){
		ElasticData data = new ElasticData();
		data.setIndex("finaltest");
		data.setType("finaltest");
		data.setId("student");
		Map<String,Object> info=new HashMap<>();
		info.put("name","学生");
		info.put("idcard","98745612336985247"+(int)(10+Math.random()*(30-10+1)));
		info.put("age",""+(int)(10+Math.random()*(30-10+1)));
		info.put("score",""+(int)(10+Math.random()*(100-10+1)));
		data.setData(JsonConvertUtils.toJson(info));
		boolean flag = operateFactory.getElasticSession().addEsData(data);
		System.out.println("flag="+flag);
	}
	
	@Test
	public void data1000000(){
		long start = System.currentTimeMillis();
		List<ElasticData> datas=new ArrayList<>();
		for (int i = 0; i <200000 ; i++) {
			ElasticData data = new ElasticData();
			data.setIndex("data1000000");
			data.setType("data1000000");
			data.setId("student"+i);
			Map<String,Object> info=new HashMap<>();
			info.put("name","学生"+i);
			info.put("idcard","8888888888888"+(int)(10+Math.random()*(100-10+1)));
			info.put("age",""+(int)(10+Math.random()*(80-10+1)));
			info.put("score",""+(int)(10+Math.random()*(150-10+1)));
			info.put("create_time",new Date());
			data.setMapFlag(true);
			data.setMapData(info);
			datas.add(data);
		}
		boolean flag = operateFactory.getElasticSession().addEsDataListByProcessor(datas,true);
		long end = System.currentTimeMillis();
		System.out.println("flag="+flag+", cost time ="+(end-start)/1000);
	}
	
	@Test
	public void search(){
		QueryBuilder builder = QueryBuilders.matchAllQuery();
		List<ElasticData>  datas = operateFactory.getElasticSession().searcher(builder,"finaltest","finaltest");
		System.out.println("size="+datas.size());
		for (int i = 0; i < datas.size(); i++) {
			System.out.println(datas.get(i).getData());
		}
	}
}
