
package com.javabase.pay.controller;

import com.application.base.pay.ali.bean.AliTransactionType;
import com.application.base.pay.common.api.Callback;
import com.application.base.pay.common.api.PayConfig;
import com.application.base.pay.common.bean.*;
import com.application.base.pay.common.util.MatrixToImageWriter;
import com.application.base.pay.common.util.str.StringUtils;
import com.application.base.pay.wechat.bean.WxTransactionType;
import com.javabase.pay.dao.ApyAccountRepository;
import com.javabase.pay.entity.ApyAccount;
import com.javabase.pay.entity.PayType;
import com.javabase.pay.request.QueryOrder;
import com.javabase.pay.service.ApyAccountService;
import com.javabase.pay.service.PayResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @desc 发起支付入口
 * @author: 孤狼
 */
@RestController
@RequestMapping
public class PayController {

    @Resource
    private ApyAccountService service;

    @RequestMapping("/")
    public ModelAndView index(){
        return new ModelAndView("/index.html");
    }

    /**
     * 这里模拟账户信息增加
     *
     * @param account
     * @return 支付账户信息
     */
    @RequestMapping("add")
    public Map<String, Object> add(ApyAccount account) {
        ApyAccountRepository.apyAccounts.put(account.getPayId(), account);
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("account", account);
        return data;
    }

    /**
     * 跳到支付页面
     * 针对实时支付,即时付款
     *
     * @param payId           账户id
     * @param transactionType 交易类型， 这个针对于每一个 支付类型的对应的几种交易方式
     * @param bankType        针对刷卡支付，卡的类型，类型值
     * @param price       金额
     * @return 跳到支付页面
     */
    @RequestMapping(value = "toPay.html", produces = "text/html;charset=UTF-8")
    public String toPay(HttpServletRequest request,Integer payId, String transactionType, String bankType, BigDecimal price) {
        //获取对应的支付账户操作工具（可根据账户id）
        PayResponse payResponse = service.getPayResponse(payId);

        PayOrder order = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, "22222222233", PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType(transactionType));
        // ------  微信H5使用----
        order.setSpbillCreateIp(request.getHeader("X-Real-IP"));
        StringBuffer requestURL = request.getRequestURL();
        //设置网页地址
        order.setWapUrl(requestURL.substring(0, requestURL.indexOf("/") > 0 ? requestURL.indexOf("/") : requestURL.length() ));
        //设置网页名称
        order.setWapName("在线充值");
        // ------  微信H5使用----

