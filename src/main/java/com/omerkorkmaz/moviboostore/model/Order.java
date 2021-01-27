package com.omerkorkmaz.moviboostore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int orderId;

    @Column(name = "Order_Number", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "Create_Date", nullable = false)
    private Date createDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private Payment payment;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer customer;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Credit credit;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address deliveryAddress;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Address billingAddress;


    @NotNull(message = "FirstName is required")
    private String firstName;

    @NotNull(message = "LastName is required")
    private String lastName;

    @NotNull(message = "EmailId is required")
    @Email
    private String emailId;

    @NotNull(message = "Phone is required")
    private String phone;

    @NotNull(message = "Street is required")
    private String street;

    @NotNull(message = "Number is required")
    private String number;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "State is required")
    private String state;

    @NotNull(message = "ZipCode is required")
    private String zipCode;

    @NotNull(message = "Country is required")
    private String country;

    @NotNull(message = "FirstName is required")
    private String billingFirstName;

    @NotNull(message = "LastName is required")
    private String billingLastName;

    @NotNull(message = "Address Line1 is required")
    private String billingAddressLine1;
    private String billingAddressLine2;

    @NotNull(message = "City is required")
    private String billingCity;

    @NotNull(message = "State is required")
    private String billingState;

    @NotNull(message = "ZipCode is required")
    private String billingZipCode;

    @NotNull(message = "Country is required")
    private String billingCountry;

    @NotNull(message = "Credit Number is required")
    private String creditNumber;

}
