package com.javabase.pay.service;

import com.application.base.pay.common.api.PayConfig;
import com.application.base.pay.common.api.PayMessageHandler;
import com.application.base.pay.common.api.PayMessageRouter;
import com.application.base.pay.common.api.PayService;
import com.application.base.pay.common.bean.FileType;
import com.application.base.pay.common.http.HttpConfig;
import com.javabase.pay.entity.ApyAccount;
import com.javabase.pay.entity.PayType;
import com.javabase.pay.service.handler.*;
import com.javabase.pay.service.interceptor.AliPayMessageInterceptor;
import com.javabase.pay.service.interceptor.YoudianPayMessageInterceptor;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import javax.annotation.Resource;

/**
 * 支付响应对象
 * @author: 孤狼
 */
public class PayResponse {

    //@Resource
    private AutowireCapableBeanFactory spring;

    private PayConfig storage;

    private PayService service;

    private PayMessageRouter router;

    
    public PayResponse() {
    }

    /**
     * 初始化支付配置
     * @param apyAccount 账户信息
     * @see ApyAccount 对应表结构详情--》 /pay-java-demo/resources/apy_account.sql
     */
    public void init(ApyAccount apyAccount) {
        //根据不同的账户类型 初始化支付配置
        this.service = apyAccount.getPayType().getPayService(apyAccount);
        this.storage = service.getPayConfigStorage();
        //这里设置http请求配置
//        service.setRequestTemplateConfigStorage(getHttpConfigStorage());
        buildRouter(apyAccount.getPayId());
    }

    /**
     * 获取http配置，如果配置为null则为默认配置，无代理,无证书的请求方式。
     *  此处非必需
     * @param apyAccount 账户信息
     * @return 请求配置
     */
    public HttpConfig getHttpConfigStorage(ApyAccount apyAccount){
        HttpConfig httpConfigStorage = new HttpConfig();
     /* 网路代理配置 根据需求进行设置*/
//        //http代理地址
//        httpConfigStorage.setHttpProxyHost("192.168.1.69");
//        //代理端口
//        httpConfigStorage.setHttpProxyPort(3308);
//        //代理用户名
//        httpConfigStorage.setHttpProxyUsername("user");
//        //代理密码
//        httpConfigStorage.setHttpProxyPassword("password");

        //设置ssl证书路径
        httpConfigStorage.setKeystorePath(apyAccount.getKeystorePath());
        //设置ssl证书对应的密码
        httpConfigStorage.setStorePassword(apyAccount.getStorePassword());
        return httpConfigStorage;
    }


    /**
     * 配置路由
     * @param payId 指定账户id，用户多微信支付多支付宝支付
     */
    private void buildRouter(Integer payId) {
        router = new PayMessageRouter(this.service);
        router
                .rule()
                //消息类型
                .msgType(FileType.TEXT.getKey())
                //支付账户事件类型
                .payType(PayType.aliPay.name())
                //拦截器
                .interceptor(new AliPayMessageInterceptor())
                //处理器
                .handler(autowire(new AliPayMessageHandler(payId)))
                .end()
                .rule()
                .msgType(FileType.XML.name())
                .payType(PayType.wxPay.name())
                .handler(autowire(new WxPayMessageHandler(payId)))
                .end()
                .rule()
                .msgType(FileType.JSON.name())
                .payType(PayType.youdianPay.name())
                .interceptor(new YoudianPayMessageInterceptor()) //拦截器
                .handler(autowire(new YouDianPayMessageHandler(payId)))
                .end()
                .rule()
                .msgType(FileType.XML.name())
                .payType(PayType.fuiou.name())
                .handler(autowire(new FuiouPayMessageHandler(payId)))
                .end()
                .rule()
                .msgType(FileType.JSON.name())
                .payType(PayType.unionPay.name())
                .handler(autowire(new UnionPayMessageHandler(payId)))
                .end()
        ;
    }


    private PayMessageHandler autowire(PayMessageHandler handler) {
        spring.autowireBean(handler);
        return handler;
    }

    public PayConfig getStorage() {
        return storage;
    }

    public PayService getService() {
        return service;
    }

    public PayMessageRouter getRouter() {
        return router;
    }
}
