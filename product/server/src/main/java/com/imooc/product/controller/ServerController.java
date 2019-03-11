package com.imooc.product.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @RequestMapping("/msg")
    @HystrixCommand(fallbackMethod = "fallback")
    public String message(){
        try {
            Thread.sleep(4000);
        }catch (Exception e){}

        return "this is product message";
    }

    public String fallback(){
        return "fallback";
    }
}
