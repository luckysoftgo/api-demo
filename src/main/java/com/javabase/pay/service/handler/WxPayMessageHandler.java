package com.javabase.pay.service.handler;

import com.application.base.pay.common.api.PayService;
import com.application.base.pay.common.bean.PayMessage;
import com.application.base.pay.common.bean.PayOutMessage;
import com.application.base.pay.common.exception.PayErrorException;

import java.util.Map;

/**
 * 微信支付回调处理器
 * @author 孤狼
 */
public class WxPayMessageHandler extends BasePayMessageHandler {

    public WxPayMessageHandler(Integer payId) {
        super(payId);
    }

    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        //交易状态
        if ("SUCCESS".equals(payMessage.getPayMessage().get("result_code"))){
            /////这里进行成功的处理
            return  payService.getPayOutMessage("SUCCESS", "OK");
        }

        return  payService.getPayOutMessage("FAIL", "失败");
    }
}
