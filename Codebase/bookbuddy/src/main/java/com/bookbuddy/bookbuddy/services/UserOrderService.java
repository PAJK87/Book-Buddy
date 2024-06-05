package com.bookbuddy.bookbuddy.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbuddy.bookbuddy.entities.Cart;
import com.bookbuddy.bookbuddy.entities.CartItem;
import com.bookbuddy.bookbuddy.entities.OrderItem;
import com.bookbuddy.bookbuddy.entities.User;
import com.bookbuddy.bookbuddy.entities.UserOrder;
import com.bookbuddy.bookbuddy.entityDTOS.UserOrderDTO;
import com.bookbuddy.bookbuddy.exceptions.CartNotFoundException;
import com.bookbuddy.bookbuddy.exceptions.UserNotFoundException;
import com.bookbuddy.bookbuddy.repositories.CartRepository;
import com.bookbuddy.bookbuddy.repositories.UserOrderRepository;
import com.bookbuddy.bookbuddy.repositories.UserRepository;

@Service
public class UserOrderService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserOrderRepository userOrderRepository;

    @Autowired
    UserRepository userRepository;

    public UserOrderDTO saveOrderDetails(Long cartId, Long paymentIntentId) {
        UserOrder newOrder = new UserOrder();
        userOrderRepository.save(newOrder);
        Cart orderCart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        newOrder.setUser(orderCart.getUser());
        newOrder.setPaymentIntentId(paymentIntentId);
        newOrder.setTotalAmount(orderCart.getTotalPrice().doubleValue());
        for (CartItem item : orderCart.getCartItems()) {
            OrderItem newItem = new OrderItem(newOrder, item.getBook(), item.getItemPrice());
            newOrder.getItemsInOrder().add(newItem);
        }
        userOrderRepository.save(newOrder);
        clearCart(cartId);
        return UserOrderDTO.fromEntity(newOrder);
    }

    public void clearCart(Long cartId) {
        Cart cartToClear = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));

        for (CartItem item : cartToClear.getCartItems()) {
            cartToClear.getCartItems().remove(item);
        }
        cartToClear.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cartToClear);
    }

    public List<UserOrderDTO> getAllOrdersByUser(Long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        List<UserOrder> userOrders = userOrderRepository.findByUser(foundUser);
        List<UserOrderDTO> userOrderDTOs = new ArrayList<>();
        for (UserOrder order : userOrders) {
            UserOrderDTO orderDTO = UserOrderDTO.fromEntity(order);
            userOrderDTOs.add(orderDTO);
        }
        return userOrderDTOs;
    }

}
