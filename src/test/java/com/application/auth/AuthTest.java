package com.application.auth;

import com.application.base.auth.check.impl.CheckSourcePoolServiceImpl;
import com.application.base.core.BaseJunit4Test;
import com.application.base.core.datasource.impl.cache.MutilDefaultCacheReadAndWriteDataSessionFactory;
import com.application.base.auth.entity.SourceVO;
import com.application.base.cache.redis.api.RedisSession;
import com.application.base.auth.util.CacheType;
import com.application.base.auth.util.SourceType;
import com.application.base.utils.json.JsonConvertUtils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @DESC  信息描述.
 * @author 孤狼.
 */
public class AuthTest  extends BaseJunit4Test {
	
	
	@Autowired
	private MutilDefaultCacheReadAndWriteDataSessionFactory factory;
	
	/**
	 * 注册到对应的xml文件中,就可以配合使用.
	 */
	@Autowired
	private CheckSourcePoolServiceImpl sourcePoolService;
	
	/**
	 * 测试结果.
	 */
	@Test
	public void testAuth(){
		RedisSession redisSession = factory.getRedisSessionFactory().getRedisSession();
		SourceVO sourceVO = new SourceVO();
		sourceVO.setVoName("userCache");
		Map<String,Object> mapVals = new HashMap<>(64);
		mapVals.put("userName","rockygogo");
		mapVals.put("userPass","123456789");
		sourceVO.setMapValues(mapVals);
		sourceVO.setVoTime(24*60*60);
		sourcePoolService.setRedisSession(redisSession);
		String key  = sourcePoolService.saveSourceVOInfo(sourceVO, SourceType.LOGIN_INFO ,CacheType.REDIS_TYPE,24*60*60);
		System.out.println("key = "+key);
		
		SourceVO sVo = sourcePoolService.getSourceVOByKey(key, SourceType.LOGIN_INFO ,CacheType.REDIS_TYPE);
		System.out.println(sVo.getVoName()+","+sVo.getVoTime()+",values ="+JsonConvertUtils.toJson(sVo));
	}
	
}
