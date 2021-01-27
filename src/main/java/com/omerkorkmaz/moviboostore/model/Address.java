package com.omerkorkmaz.moviboostore.model;

import lombok.*;
import javax.persistence.*;


@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int addressId;

    @Column(name = "Street")
    private String street;

    @Column(name = "Number")
    private String number;

    @Column(name = "ZipCode")
    private String zipCode;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "Country")
    private String country;

    @OneToOne(fetch = FetchType.LAZY)
    private Order order;

}
