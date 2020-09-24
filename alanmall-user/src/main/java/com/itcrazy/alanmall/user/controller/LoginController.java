package com.itcrazy.alanmall.user.controller;

import com.itcrazy.alanmall.common.result.ResponseData;
import com.itcrazy.alanmall.common.result.ResponseUtil;
import com.itcrazy.alanmall.common.util.CookieUtil;
import com.itcrazy.alanmall.user.IKaptchaService;
import com.itcrazy.alanmall.user.IUserLoginService;
import com.itcrazy.alanmall.user.annotation.Anoymous;
import com.itcrazy.alanmall.user.constants.SysRetCodeConstants;
import com.itcrazy.alanmall.user.dto.KaptchaCodeRequest;
import com.itcrazy.alanmall.user.dto.KaptchaCodeResponse;
import com.itcrazy.alanmall.user.dto.UserLoginRequest;
import com.itcrazy.alanmall.user.dto.UserLoginResponse;
import com.itcrazy.alanmall.user.intercepter.TokenIntercepter;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Reference(timeout = 3000)
    IUserLoginService iUserLoginService;

    @Reference(timeout = 3000)
    IKaptchaService iKaptchaService;

    /**
     * 验证码开关
     */
    @Value("${captchaFlag:true}")
    private boolean captchaFlag;

    @Anoymous
    @PostMapping("/login")
    public ResponseData login(@RequestBody Map<String,String> map,
                              HttpServletRequest request, HttpServletResponse response) {
        UserLoginRequest loginRequest=new UserLoginRequest();
        loginRequest.setPassword(map.get("userPwd"));
        loginRequest.setUsername(map.get("userName"));

        if (captchaFlag) {
            KaptchaCodeRequest kaptchaCodeRequest = new KaptchaCodeRequest();
            // 根据kaptcha_uuid 获取对应的value uuid
            String uuid = CookieUtil.getCookieValue(request,"kaptcha_uuid");
            kaptchaCodeRequest.setCode(map.get("captcha"));
            kaptchaCodeRequest.setUuid(uuid);

            KaptchaCodeResponse kaptchaCodeResponse = iKaptchaService.validateKaptcha(kaptchaCodeRequest);

            if (!kaptchaCodeResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
                return new ResponseUtil<>().setErrorMsg(kaptchaCodeResponse.getMsg());
            }
        }

        UserLoginResponse userLoginResponse=iUserLoginService.login(loginRequest);

        if(userLoginResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            Cookie cookie = CookieUtil.genCookie(TokenIntercepter.ACCESS_TOKEN, userLoginResponse.getToken(),"/",24*60*60);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return new ResponseUtil().setData(userLoginResponse);
        }
        return new ResponseUtil().setErrorMsg(userLoginResponse.getMsg());
    }

}
