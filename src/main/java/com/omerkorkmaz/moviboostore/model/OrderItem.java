package com.omerkorkmaz.moviboostore.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orderItems")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int orderItemId;

    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "Quantity")
    private int quantity;

    @ManyToMany
    private List<Product> product;

    @ManyToMany
    private List<Order> orders;

}
