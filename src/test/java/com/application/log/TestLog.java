package com.application.log;

import com.application.base.core.result.ResultDataVO;
import com.application.base.utils.LogBackUtils;
import com.application.base.utils.json.JsonConvertUtils;
import com.javabase.demo.dao.test.impl.TestTeacherDaoImpl;
import com.javabase.demo.entity.test.TestTeacher;
import com.javabase.demo.service.test.TestStudentService;
import org.junit.Test;

import com.application.base.core.BaseJunit4Test;
import com.application.base.core.apisupport.BaseResultSupport;
import com.javabase.demo.dao.test.TestStudentDao;


public class TestLog extends BaseJunit4Test{

	@Test
	public void test1() {
		ResultDataVO res =new ResultDataVO("1","test message");
        res.setData("qqqqbbaa");
        String resultJson = JsonConvertUtils.toJson(res, false);
		LogBackUtils.logInfo(BaseResultSupport.class, "successResultJSON", "125", "调用接口成功，返回JSON数据 resultJson:["+resultJson+"]");
        
		System.out.println("======================================================================");
		System.out.println("======================================================================");
		System.out.println("======================================================================");
		LogBackUtils.logTrace(TestStudentDao.class,null,null,"1234567");
		LogBackUtils.logDebug(TestLog.class,null,null,"1234567");
		LogBackUtils.logInfo(TestTeacher.class,null,null,"1234567");
		LogBackUtils.logWarn(TestStudentService.class,null,null,"1234567");
		LogBackUtils.logError(TestTeacherDaoImpl.class,null,null,"1234567");
	}
}
