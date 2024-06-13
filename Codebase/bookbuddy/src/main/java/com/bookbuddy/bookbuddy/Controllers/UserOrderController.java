package com.bookbuddy.bookbuddy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbuddy.bookbuddy.entityDTOS.UserOrderDTO;
import com.bookbuddy.bookbuddy.exceptions.OrderNotFoundException;
import com.bookbuddy.bookbuddy.repositories.UserOrderRepository;
import com.bookbuddy.bookbuddy.services.UserOrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("user-order")
@CrossOrigin("*")
public class UserOrderController {

    @Autowired
    UserOrderService userOrderService;

    @Autowired
    UserOrderRepository userOrderRepository;

    @PostMapping("/create/{cartId}")
    public ResponseEntity<Long> saveOrderDetails(@PathVariable Long cartId,
            @RequestBody String paymentIntentId) {
        Long newOrderId = userOrderService.saveOrderDetails(cartId, paymentIntentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrderId);
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

}
