package org.example.crmdemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@DynamicUpdate

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Integer age;
    private String course;
    private String course_format;
    private String course_type;
    private Integer sum;
    private Integer alreadyPaid;
    private LocalDateTime created_at;
    private String utm;
    private String msg;
    private String status;
}
