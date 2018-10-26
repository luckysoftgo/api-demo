package com.javabase.pay.service.handler;


import com.application.base.pay.common.api.PayService;
import com.application.base.pay.common.bean.PayMessage;
import com.application.base.pay.common.bean.PayOutMessage;
import com.application.base.pay.common.exception.PayErrorException;
import com.application.base.pay.union.bean.SDKConstants;

import java.util.Map;

/**
 * @author 孤狼
 */
public class UnionPayMessageHandler extends BasePayMessageHandler {


    public UnionPayMessageHandler(Integer payId) {
        super(payId);
    }

    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        //交易状态
        if (SDKConstants.OK_RESP_CODE.equals(payMessage.getPayMessage().get(SDKConstants.param_respCode))) {
            /////这里进行成功的处理

            return payService.successPayOutMessage(payMessage);
        }

        return payService.getPayOutMessage("fail", "失败");
    }
}
