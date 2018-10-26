package com.javabase.demo.constant;

import com.application.base.core.constant.CoreConstants;
import com.application.base.core.result.ResultDataVO;

/**
 * 常量类
 * @author rocky
 */
public class Constant extends CoreConstants{
	
    public static String TIME_OUT = "10204";

    @SuppressWarnings("rawtypes")
	public static ResultDataVO SESSION_TIMEOUT = new ResultDataVO(TIME_OUT,"登陆超时");

}
