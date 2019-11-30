package com.application.zookeeper;

import com.application.base.config.zookeeper.curator.factory.ZooKeeperSimpleSessionFactory;
import com.application.base.core.BaseJunit4Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : 孤狼
 * @NAME: ZooKeeperTest
 * @DESC:
 **/
public class ZooKeeperTest extends BaseJunit4Test {
	

	@Autowired
	private ZooKeeperSimpleSessionFactory zookeeperFactory;
	
	@Test
	public void test(){
		for (int i = 0; i < 500 ; i++) {
			try {
				String node = "/testNode"+i;
				boolean result = zookeeperFactory.getZooKeeperSession().exists(node);
				if (result){
					System.out.println("存在!");
				}else{
					System.out.println("不存在!");
				}
			}catch (Exception e){
				System.out.println("获取配置信息异常!");
			}
		}
	}
	
}