        //此处只有刷卡支付(银行卡支付)时需要
        if (StringUtils.isNotEmpty(bankType)) {
            order.setBankType(bankType);
        }
        Map orderInfo = payResponse.getService().orderInfo(order);
        return payResponse.getService().buildRequest(orderInfo, MethodType.POST);
    }

    /**
     * 跳到支付页面
     * 针对实时支付,即时付款
     * @return 跳到支付页面
     */
    @RequestMapping(value = "toWxPay.html", produces = "text/html;charset=UTF-8")
    public String toWxPay(HttpServletRequest request) {
        //获取对应的支付账户操作工具（可根据账户id）
        PayResponse payResponse = service.getPayResponse(2);

        PayOrder order = new PayOrder("订单title", "摘要",  new BigDecimal(0.01) , UUID.randomUUID().toString().replace("-", ""),  WxTransactionType.MWEB);
        order.setSpbillCreateIp(request.getHeader("X-Real-IP"));
        StringBuffer requestURL = request.getRequestURL();
        //设置网页地址
        order.setWapUrl(requestURL.substring(0, requestURL.indexOf("/") > 0 ? requestURL.indexOf("/") : requestURL.length() ));
        //设置网页名称
        order.setWapName("在线充值");

        Map orderInfo = payResponse.getService().orderInfo(order);
        return payResponse.getService().buildRequest(orderInfo, MethodType.POST);
    }


    /**
     * 公众号支付
     *
     *
     * @param payId           账户id
     * @param openid openid
     * @param price 金额
     * @return 返回jsapi所需参数
     */
    @RequestMapping(value = "jsapi" )
    public Map toPay(Integer payId, String openid, BigDecimal price) {
        //获取对应的支付账户操作工具（可根据账户id）
        PayResponse payResponse = service.getPayResponse(payId);
        PayOrder order = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType("JSAPI"));
        order.setOpenId(openid);
        Map orderInfo = payResponse.getService().orderInfo(order);
        orderInfo.put("code", 0);
        return orderInfo;
    }


    /**
     * 刷卡付,pos主动扫码付款(条码付)
     * @param payId           账户id
     * @param transactionType 交易类型， 这个针对于每一个 支付类型的对应的几种交易方式
     * @param authCode        授权码，条码等
     * @param price       金额
     * @return 支付结果
     */
    @RequestMapping(value = "microPay")
    public Map<String, Object> microPay(Integer payId, String transactionType, BigDecimal price, String authCode) throws IOException {
        //获取对应的支付账户操作工具（可根据账户id）
        PayResponse payResponse = service.getPayResponse(payId);

        PayOrder order = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType(transactionType));
        //设置授权码，条码等
        order.setAuthCode(authCode);
        //支付结果
        Map<String, Object> params = payResponse.getService().microPay(order);
        PayConfig storage = payResponse.getService().getPayConfigStorage();
        //校验
        if (payResponse.getService().verify(params)) {
            PayMessage message = new PayMessage(params, storage.getPayType(), storage.getMsgType().name());
            //支付校验通过后的处理
            payResponse.getRouter().route(message);
        }
        //这里开发者自行处理
        return params;
    }

    /**
     * 获取二维码图像
     * 二维码支付
     * @param payId           账户id
     * @param transactionType 交易类型， 这个针对于每一个 支付类型的对应的几种交易方式
     * @param price       金额
     * @return 二维码图像
     */
    @RequestMapping(value = "toQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
    public byte[] toWxQrPay(Integer payId, String transactionType, BigDecimal price) throws IOException {
        //获取对应的支付账户操作工具（可根据账户id）
        PayResponse payResponse = service.getPayResponse(payId);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(payResponse.getService().genQrPay(new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, System.currentTimeMillis()+"", PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType(transactionType))), "JPEG", baos);
        return baos.toByteArray();
    }

    /**
     * 获取一码付二维码图像
     * 二维码支付
     * @param wxPayId           微信账户id
     * @param aliPayId           支付宝id
     * @param price       金额
     * @return 二维码图像
     */
    @RequestMapping(value = "toWxAliQrPay.jpg", produces = "image/jpeg;charset=UTF-8")
    public byte[] toWxAliQrPay(Integer wxPayId,Integer aliPayId, BigDecimal price, HttpServletRequest request) throws IOException {
        //获取对应的支付账户操作工具（可根据账户id）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //这里为需要生成二维码的地址
        StringBuffer url = request.getRequestURL();
        url = new StringBuffer(url.substring(0, url.lastIndexOf(request.getRequestURI())));
         url .append("/toWxAliPay.html?");
        if (null != wxPayId){
            url.append("wxPayId=").append(wxPayId).append("&");
        }
        if (null != aliPayId){
            url.append("aliPayId=").append(aliPayId).append("&");
        }
        url.append("price=").append(price);

        ImageIO.write(MatrixToImageWriter.writeInfoToJpgBuff(url.toString()), "JPEG", baos);
        return baos.toByteArray();
    }

    /**
     *
     * 支付宝与微信平台的判断 并进行支付的转跳
     * @param wxPayId           微信账户id
     * @param aliPayId           支付宝id
     * @param price       金额
     * @return 支付宝与微信平台的判断
     */
    @RequestMapping(value = "toWxAliPay.html", produces = "text/html;charset=UTF-8")
    public String toWxAliPay(Integer wxPayId,Integer aliPayId, BigDecimal price, HttpServletRequest request) throws IOException {
        StringBuilder html = new StringBuilder();

        //这里为WAP支付的地址，根据需求自行修改
        StringBuffer url = request.getRequestURL();
        url = new StringBuffer(url.substring(0, url.lastIndexOf(request.getRequestURI())));
        url .append("/toPay.html");

        html.append("<html><head></head><body><script type=\"text/javascript\"> ");
//        html.append("\nalert('111');\n");

        if (null != wxPayId){
            html.append("if(isWxPay()){\n");
            html.append("window.location='");
            //这里使用H5支付，公众号支付是否可以？请开发者自行尝试
            html.append(url.toString()).append("?payId=").append(wxPayId).append("&transactionType=").append(WxTransactionType.MWEB.getType()).append("&price=").append(price);
            html.append("';\n }else\n");
        }

        if (null != aliPayId) {
            html.append("if(isAliPay()){\n");
            html.append("window.location='");
            html.append(url).append("?payId=").append(aliPayId).append("&transactionType=").append(AliTransactionType.WAP.getType()).append("&price=").append(price);
            html.append("';\n } else");
        }
        html.append("{\n alert('请使用微信或者支付宝App扫码'+window.navigator.userAgent.toLowerCase());\n }");
        //判断是否为微信
        html.append("function isWxPay(){ \n" +
                " var ua = window.navigator.userAgent.toLowerCase();\n" +
                " if(ua.match(/MicroMessenger/i) == 'micromessenger'){\n" +
                " return true;\n" +
                " }\n" +
                " return false;\n" +
                "} \n");
        //判断是否为支付宝
        html.append("function isAliPay(){\n" +
                " var ua = window.navigator.userAgent.toLowerCase();\n" +
                " if(ua.match(/AlipayClient/i) =='alipayclient'){\n" +
                "  return true;\n" +
                " }\n" +
                "  return false;\n" +
                "}</script> <body></html>");
        return html.toString();
    }



    /**
     * 获取支付预订单信息
     *
     * @param payId           支付账户id
     * @param transactionType 交易类型
     * @return 支付预订单信息
     */
    @RequestMapping("getOrderInfo")
    public Map<String, Object> getOrderInfo(Integer payId, String transactionType, BigDecimal price) {
        //获取对应的支付账户操作工具（可根据账户id）
        PayResponse payResponse = service.getPayResponse(payId);
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        PayOrder order = new PayOrder("订单title", "摘要", null == price ? new BigDecimal(0.01) : price, UUID.randomUUID().toString().replace("-", ""), PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType(transactionType));
        data.put("orderInfo", payResponse.getService().orderInfo(order));
        return data;
    }


    /**
     * 支付回调地址
     *
     * @param request
     * @return 支付是否成功
     */
    @RequestMapping(value = "payBack{payId}.json")
    public String payBack(HttpServletRequest request, @PathVariable Integer payId) throws IOException {
        //根据账户id，获取对应的支付账户操作工具
        PayResponse payResponse = service.getPayResponse(payId);
        PayConfig storage = payResponse.getStorage();
        //获取支付方返回的对应参数
        Map<String, Object> params = payResponse.getService().getParameter2Map(request.getParameterMap(), request.getInputStream());
//        Map<String, Object> params = JSONObject.parseObject("{\"bizType\":\"000201\",\"signPubKeyCert\":\"-----BEGIN CERTIFICATE-----\\r\\nMIIEQzCCAyugAwIBAgIFEBJJZVgwDQYJKoZIhvcNAQEFBQAwWDELMAkGA1UEBhMC\\r\\nQ04xMDAuBgNVBAoTJ0NoaW5hIEZpbmFuY2lhbCBDZXJ0aWZpY2F0aW9uIEF1dGhv\\r\\ncml0eTEXMBUGA1UEAxMOQ0ZDQSBURVNUIE9DQTEwHhcNMTcxMTAxMDcyNDA4WhcN\\r\\nMjAxMTAxMDcyNDA4WjB3MQswCQYDVQQGEwJjbjESMBAGA1UEChMJQ0ZDQSBPQ0Ex\\r\\nMQ4wDAYDVQQLEwVDVVBSQTEUMBIGA1UECxMLRW50ZXJwcmlzZXMxLjAsBgNVBAMU\\r\\nJTA0MUBaMjAxNy0xMS0xQDAwMDQwMDAwOlNJR05AMDAwMDAwMDEwggEiMA0GCSqG\\r\\nSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDDIWO6AESrg+34HgbU9mSpgef0sl6avr1d\\r\\nbD/IjjZYM63SoQi3CZHZUyoyzBKodRzowJrwXmd+hCmdcIfavdvfwi6x+ptJNp9d\\r\\nEtpfEAnJk+4quriQFj1dNiv6uP8ARgn07UMhgdYB7D8aA1j77Yk1ROx7+LFeo7rZ\\r\\nDdde2U1opPxjIqOPqiPno78JMXpFn7LiGPXu75bwY2rYIGEEImnypgiYuW1vo9UO\\r\\nG47NMWTnsIdy68FquPSw5FKp5foL825GNX3oJSZui8d2UDkMLBasf06Jz0JKz5AV\\r\\nblaI+s24/iCfo8r+6WaCs8e6BDkaijJkR/bvRCQeQpbX3V8WoTLVAgMBAAGjgfQw\\r\\ngfEwHwYDVR0jBBgwFoAUz3CdYeudfC6498sCQPcJnf4zdIAwSAYDVR0gBEEwPzA9\\r\\nBghggRyG7yoBATAxMC8GCCsGAQUFBwIBFiNodHRwOi8vd3d3LmNmY2EuY29tLmNu\\r\\nL3VzL3VzLTE0Lmh0bTA5BgNVHR8EMjAwMC6gLKAqhihodHRwOi8vdWNybC5jZmNh\\r\\nLmNvbS5jbi9SU0EvY3JsMjQ4NzIuY3JsMAsGA1UdDwQEAwID6DAdBgNVHQ4EFgQU\\r\\nmQQLyuqYjES7qKO+zOkzEbvdFwgwHQYDVR0lBBYwFAYIKwYBBQUHAwIGCCsGAQUF\\r\\nBwMEMA0GCSqGSIb3DQEBBQUAA4IBAQAujhBuOcuxA+VzoUH84uoFt5aaBM3vGlpW\\r\\nKVMz6BUsLbIpp1ho5h+LaMnxMs6jdXXDh/du8X5SKMaIddiLw7ujZy1LibKy2jYi\\r\\nYYfs3tbZ0ffCKQtv78vCgC+IxUUurALY4w58fRLLdu8u8p9jyRFHsQEwSq+W5+bP\\r\\nMTh2w7cDd9h+6KoCN6AMI1Ly7MxRIhCbNBL9bzaxF9B5GK86ARY7ixkuDCEl4XCF\\r\\nJGxeoye9R46NqZ6AA/k97mJun//gmUjStmb9PUXA59fR5suAB5o/5lBySZ8UXkrI\\r\\npp/iLT8vIl1hNgLh0Ghs7DBSx99I+S3VuUzjHNxL6fGRhlix7Rb8\\r\\n-----END CERTIFICATE-----\",\"orderId\":\"20171213224128\",\"signature\":\"l8xBYSoMNzt01DDa9/JYcrQKWxN5tasUgSxf6NNsQK5t+DqMr2G9qhHXnDg5bEzeRyTFP4bM3htX9RTRhXYDy7EEsL46ZD4ib5I6mp2wXx+26zscUcLdJUiddkY5eFvQK4tPC8blw7Y6p858yiVJpHgbOK3cONhS7vwPJtK2jMbkY+GATu3aZ4iygkQc75cG+EW8nJQVwLNh7q9A6A6II18EFxR7XubdlIHXv/InVaS6ux8Wh2nmQlhRRnLtHq1ri7v1QPlu2FzM+kaf7/fn61iGr8zEPj62NzWDXue62LUfb4kTRgdkcJnfJBJl8vjZ/w93UtsnK3zjzJC/Nu+wCw==\",\"txnSubType\":\"01\",\"traceNo\":\"492156\",\"accNo\":\"6221********0000\",\"settleAmt\":\"1000\",\"settleCurrencyCode\":\"156\",\"settleDate\":\"1213\",\"txnType\":\"01\",\"encoding\":\"UTF-8\",\"version\":\"5.1.0\",\"queryId\":\"511712132241284921568\",\"accessType\":\"0\",\"exchangeRate\":\"0\",\"respMsg\":\"success\",\"traceTime\":\"1213224128\",\"txnTime\":\"20171213224128\",\"merId\":\"777290058154626\",\"currencyCode\":\"156\",\"respCode\":\"00\",\"signMethod\":\"01\",\"txnAmt\":\"1000\"}");

        if (null == params) {
            return payResponse.getService().getPayOutMessage("fail", "失败").toMessage();
        }

        //校验
        if (payResponse.getService().verify(params)) {
            PayMessage message = new PayMessage(params, storage.getPayType(), storage.getMsgType().name());
            PayOutMessage outMessage = payResponse.getRouter().route(message);
            return outMessage.toMessage();
        }

        return payResponse.getService().getPayOutMessage("fail", "失败").toMessage();
    }

    /**
     * 查询
     *
     * @param order 订单的请求体
     * @return 返回查询回来的结果集，支付方原值返回
     */
    @RequestMapping("query")
    public Map<String, Object> query(QueryOrder order) {
        PayResponse payResponse = service.getPayResponse(order.getPayId());
        return payResponse.getService().query(order.getTradeNo(), order.getOutTradeNo());
    }
    /**
     * 查询
     *
     * @param order 订单的请求体
     * @return 返回查询回来的结果集，支付方原值返回
     */
