package org.example.crmdemo.controllers;

import lombok.AllArgsConstructor;
import org.example.crmdemo.dto.order.OrderPaginationResponseDto;
import org.example.crmdemo.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/")
@AllArgsConstructor

public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<OrderPaginationResponseDto> getOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        return new ResponseEntity<>(orderService.getOrders(page, order, direction), HttpStatus.OK);
    }
}
