package com.imooc.order.service.impl;

import com.imooc.order.service.HystrixService;
import com.imooc.product.client.ProductClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixServiceImpl implements HystrixService {

    @Autowired
    private ProductClient productClient;

    @Override
    @HystrixCommand
    public String getProductInfoList() {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject("http://192.168.13.45:8081/product/list", String.class);
        String message = productClient.message();

        try {
            Thread.sleep(5000);
        } catch (Exception e){}
        if(message != null)
            return message;
        return "123";
    }

    public String defaultFallback(){
        return "busy now.....";
    }
}
