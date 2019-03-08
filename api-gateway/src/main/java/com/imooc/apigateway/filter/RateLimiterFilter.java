package com.imooc.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.imooc.apigateway.exception.RateLimiterException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

@Component
public class RateLimiterFilter extends ZuulFilter{

    private static final RateLimiter rateLimiter = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if(!rateLimiter.tryAcquire()){
            throw new RateLimiterException(-1, "too many request !");
        }
        return null;
    }
}
