package com.bookbuddy.bookbuddy.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbuddy.bookbuddy.entities.Cart;
import com.bookbuddy.bookbuddy.entities.CartItem;
import com.bookbuddy.bookbuddy.entities.OrderItem;
import com.bookbuddy.bookbuddy.entities.User;
import com.bookbuddy.bookbuddy.entities.UserOrder;
import com.bookbuddy.bookbuddy.entityDTOS.CreateUserOrderDTO;
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

    // public Long saveOrderDetails(CreateUserOrderDTO userOrder) {
    // Cart orderCart = cartRepository.findById(userOrder.getCartId())
    // .orElseThrow(() -> new CartNotFoundException(userOrder.getCartId()));
    // UserOrder newOrder = new UserOrder(orderCart.getUser(),
    // userOrder.getPaymentIntentId(),
    // userOrder.getUserShippingAddress(), orderCart.getTotalPrice().doubleValue());
    // userOrderRepository.save(newOrder);
    // for (CartItem item : orderCart.getCartItems()) {
    // OrderItem newItem = new OrderItem(newOrder.getOrderId(), item.getBook(),
    // item.getItemPrice());
    // newOrder.getItemsInOrder().add(newItem);
    // }
    // userOrderRepository.save(newOrder);
    // clearCart(userOrder.getCartId());
    // Long userOrderId = newOrder.getOrderId();
    // return userOrderId;
    // }

    // public void clearCart(Long cartId) {
    // Cart cartToClear = cartRepository.findById(cartId).orElseThrow(() -> new
    // CartNotFoundException(cartId));

    // Iterator<CartItem> iterator = cartToClear.getCartItems().iterator();
    // while (iterator.hasNext()) {
    // CartItem item = iterator.next();
    // iterator.remove();
    // }
    // cartToClear.setTotalPrice(BigDecimal.ZERO);
    // cartRepository.save(cartToClear);
    // }

    public Long saveOrderDetails(CreateUserOrderDTO userOrder) {
        Logger logger = LoggerFactory.getLogger(UserOrderService.class);
        logger.info("User Order: {}", userOrder);
        if (userOrder == null) {
            logger.error("UserOrder must not be null");
            throw new IllegalArgumentException("UserOrder must not be null");
        } else if (userOrder.getCartId() == null) {
            logger.error("Cart ID must not be null");
            throw new IllegalArgumentException("Cart ID must not be null");
        }

        logger.info("Starting saveOrderDetails with userOrder: {}", userOrder);

        Cart orderCart = cartRepository.findById(userOrder.getCartId())
                .orElseThrow(() -> new CartNotFoundException(userOrder.getCartId()));

        UserOrder newOrder = new UserOrder(orderCart.getUser(), userOrder.getPaymentIntentId(),
                userOrder.getUserShippingAddress(), orderCart.getTotalPrice().doubleValue());

        userOrderRepository.save(newOrder);

        for (CartItem item : orderCart.getCartItems()) {
            OrderItem newItem = new OrderItem(newOrder.getOrderId(), item.getBook(), item.getItemPrice());
            newOrder.getItemsInOrder().add(newItem);
        }

        userOrderRepository.save(newOrder);
        clearCart(userOrder.getCartId());

        Long userOrderId = newOrder.getOrderId();
        logger.info("Order saved successfully with orderId: {}", userOrderId);

        return userOrderId;
    }

    public void clearCart(Long cartId) {
        Logger logger = LoggerFactory.getLogger(UserOrderService.class);

        if (cartId == null) {
            logger.error("Cart ID must not be null");
            throw new IllegalArgumentException("Cart ID must not be null");
        }

        logger.info("Starting clearCart with cartId: {}", cartId);

        Cart cartToClear = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        Iterator<CartItem> iterator = cartToClear.getCartItems().iterator();
        while (iterator.hasNext()) {
            CartItem item = iterator.next();
            iterator.remove();
        }

        cartToClear.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cartToClear);

        logger.info("Cart cleared successfully for cartId: {}", cartId);
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