/*    @RequestMapping("unionRefundOrConsumeUndo")
    public Map<String, Object> unionQuery(UnionQueryOrder order,String transactionType) {
        PayResponse payResponse = service.getPayResponse(order.getPayId());
        UnionPayService service =  (UnionPayService)payResponse.getService();

        return service.unionRefundOrConsumeUndo(order,UnionTransactionType.valueOf(transactionType));
    }*/

    /**
     * 交易关闭接口
     *
     * @param order 订单的请求体
     * @return 返回支付方交易关闭后的结果
     */
    @RequestMapping("close")
    public Map<String, Object> close(QueryOrder order) {
        PayResponse payResponse = service.getPayResponse(order.getPayId());
        return payResponse.getService().close(order.getTradeNo(), order.getOutTradeNo());
    }

    /**
     * 申请退款接口
     *
     * @param order 订单的请求体
     * @return 返回支付方申请退款后的结果
     */
    @RequestMapping("refund")
    public Map<String, Object> refund(Integer payId ,RefundOrder order) {
        PayResponse payResponse = service.getPayResponse(payId);

//        return payResponse.getService().refund(order.getTradeNo(), order.getOutTradeNo(), order.getRefundAmount(), order.getTotalAmount());
        return payResponse.getService().refund(order);
    }

    /**
     * 查询退款
     *
     * @param order 订单的请求体
     * @return 返回支付方查询退款后的结果
     */
    @RequestMapping("refundquery")
    public Map<String, Object> refundquery(QueryOrder order) {
        PayResponse payResponse = service.getPayResponse(order.getPayId());
        return payResponse.getService().refundquery(order.getTradeNo(), order.getOutTradeNo());
    }

    /**
     * 下载对账单
     *
     * @param order 订单的请求体
     * @return 返回支付方下载对账单的结果
     */
    @RequestMapping("downloadbill")
    public Object downloadbill(QueryOrder order) {
        PayResponse payResponse = service.getPayResponse(order.getPayId());
        return payResponse.getService().downloadbill(order.getBillDate(), order.getBillType());
    }


    /**
     * 通用查询接口，根据 TransactionType 类型进行实现,此接口不包括退款
     *
     * @param order 订单的请求体
     * @return 返回支付方对应接口的结果
     */
    @RequestMapping("secondaryInterface")
    public Map<String, Object> secondaryInterface(QueryOrder order) {
        PayResponse payResponse = service.getPayResponse(order.getPayId());
        TransactionType type = PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType(order.getTransactionType());
        return payResponse.getService().secondaryInterface(order.getTradeNoOrBillDate(), order.getOutTradeNoBillType(), type, new Callback<Map<String, Object>>() {
            @Override
            public Map<String, Object> perform(Map<String, Object> map) {
                return map;
            }
        });
    }


}
