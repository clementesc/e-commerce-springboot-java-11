package com.example.ecommerce.services;

import com.example.ecommerce.entities.Order;
import com.example.ecommerce.entities.User;
import com.example.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Integer id){
        Optional<Order> opt = repository.findById(id);
        return opt.get();
    }
}
