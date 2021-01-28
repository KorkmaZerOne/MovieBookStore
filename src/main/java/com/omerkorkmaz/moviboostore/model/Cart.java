package com.omerkorkmaz.moviboostore.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private Customer customer;
    private Address deliveryAddress;
    private Payment payment;

    public Cart() {
        items = new ArrayList<CartItem>();
        customer = new Customer();
        deliveryAddress = new Address();
        payment = new Payment();
    }

    public void addItem(Product product) {
        for (CartItem cartItem : items) {
            if (cartItem.getProduct().getSku().equals(product.getSku())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
        CartItem item = new CartItem(product, 1);
        this.items.add(item);
    }

    public void updateItemQuantity(Product product, int quantity) {
        for (CartItem cartItem : items) {
            if (cartItem.getProduct().getSku().equals(product.getSku())) {
                cartItem.setQuantity(quantity);
            }
        }
    }

    public void removeItem(String sku) {
        CartItem item = null;
        for (CartItem cartItem : items) {
            if (cartItem.getProduct().getSku().equals(sku)) {
                item = cartItem;
                break;
            }
        }
        if (item != null) {
            items.remove(item);
        }
    }

    public void clearItems() {
        items = new ArrayList<CartItem>();
    }

    public int getItemCount() {
        int count = 0;
        for (CartItem cartItem : items) {
            count += cartItem.getQuantity();
        }
        return count;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        double amount = 0;
        for (CartItem cartItem : items) {
            amount = cartItem.getSubTotal();
            ;
        }
        return amount;
    }
    public Customer getCustomer()
    {
        return customer;
    }
    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
    public Address getDeliveryAddress()
    {
        return deliveryAddress;
    }
    public void setDeliveryAddress(Address deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }
    public Payment getPayment()
    {
        return payment;
    }
    public void setPayment(Payment payment)
    {
        this.payment = payment;
    }

}
