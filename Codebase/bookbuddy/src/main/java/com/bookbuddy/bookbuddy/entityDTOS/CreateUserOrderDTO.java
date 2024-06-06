package com.bookbuddy.bookbuddy.entityDTOS;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateUserOrderDTO {

    @Schema(description = "User Id of the user who is placing the order", example = "001")
    private Long userId;

    @Schema(description = "Cart Id for the cart of the order", example = "001")
    private Long cartId;

    @Schema(description = "Id of the payment intent of the order", example = "djah_412345")
    private String paymentIntentId;

    @Schema(description = "Shipping Address for the Order", example = "123 Main Street Example, Arizona 11111")
    private String shippingAddress;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}
