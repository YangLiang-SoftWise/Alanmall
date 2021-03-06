package com.itcrazy.alanmall.order.conveter;

import com.itcrazy.alanmall.order.dal.entity.Order;
import com.itcrazy.alanmall.order.dal.entity.OrderItem;
import com.itcrazy.alanmall.order.dal.entity.OrderShipping;
import com.itcrazy.alanmall.order.dto.OrderDetailInfo;
import com.itcrazy.alanmall.order.dto.OrderDetailResponse;
import com.itcrazy.alanmall.order.dto.OrderItemDto;
import com.itcrazy.alanmall.order.dto.OrderShippingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * @Auther: mathyoung
 * @description:
 */
@Mapper(componentModel = "spring")
public interface QueryOrderDetailConverter {
    @Mappings({})
    OrderDetailResponse order2Res(Order order);

    @Mappings({})
    OrderDetailInfo order2detail(Order order);

    @Mappings({})
    List<OrderItemDto> items2dtos(List<OrderItem> items);

    @Mappings({})
    OrderShippingDto shipping2dto(OrderShipping shipping);

    @Mappings({})
    OrderDetailInfo order2OrderDetail(Order order);
}
