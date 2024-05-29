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
@Schema(name = "Book", description = "A book entity")
@Table(name = "orders")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "totalAmount")
    private double totalAmount;

    @Column(name = "paymentId")
    private long paymentIntentId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> itemsInOrder = new ArrayList<>();

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

    public long getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(long paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public List<OrderItem> getItemsInOrder() {
        return itemsInOrder;
    }

    public void setItemsInOrder(List<OrderItem> itemsInOrder) {
        this.itemsInOrder = itemsInOrder;
    }

}