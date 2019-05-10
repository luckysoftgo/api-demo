package com.javabase.demo.elastic;

import com.application.base.all.elastic.EsClientBuilder;
import com.application.base.all.elastic.elastic.factory.ElasticSessionOperateFactory;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @NAME: ElasticInitFactory
 * @DESC: 初始化elastic的实例
 * @USER: admin
 **/
@Component("elasticFactory")
public class ElasticInitFactory {
	
	/**
	 * 初始化elastic信息
	 */
	@PostConstruct
	public void initElastic() {
		String inputPath = "properties/es.properties";
		TransportClient settingClient = null;
		if (settingClient == null) {
			synchronized (TransportClient.class) {
				if (settingClient == null) {
					EsClientBuilder client = new EsClientBuilder();
					settingClient = client.initSettingsClient(inputPath);
				}
			}
		}
		ElasticSessionOperateFactory operateFactory=new ElasticSessionOperateFactory();
		operateFactory.setTransportClient(settingClient);
	}
	
}
