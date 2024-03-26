package com.oubari.orderservice.service;

import com.oubari.orderservice.dto.OrderLineItemsDto;
import com.oubari.orderservice.dto.OrderRequest;
import com.oubari.orderservice.model.Order;
import com.oubari.orderservice.model.OrderLineItems;
import com.oubari.orderservice.repository.OrderRepostory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepostory orderRepostory;

    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList=orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto).toList();
        order.setOrderLineItemsList(orderLineItemsList);

       List<String> skucodes=order.getOrderLineItemsList().stream().map(OrderLineItems::getSkucode).toList();
        // call inventory service and place order if product is in stock
       List<String> result= webClientBuilder.build().get().uri("http://localhost:8082/api/inventory",uriBuilder -> uriBuilder.queryParam("skucodes",skucodes).build()).retrieve().bodyToMono(List.class).block(); //syn communication
        if(Boolean.TRUE.equals(false)){
            orderRepostory.save(order);

        }
        throw  new IllegalArgumentException("product is not in stock");

    }

    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setSkucode(orderLineItemsDto.getSkucode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

        return orderLineItems;
    }
}
