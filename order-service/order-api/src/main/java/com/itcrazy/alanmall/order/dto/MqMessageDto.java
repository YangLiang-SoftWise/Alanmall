package com.itcrazy.alanmall.order.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: mathyoung
 * @description: mq dto
 */
@Data
public class MqMessageDto {
    private Long msgId;

    private String exchange;

    private String queue;

    private String tag;

    private Integer status;

    private Date created;

    private Date updated;
}
