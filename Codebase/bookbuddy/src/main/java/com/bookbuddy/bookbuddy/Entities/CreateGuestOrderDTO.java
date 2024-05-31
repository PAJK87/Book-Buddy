package com.bookbuddy.bookbuddy.entities;

import java.math.BigDecimal;

public class CreateGuestOrderDTO {

    private String guestName;

    private String guestEmail;

    private String guestShippingAddress;

    private Long paymentIntentId;

    private BigDecimal totalOrderAmount;

    private Long bookId;

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getGuestShippingAddress() {
        return guestShippingAddress;
    }

    public void setGuestShippingAddress(String guestShippingAddress) {
        this.guestShippingAddress = guestShippingAddress;
    }

    public Long getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(Long paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

}
