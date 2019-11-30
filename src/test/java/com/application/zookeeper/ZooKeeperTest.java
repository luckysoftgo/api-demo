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
				String node = "/super/testNode"+i;
				boolean result = zookeeperFactory.getZooKeeperSession().createNode(node);
				if (result){
					System.out.println("节点"+node+"存在!");
					result = zookeeperFactory.getZooKeeperSession().deleteNode(node,false);
					if (result){
						System.out.println(node+"删除成功");
					}
				}else{
					System.out.println("不存在!");
				}
			}catch (Exception e){
				System.out.println("获取配置信息异常!");
			}
		}
	}
	
}
