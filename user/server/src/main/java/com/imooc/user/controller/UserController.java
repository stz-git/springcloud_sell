package com.imooc.user.controller;

import com.imooc.user.dataobject.UserInfo;
import com.imooc.user.service.UserService;
import com.imooc.user.util.CookieUtil;
import com.imooc.user.util.ResultUtil;
import com.imooc.user.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/buyer/login")
    public ResultVO buyerLogin(@RequestParam("openid") String openid,
                               HttpServletResponse response){
        if(StringUtils.isEmpty(openid))
            log.error("openid is empty");

        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null)
            return ResultUtil.error(-1, "userInfo == null");

        if(!userInfo.getRole().equals(1))
            return ResultUtil.error(-1, "not buyer");

        CookieUtil.set(response, "openid", openid, 7200);

        return ResultUtil.success();
    }

    @GetMapping("/seller/login")
    public ResultVO sellerLogin(@RequestParam("openid") String openid,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        if(StringUtils.isEmpty(openid))
            log.error("openid is empty");
        //1.
        Cookie cookie = CookieUtil.get(request, "token");
        if(cookie != null &&
                !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format("token_%s", cookie.getValue()))))
            return ResultUtil.success();

        //2.
        UserInfo userInfo = userService.findByOpenid(openid);
        if(userInfo == null)
            return ResultUtil.error(-1, "userInfo == null");
        //3.
        if(!userInfo.getRole().equals(2))
            return ResultUtil.error(-1, "not seller");

        //4.save in redis
        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format("token_%s", token), openid, 7200, TimeUnit.SECONDS);

        //5.save in cookie
        CookieUtil.set(response, "token", token, 7200);

        return ResultUtil.success();
    }
}
