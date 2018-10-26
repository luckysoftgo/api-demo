package com.javabase.pay.entity;

import com.application.base.pay.ali.api.AliPayConfig;
import com.application.base.pay.ali.api.AliPayService;
import com.application.base.pay.ali.bean.AliTransactionType;
import com.application.base.pay.common.api.PayService;
import com.application.base.pay.common.bean.BasePayType;
import com.application.base.pay.common.bean.TransactionType;
import com.application.base.pay.fuiou.api.FuiouPayConfig;
import com.application.base.pay.fuiou.api.FuiouPayService;
import com.application.base.pay.fuiou.bean.FuiouTransactionType;
import com.application.base.pay.union.api.UnionPayConfig;
import com.application.base.pay.union.api.UnionPayService;
import com.application.base.pay.union.bean.UnionTransactionType;
import com.application.base.pay.wechat.api.WxPayConfig;
import com.application.base.pay.wechat.api.WxPayService;
import com.application.base.pay.wechat.bean.WxTransactionType;
import com.application.base.pay.youdian.api.YouDianPayConfig;
import com.application.base.pay.youdian.api.YouDianPayService;
import com.application.base.pay.youdian.bean.YoudianTransactionType;

/**
 * 支付类型
 * @author 孤狼
 */
public enum PayType implements BasePayType {

    aliPay{
        /**
         *  @see com.application.base.pay.ali.api.AliPayService 17年更新的版本,旧版本请自行切换
         * @param apyAccount
         * @return
         */
        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            AliPayConfig aliPayConfigStorage = new AliPayConfig();
            aliPayConfigStorage.setPId(apyAccount.getPartner());
            aliPayConfigStorage.setAppId(apyAccount.getAppId());
            aliPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
            aliPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            aliPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
            aliPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            aliPayConfigStorage.setSignType(apyAccount.getSignType());
            aliPayConfigStorage.setSeller(apyAccount.getSeller());
            aliPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            aliPayConfigStorage.setMsgType(apyAccount.getFileType());
            aliPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            aliPayConfigStorage.setTest(apyAccount.isTest());
            return new AliPayService(aliPayConfigStorage);
        }

        @Override
        public TransactionType getTransactionType(String transactionType) {
            // com.egzosn.pay.ali.before.bean.AliTransactionType 17年更新的版本,旧版本请自行切换

            // AliTransactionType 17年更新的版本,旧版本请自行切换
            return AliTransactionType.valueOf(transactionType);
        }


    },wxPay {
        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            WxPayConfig wxPayConfigStorage = new WxPayConfig();
            wxPayConfigStorage.setMchId(apyAccount.getPartner());
            wxPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
            wxPayConfigStorage.setAppId(apyAccount.getAppId());
            wxPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            wxPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
            wxPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            wxPayConfigStorage.setSignType(apyAccount.getSignType());
            wxPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            wxPayConfigStorage.setMsgType(apyAccount.getFileType());
            wxPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            wxPayConfigStorage.setTest(apyAccount.isTest());
            return  new WxPayService(wxPayConfigStorage);
        }

        /**
         * 根据支付类型获取交易类型
         * @param transactionType 类型值
         * @see WxTransactionType
         * @return
         */
        @Override
        public TransactionType getTransactionType(String transactionType) {
            return WxTransactionType.valueOf(transactionType);
        }
    },youdianPay {
        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            // TODO 2017/1/23 14:12 author: egan  集群的话,友店可能会有bug。暂未测试集群环境
            YouDianPayConfig wxPayConfigStorage = new YouDianPayConfig();
            wxPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            wxPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
//            wxPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
//            wxPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            wxPayConfigStorage.setSignType(apyAccount.getSignType());
            wxPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            wxPayConfigStorage.setMsgType(apyAccount.getFileType());
            wxPayConfigStorage.setSeller(apyAccount.getSeller());
            wxPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            wxPayConfigStorage.setTest(apyAccount.isTest());
            return  new YouDianPayService(wxPayConfigStorage);
        }

        /**
         * 根据支付类型获取交易类型
         * @param transactionType 类型值
         * @see YoudianTransactionType
         * @return
         */
        @Override
        public TransactionType getTransactionType(String transactionType) {

            return YoudianTransactionType.valueOf(transactionType);
        }
    },fuiou{

        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            FuiouPayConfig fuiouPayConfigStorage = new FuiouPayConfig();
            fuiouPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
            fuiouPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            fuiouPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
            fuiouPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            fuiouPayConfigStorage.setSignType(apyAccount.getSignType());
            fuiouPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            fuiouPayConfigStorage.setMsgType(apyAccount.getFileType());
            fuiouPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            fuiouPayConfigStorage.setTest(apyAccount.isTest());
            return new FuiouPayService(fuiouPayConfigStorage);
        }

        @Override
        public TransactionType getTransactionType(String transactionType) {
            return FuiouTransactionType.valueOf(transactionType);
        }


    },unionPay{

        @Override
        public PayService getPayService(ApyAccount apyAccount) {
            UnionPayConfig unionPayConfigStorage = new UnionPayConfig();
            unionPayConfigStorage.setMerId(apyAccount.getPartner());
            unionPayConfigStorage.setCertSign(true);
            unionPayConfigStorage.setKeyPublic(apyAccount.getPublicKey());
            unionPayConfigStorage.setKeyPrivate(apyAccount.getPrivateKey());
            unionPayConfigStorage.setNotifyUrl(apyAccount.getNotifyUrl());
            unionPayConfigStorage.setReturnUrl(apyAccount.getReturnUrl());
            unionPayConfigStorage.setSignType(apyAccount.getSignType());
            unionPayConfigStorage.setPayType(apyAccount.getPayType().toString());
            unionPayConfigStorage.setMsgType(apyAccount.getFileType());
            unionPayConfigStorage.setInputCharset(apyAccount.getInputCharset());
            unionPayConfigStorage.setTest(apyAccount.isTest());
            return new UnionPayService(unionPayConfigStorage);
        }

        @Override
        public TransactionType getTransactionType(String transactionType) {
            return UnionTransactionType.valueOf(transactionType);
        }


    };

    public abstract PayService getPayService(ApyAccount apyAccount);


}
