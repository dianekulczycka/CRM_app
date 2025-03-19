package org.example.crmdemo.controllers;

import lombok.AllArgsConstructor;
import org.example.crmdemo.dto.order.OrderDto;
import org.example.crmdemo.dto.order.OrderFormDataDto;
import org.example.crmdemo.dto.order.OrderPaginationResponseDto;
import org.example.crmdemo.mappers.OrderMapper;
import org.example.crmdemo.services.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/")
    public ResponseEntity<OrderPaginationResponseDto> getOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "desc") String direction
    ) {
        Pageable pageable = orderService.createPageable(page, order, direction);
        OrderPaginationResponseDto response = orderService.getOrders(pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Void> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderFormDataDto orderFormDataDto,
            @RequestHeader("Authorization") String token) {
        OrderDto orderDto = orderMapper.mapToOrderDto(orderFormDataDto);
        orderService.updateOrder(id, orderDto, token.replace("Bearer ", ""));
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
