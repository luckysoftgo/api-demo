package com.application.elastic;

import com.application.base.all.elastic.elastic.transport.factory.EsTransportSessionPoolFactory;
import com.application.base.all.elastic.entity.ElasticData;
import com.application.base.core.BaseJunit4Test;
import com.application.base.utils.json.JsonConvertUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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
	private EsTransportSessionPoolFactory operateFactory;
	
	
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
	public void test100(){
		List<ElasticData> datas=new ArrayList<>();
		for (int i = 1000; i <1500 ; i++) {
			ElasticData data = new ElasticData();
			data.setIndex("finaltest");
			data.setType("finaltest");
			data.setId("student"+i);
			Map<String,Object> info=new HashMap<>();
			info.put("name","学生"+i);
			info.put("idcard","98745612336985247"+(int)(10+Math.random()*(30-10+1)));
			info.put("age",""+(int)(10+Math.random()*(30-10+1)));
			info.put("score",""+(int)(10+Math.random()*(100-10+1)));
			data.setData(info);
			datas.add(data);
		}
		boolean flag = operateFactory.getElasticSession().addEsDataList(datas);
		System.out.println("flag="+flag);
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
