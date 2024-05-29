package com.bookbuddy.bookbuddy.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookbuddy.bookbuddy.entities.Cart;
import com.bookbuddy.bookbuddy.entities.CartItem;
import com.bookbuddy.bookbuddy.entities.OrderDTO;
import com.bookbuddy.bookbuddy.entities.OrderItem;
import com.bookbuddy.bookbuddy.entities.User;
import com.bookbuddy.bookbuddy.entities.UserOrder;
import com.bookbuddy.bookbuddy.exceptions.CartNotFoundException;
import com.bookbuddy.bookbuddy.exceptions.UserNotFoundException;
import com.bookbuddy.bookbuddy.repositories.CartRepository;
import com.bookbuddy.bookbuddy.repositories.OrderRepository;
import com.bookbuddy.bookbuddy.repositories.UserRepository;

public class OrderService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public OrderDTO saveOrderDetails(Long cartId, Long paymentIntentId) {
        UserOrder newOrder = new UserOrder();
        orderRepository.save(newOrder);
        Cart orderCart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        newOrder.setUser(orderCart.getUser());
        newOrder.setPaymentIntentId(paymentIntentId);
        newOrder.setTotalAmount(orderCart.getTotalPrice().doubleValue());
        for (CartItem item : orderCart.getCartItems()) {
            OrderItem newItem = new OrderItem(newOrder, item.getBook(), item.getItemPrice());
            newOrder.getItemsInOrder().add(newItem);
        }
        orderRepository.save(newOrder);
        clearCart(cartId);
        return OrderDTO.fromEntity(newOrder);
    }

    public void clearCart(Long cartId) {
        Cart cartToClear = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));

        for (CartItem item : cartToClear.getCartItems()) {
            cartToClear.getCartItems().remove(item);
        }
        cartToClear.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cartToClear);
    }

    public List<UserOrder> getUserOrders(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<UserOrder> userOrders;
        userOrders = orderRepository.findByUser(user);
        return userOrders;
    }

}
