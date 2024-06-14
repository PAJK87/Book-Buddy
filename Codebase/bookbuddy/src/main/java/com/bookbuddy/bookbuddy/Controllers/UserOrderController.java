package com.bookbuddy.bookbuddy.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbuddy.bookbuddy.entityDTOS.CreateUserOrderDTO;
import com.bookbuddy.bookbuddy.entityDTOS.UserOrderDTO;
import com.bookbuddy.bookbuddy.exceptions.OrderNotFoundException;
import com.bookbuddy.bookbuddy.repositories.UserOrderRepository;
import com.bookbuddy.bookbuddy.services.UserOrderService;

@RestController
@RequestMapping("user-order")
@CrossOrigin("*")
public class UserOrderController {

    @Autowired
    UserOrderService userOrderService;

    @Autowired
    UserOrderRepository userOrderRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserOrderController.class);

    @PostMapping("/create")
    public ResponseEntity<Long> saveOrderDetails(@RequestBody CreateUserOrderDTO userOrder) {
        logger.info("Received userOrder JSON: {}", userOrder);
        if (userOrder == null || userOrder.getCartId() == null) {
            logger.error("UserOrder or Cart ID must not be null");
            throw new IllegalArgumentException("UserOrder or Cart ID must not be null");
        }

        Long userOrderId = userOrderService.saveOrderDetails(userOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(userOrderId);
    }

    @GetMapping("/{userOrderId}")
    public ResponseEntity<UserOrderDTO> getUserOrderById(@PathVariable Long userOrderId) {
        UserOrderDTO userOrder = UserOrderDTO.fromEntity(
                userOrderRepository.findById(userOrderId).orElseThrow(() -> new OrderNotFoundException(userOrderId)));
        return ResponseEntity.ok(userOrder);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<UserOrderDTO>> getAllUserOrders(@PathVariable Long userId) {
        List<UserOrderDTO> listOfAllOrdersMadeByAUser = userOrderService.getAllOrdersByUser(userId);
        return ResponseEntity.ok(listOfAllOrdersMadeByAUser);
    }

    @PostMapping("/test-create")
    public ResponseEntity<CreateUserOrderDTO> testCreate(@RequestBody CreateUserOrderDTO userOrder) {
        logger.info("Received userOrder JSON: {}", userOrder);
        return ResponseEntity.ok(userOrder);
    }

}
