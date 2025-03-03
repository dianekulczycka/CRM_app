package org.example.crmdemo.repositories;

import org.example.crmdemo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
}
