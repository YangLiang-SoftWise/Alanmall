package com.itcrazy.alanmall.mscard.controllertest;

import com.itcrazy.alanmall.user.inter.IUserLoginService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class Test {
    @Reference
    IUserLoginService iUserLoginService;

    @GetMapping("/vue")
    public String test() {
        System.out.println("hello");
        return "test";
    }
}
