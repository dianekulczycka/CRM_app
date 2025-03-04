package org.example.crmdemo.services;

import lombok.RequiredArgsConstructor;
import org.example.crmdemo.dto.order.OrderDto;
import org.example.crmdemo.dto.order.OrderPaginationResponseDto;
import org.example.crmdemo.entities.Order;
import org.example.crmdemo.mappers.OrderMapper;
import org.example.crmdemo.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderPaginationResponseDto getOrders(int page, String order, String direction) {
        int perPage = 25;
        int offset = (page - 1) * perPage;
        long total = orderRepository.findAll().size();

        List<OrderDto> paginatedOrders = orderRepository
                .findAll()
                .stream()
                .sorted(createComparator(order, direction))
                .skip(offset)
                .limit(perPage)
                .map(OrderMapper::toDto)
                .toList();

        Integer nextPage = (offset + perPage) < total ? page + 1 : null;
        Integer prevPage = page > 1 ? page - 1 : null;

        return new OrderPaginationResponseDto(total, perPage, nextPage, prevPage, paginatedOrders);
    }

    private Comparable<?> getSortKey(Order order, String field) {
        return switch (field) {
            case "name" -> order.getName();
            case "surname" -> order.getSurname();
            case "email" -> order.getEmail();
            case "phone" -> order.getPhone();
            case "age" -> order.getAge();
            case "course" -> order.getCourse();
            case "course_format" -> order.getCourse_format();
            case "course_type" -> order.getCourse_type();
            case "sum" -> order.getSum();
            case "alreadyPaid" -> order.getAlreadyPaid();
            case "created_at" -> order.getCreated_at();
            case "utm" -> order.getUtm();
            case "msg" -> order.getMsg();
            case "status" -> order.getStatus();
            default -> order.getId();
        };
    }

    private Comparator<Order> createComparator(String order, String direction) {
        Comparator<Order> comparator = Comparator.comparing(
                orderObj -> (Comparable<Object>) getSortKey(orderObj, order),
                Comparator.nullsFirst(Comparator.naturalOrder())
        );
        return "asc".equalsIgnoreCase(direction) ? comparator : comparator.reversed();
    }
}

