package org.example.crmdemo.mappers;

import org.example.crmdemo.dto.order.OrderDto;
import org.example.crmdemo.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public static OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }

        return OrderDto.builder()
                .id(order.getId())
                .name(order.getName())
                .surname(order.getSurname())
                .email(order.getEmail())
                .phone(order.getPhone())
                .age(order.getAge())
                .course(order.getCourse())
                .courseFormat(order.getCourseFormat())
                .courseType(order.getCourseType())
                .sum(order.getSum())
                .alreadyPaid(order.getAlreadyPaid())
                .createdAt(order.getCreatedAt())
                .utm(order.getUtm())
                .msg(order.getMsg())
                .status(order.getStatus())
                .build();
    }

    public static Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }

        return Order.builder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .age(dto.getAge())
                .course(dto.getCourse())
                .courseFormat(dto.getCourseFormat())
                .courseType(dto.getCourseType())
                .sum(dto.getSum())
                .alreadyPaid(dto.getAlreadyPaid())
                .createdAt(dto.getCreatedAt())
                .utm(dto.getUtm())
                .msg(dto.getMsg())
                .status(dto.getStatus())
                .build();
    }
}