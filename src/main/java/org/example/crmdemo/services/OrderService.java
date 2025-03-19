package org.example.crmdemo.services;

import lombok.RequiredArgsConstructor;
import org.example.crmdemo.dto.order.OrderDto;
import org.example.crmdemo.dto.order.OrderPaginationResponseDto;
import org.example.crmdemo.entities.Group;
import org.example.crmdemo.entities.Manager;
import org.example.crmdemo.entities.Order;
import org.example.crmdemo.mappers.OrderMapper;
import org.example.crmdemo.repositories.ManagerRepository;
import org.example.crmdemo.repositories.OrderRepository;
import org.example.crmdemo.utilities.JwtUtility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ManagerRepository managerRepository;
    private final JwtUtility jwtUtility;
    private final GroupService groupService;
    private final OrderMapper orderMapper;

    public OrderPaginationResponseDto getOrders(Pageable pageable) {
        Page<Order> page = orderRepository.findAll(pageable);
        List<OrderDto> orderDtos = page.stream()
                .map(OrderMapper::toDto)
                .collect(Collectors.toList());

        return new OrderPaginationResponseDto(
                page.getTotalElements(),
                page.getSize(),
                page.hasNext() ? page.getNumber() + 1 : null,
                page.hasPrevious() ? page.getNumber() - 1 : null,
                orderDtos
        );
    }

    public Pageable createPageable(int page, String sortBy, String direction) {
        int pageNumber = page - 1;

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(Sort.Order.asc(sortBy))
                : Sort.by(Sort.Order.desc(sortBy));

        return PageRequest.of(pageNumber, 25, sort);
    }

    @Transactional
    public void updateOrder(Long orderId, OrderDto orderDto, String token) {
        String email = jwtUtility.extractUsername(token);

        Manager manager = managerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getManager() != null && !order.getManager().equals(manager.getSurname())) {
            throw new RuntimeException("You can only update your orders");
        }

        orderMapper.updateEntity(order, orderDto);

        if (orderDto.getGroupName() != null) {
            Group group = groupService.getOrCreateGroup(orderDto.getGroupName().toUpperCase());
            order.setGroup(group);
        }

        orderRepository.save(order);
    }
}