package com.javabase.pay.service.handler;

import com.application.base.pay.common.api.PayMessageHandler;

/**
 *@author 孤狼
 */
public abstract class BasePayMessageHandler implements PayMessageHandler {
    //支付账户id
    private Integer payId;

    public BasePayMessageHandler(Integer payId) {
        this.payId = payId;
    }

    public Integer getPayId() {
        return payId;
    }
}
