package com.javabase.demo.elastic;

import com.application.base.all.elastic.EsClientBuilder;
import com.application.base.all.elastic.elastic.factory.ElasticSessionOperateFactory;
import com.application.base.all.elastic.exception.ElasticException;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @NAME: ElasticInitFactory
 * @DESC: 初始化elastic的实例
 * @USER: 孤狼
 **/
@Component("elasticFactory")
public class ElasticInitFactory {
	
	/**
	 * Elastic 实例.
	 */
	public ElasticSessionOperateFactory operateFactory;
	
	/**
	 * 配置文件路径
	 */
	String inputPath = "properties/es.properties";
	
	/**
	 * 初始化elastic信息
	 */
	@PostConstruct
	public void initElastic() {
		//解决抛错的异常信息设置.
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		TransportClient settingClient = null;
		if (settingClient == null) {
			synchronized (ElasticInitFactory.class) {
				if (settingClient == null) {
					EsClientBuilder client = new EsClientBuilder();
					settingClient = client.initSettingsClient(inputPath);
					operateFactory=new ElasticSessionOperateFactory();
					operateFactory.setTransportClient(settingClient);
				}
			}
		}
	}
	
	/**
	 * 获得操作ES的实例对象.
	 * @return
	 */
	public ElasticSessionOperateFactory getSettingInstance() throws ElasticException {
		if (operateFactory==null){
			throw new ElasticException("通过配置文件获取ElasticSearch的操作实例失败了.");
		}
		//有的话,直接返回.
		return operateFactory;
	}
	
	/**
	 * 获得操作ES的实例对象.
	 * @return
	 */
	public ElasticSessionOperateFactory getParamInstance(String clusterName,String serverIPs,boolean isAppend) throws ElasticException{
		//解决抛错的异常信息设置.
		System.setProperty("es.set.netty.runtime.available.processors", "false");
		if (operateFactory==null){
			TransportClient paramClient = null;
			if (paramClient == null) {
				synchronized (ElasticInitFactory.class) {
					if (paramClient == null) {
						EsClientBuilder client = new EsClientBuilder();
						paramClient = client.initParamsClient(clusterName,serverIPs,isAppend);
						operateFactory=new ElasticSessionOperateFactory();
						operateFactory.setTransportClient(paramClient);
						return operateFactory;
					}
				}
			}
			if (operateFactory==null){
				throw new ElasticException("通过配信息获得ElasticSearch的操作实例失败了.");
			}
		}
		//有的话,直接返回.
		return operateFactory;
	}
}

