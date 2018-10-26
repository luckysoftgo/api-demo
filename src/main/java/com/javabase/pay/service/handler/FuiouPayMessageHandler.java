package com.javabase.pay.service.handler;

import com.application.base.pay.common.api.PayService;
import com.application.base.pay.common.bean.PayMessage;
import com.application.base.pay.common.bean.PayOutMessage;
import com.application.base.pay.common.exception.PayErrorException;

import java.util.Map;

/**
 * @author 孤狼
 */
public class FuiouPayMessageHandler extends BasePayMessageHandler {

    public FuiouPayMessageHandler(Integer payId) {
        super(payId);
    }

    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        //交易状态
        if ("0000".equals(payMessage.getPayMessage().get("order_pay_code"))){
            /////这里进行成功的处理

            return PayOutMessage.json().content("success","成功").build();
        }

        return PayOutMessage.json().content("fail", "失败").build();
    }
}
