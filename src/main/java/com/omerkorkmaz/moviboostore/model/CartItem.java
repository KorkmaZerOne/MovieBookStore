package com.omerkorkmaz.moviboostore.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CartItem {
    private Product product;
    private int quantity;

    public double getSubTotal() {
        return product.getPrice() * quantity;
    }
}
