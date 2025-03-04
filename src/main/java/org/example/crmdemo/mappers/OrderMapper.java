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
                .courseFormat(order.getCourse_format())
                .courseType(order.getCourse_type())
                .sum(order.getSum())
                .alreadyPaid(order.getAlreadyPaid())
                .createdAt(order.getCreated_at())
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
                .course_format(dto.getCourseFormat())
                .course_type(dto.getCourseType())
                .sum(dto.getSum())
                .alreadyPaid(dto.getAlreadyPaid())
                .created_at(dto.getCreatedAt())
                .utm(dto.getUtm())
                .msg(dto.getMsg())
                .status(dto.getStatus())
                .build();
    }
}