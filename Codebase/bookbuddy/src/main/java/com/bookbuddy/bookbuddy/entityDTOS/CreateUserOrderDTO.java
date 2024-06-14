package com.bookbuddy.bookbuddy.entityDTOS;

public class CreateUserOrderDTO {

    private Long cartId;

    private String paymentIntentId;

    private String userShippingAddress;

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

    public String getUserShippingAddress() {
        return userShippingAddress;
    }

    public void setUserShippingAddress(String shippingAddress) {
        this.userShippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "CreateUserOrderDTO{" +
                "cartId=" + cartId +
                ", paymentIntentId='" + paymentIntentId + '\'' +
                ", userShippingAddress='" + userShippingAddress + '\'' +
                '}';
    }

}
