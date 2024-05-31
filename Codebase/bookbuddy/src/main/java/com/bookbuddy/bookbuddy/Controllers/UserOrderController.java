package com.bookbuddy.bookbuddy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookbuddy.bookbuddy.entities.UserOrderDTO;
import com.bookbuddy.bookbuddy.services.UserOrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("user-checkout")
@CrossOrigin("*")
public class UserOrderController {

    @Autowired
    UserOrderService userOrderService;

    @PostMapping()
    public ResponseEntity<UserOrderDTO> saveOrderDetails(@PathVariable Long cartId, @RequestBody Long paymentIntentId) {
        UserOrderDTO newOrder = userOrderService.saveOrderDetails(cartId, paymentIntentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

}
