package com.rajan.dreamshops.service.Order;

import com.rajan.dreamshops.dto.OrderDto;
import com.rajan.dreamshops.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);
}
