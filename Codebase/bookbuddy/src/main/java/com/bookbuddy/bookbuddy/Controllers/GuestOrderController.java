package com.bookbuddy.bookbuddy.controllers;

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

import com.bookbuddy.bookbuddy.entities.GuestOrder;
import com.bookbuddy.bookbuddy.entityDTOS.CreateGuestOrderDTO;
import com.bookbuddy.bookbuddy.exceptions.OrderNotFoundException;
import com.bookbuddy.bookbuddy.repositories.GuestOrderRepository;
import com.bookbuddy.bookbuddy.services.GuestOrderService;

@RestController
@RequestMapping("/guest-order")
@CrossOrigin("*")
public class GuestOrderController {

    @Autowired
    GuestOrderRepository guestOrderRepository;

    @Autowired
    GuestOrderService guestOrderService;

    @PostMapping("/create")
    public ResponseEntity<Long> saveGuestCheckout(@RequestBody CreateGuestOrderDTO guestOrder) {
        Long guestOrderId = guestOrderService.createNewGuestOrder(guestOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(guestOrderId);
    }

    @GetMapping("/{guestOrderId}")
    public ResponseEntity<GuestOrder> getGuestOrder(@PathVariable Long guestOrderId) {
        GuestOrder guestOrder = guestOrderRepository.findById(guestOrderId)
                .orElseThrow(() -> new OrderNotFoundException(guestOrderId));
        return ResponseEntity.ok(guestOrder);
    }
}
