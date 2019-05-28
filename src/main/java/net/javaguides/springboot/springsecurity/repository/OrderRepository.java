package net.javaguides.springboot.springsecurity.repository;

import net.javaguides.springboot.springsecurity.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
