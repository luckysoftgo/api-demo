package com.javabase.pay.service.handler;

import com.alibaba.fastjson.JSON;
import com.application.base.pay.common.api.PayService;
import com.application.base.pay.common.bean.PayMessage;
import com.application.base.pay.common.bean.PayOutMessage;
import com.application.base.pay.common.exception.PayErrorException;

import java.util.Map;

/**
 * @author 孤狼
 */
public class YouDianPayMessageHandler extends BasePayMessageHandler {

    public YouDianPayMessageHandler(Integer payId) {
        super(payId);
    }

    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        //交易状态
        Map<String, Object> message = payMessage.getPayMessage();
        //上下文对象中获取账单
        //AmtApply amtApply = (AmtApply)context.get("amtApply");
        //日志存储
        //amtPaylogService.createAmtPaylogByCallBack(amtApply,  message.toString());

        if ("SUCCESS".equals(message.get("return_code"))){
            /////这里进行成功的处理，因没有返回金额
        }
        return  PayOutMessage.text().content(JSON.toJSONString(message)).build();
    }
}
