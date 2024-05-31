package com.bookbuddy.bookbuddy.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Schema(name = "GuestOrder", description = "Order made by a guest")
@Table(name = "guest_orders")
public class GuestOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "guest_address")
    private String guestShippingAddress;

    @Column(name = "payment_id")
    private long paymentIntentId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getGuestShippingAddress() {
        return guestShippingAddress;
    }

    public void setGuestShippingAddress(String guestShippingAddress) {
        this.guestShippingAddress = guestShippingAddress;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }
}
