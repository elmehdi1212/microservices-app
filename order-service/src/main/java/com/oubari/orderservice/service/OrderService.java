package com.oubari.orderservice.service;

import com.oubari.orderservice.dto.OrderLineItemsDto;
import com.oubari.orderservice.dto.OrderRequest;
import com.oubari.orderservice.model.Order;
import com.oubari.orderservice.model.OrderLineItems;
import com.oubari.orderservice.repository.OrderRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepostory orderRepostory;

    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList=orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItemsList);

        orderRepostory.save(order);

    }

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setSkucode(orderLineItemsDto.getSkucode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }
}
