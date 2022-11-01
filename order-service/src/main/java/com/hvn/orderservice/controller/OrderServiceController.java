package com.hvn.orderservice.controller;

import com.hvn.orderservice.dto.OrderRequest;
import com.hvn.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderServiceController {

    private final OrderService orderService;

    @RequestMapping(value = "/place-order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return "Successfully";
    }
}
