package net.javaguides.springboot.springsecurity.service;

import net.javaguides.springboot.springsecurity.model.Flight;
import net.javaguides.springboot.springsecurity.model.Order;
import net.javaguides.springboot.springsecurity.model.User;
import net.javaguides.springboot.springsecurity.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order CreateOrder(Flight flight, User user){
        Order order=new Order(flight,user);
        System.out.println(order.toString());
        user.getOrders().add(order);
       orderRepository.save(order);
        System.out.println(order.toString());
        return order;
    }
}
