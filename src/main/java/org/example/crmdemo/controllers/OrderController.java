package org.example.crmdemo.controllers;

import org.example.crmdemo.dto.order.OrderPaginationResponseDto;
import org.example.crmdemo.services.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/v1/api/")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("orders")
    public ResponseEntity<OrderPaginationResponseDto> getOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Pageable pageable = orderService.createPageable(page, order, direction);
        OrderPaginationResponseDto response = orderService.getOrders(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
