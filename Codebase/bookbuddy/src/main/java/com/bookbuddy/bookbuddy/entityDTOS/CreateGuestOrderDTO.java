package com.bookbuddy.bookbuddy.entityDTOS;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateGuestOrderDTO {

    @Schema(description = "The full name of the guest who placed the order", example = "John Doe")
    private String guestName;

    @Schema(description = "Email of the guest who placed the order", example = "example@email.com")
    private String guestEmail;

    @Schema(description = "Shipping address of the guest who placed the order", example = "123 Main Street Example, Arizona 11111")
    private String guestShippingAddress;

    @Schema(description = "Id of the payment with Stripe")
    private String paymentIntentId;

    @Schema(description = "Total amount of the order", example = "19.99")
    private BigDecimal totalOrderAmount;

    @Schema(description = "The id of the book that was ordered", example = "1")
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

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
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
