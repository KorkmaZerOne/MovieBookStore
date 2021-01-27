package com.omerkorkmaz.moviboostore.repositories;

import com.omerkorkmaz.moviboostore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByOrderNumber(String orderNumber);
}
