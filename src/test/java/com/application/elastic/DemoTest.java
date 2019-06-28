package com.application.elastic;

import com.application.base.all.elastic.entity.ElasticData;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.xpack.XPackClient;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.elasticsearch.xpack.watcher.client.WatcherClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @NAME: DemoTest
 * @DESC:
 * @USER: 孤狼
 * @DATE: 2019年6月27日
 **/
public class DemoTest {
	
	public static void main(String[] args) {
		System.setProperty("org.apache.logging.log4j.simplelog.StatusLogger.level","TRACE");
		// on startup
		TransportClient client;
		try {
			
			Settings settings = Settings.builder()
					.put("cluster.name", "elasticsearch")
					.put("xpack.security.user", "elastic:123456")
					.put("client.transport.sniff", true).build();
			client = new PreBuiltXPackTransportClient(settings)
					.addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.10.180"), 9300));
			
			XPackClient xpackClient = new XPackClient(client);
			WatcherClient watcherClient = xpackClient.watcher();
			
			watcherClient.prepareDeleteWatch();
			
			
			/*
			Settings settings = Settings.builder()
					.put("cluster.name", "elasticsearch")
					.put("client.transport.sniff", true).build();
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(
							new TransportAddress(InetAddress.getByName("192.168.10.180"), 9300));
			*/
			
			ElasticData data = new ElasticData();
			data.setIndex("finaltest");
			data.setType("finaltest");
			data.setId("student");
			Map<String,Object> info=new HashMap<>();
			info.put("name","学生");
			info.put("idcard","98745612336985247"+(int)(10+Math.random()*(30-10+1)));
			info.put("age",""+(int)(10+Math.random()*(30-10+1)));
			info.put("score",""+(int)(10+Math.random()*(100-10+1)));
			data.setMapData(info);
			data.setMapFlag(true);
			IndexResponse response = client.prepareIndex(data.getIndex(), data.getType(), data.getId()).setSource(data.getMapData()).get();
			System.out.println(response.status().getStatus());
			// on shutdown
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void test2(){
	
	}
}
