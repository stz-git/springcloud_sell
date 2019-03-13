package com.imooc.order.service.impl;

import com.imooc.order.service.HystrixService;
import com.imooc.product.client.ProductClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HystrixServiceImpl implements HystrixService {

    @Autowired
    private ProductClient productClient;

    @Override
    public String getProductInfoList() {
        String message = productClient.message();
        try {
            Thread.sleep(5000);
        } catch (Exception e){}
        if(message != null)
            return message;
        return "123";
    }
}
