package com.bookbuddy.bookbuddy.entities;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Schema(name = "UserOrder", description = "An order entity for a user")
@Table(name = "user_orders")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "payment_id")
    private String paymentIntentId;

    @Column(name = "shipping_address")
    private String userShippingAddress;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<OrderItem> itemsInOrder = new ArrayList<>();

    public UserOrder(User user, String paymentIntentId, String userShippingAddress, double totalAmount) {
        this.user = user;
        this.paymentIntentId = paymentIntentId;
        this.userShippingAddress = userShippingAddress;
        this.totalAmount = totalAmount;
    }

    public UserOrder() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public List<OrderItem> getItemsInOrder() {
        return itemsInOrder;
    }

    public void setItemsInOrder(List<OrderItem> itemsInOrder) {
        this.itemsInOrder = itemsInOrder;
    }

    public String getUserShippingAddress() {
        return userShippingAddress;
    }

    public void setUserShippingAddress(String userShippingAddress) {
        this.userShippingAddress = userShippingAddress;
    }

}