
package com.javabase.pay.service;

import com.javabase.pay.dao.ApyAccountRepository;
import com.javabase.pay.entity.ApyAccount;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 孤狼
 */
@Service
public class ApyAccountService {

    //@Resource
    private ApyAccountRepository dao;

    @Resource
    private AutowireCapableBeanFactory spring;

    /**
     * 缓存
     */
    private final static Map<Integer, PayResponse> payResponses = new HashMap<Integer, PayResponse>();

    /**
     * 这里简单初始化，引入orm等框架之后可自行删除
     */
    {

        dao = new ApyAccountRepository();
    }


    /**
     *  获取支付响应
     * @param id 账户id
     * @return
     */
    public PayResponse getPayResponse(Integer id) {
        PayResponse payResponse = null;
        if (payResponse  == null) {
            ApyAccount apyAccount = dao.findByPayId(id);
            if (apyAccount == null) {
                throw new IllegalArgumentException ("无法查询");
            }
            payResponse = new PayResponse();
            spring.autowireBean(payResponse);
            payResponse.init(apyAccount);
            payResponses.put(id, payResponse);
            // 查询
        }
        return payResponse;
    }


}
