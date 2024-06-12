package com.bookbuddy.bookbuddy.entityDTOS;

import java.util.List;

import com.bookbuddy.bookbuddy.entities.OrderItem;
import com.bookbuddy.bookbuddy.entities.UserOrder;

import io.swagger.v3.oas.annotations.media.Schema;

//This is the OrderDTO class. It is a Data Transfer Object (DTO) that is used to send order information back to the client.
//It has the order ID, user ID, list of items in the order, and the total price of all items in the order.
//This main difference between this class and the Order class is that this class does not have a reference to the User entity, only the user ID.
//Jimmy Karoly

@Schema(name = "UserOrderDTO", description = "Data Transfer Object for User Order")
public class UserOrderDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Unique ID generated by database corresponding to an order", example = "1")
    private Long orderId;

    @Schema(description = "Id of the user placing the order", example = "1")
    private Long userId;

    @Schema(description = "List of items in the order")
    private List<OrderItem> items;

    @Schema(description = "Total price of all items in the order", example = "58.23")
    private double totalOrderAmount;

    private String shippingAddress;

    public static UserOrderDTO fromEntity(UserOrder order) {
        UserOrderDTO orderDTO = new UserOrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setItems(order.getItemsInOrder());
        orderDTO.setTotalOrderAmount(order.getTotalAmount());
        orderDTO.setShippingAddress(order.getShippingAddress());
        return orderDTO;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(double totalOrderAmount) {
        this.totalOrderAmount = totalOrderAmount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}