package com.itcrazy.alanmall.user.dto;

import com.itcrazy.alanmall.common.result.AbstractResponse;
import lombok.Data;

@Data
public class UserLoginResponse extends AbstractResponse {
    private Long id;
    private String username;
    private String phone;
    private String email;
    private String sex;
    private String address;
    private String file;
    private String description;
    private Integer points;
    private Long balance;
    private int state;
    private String token;
}
