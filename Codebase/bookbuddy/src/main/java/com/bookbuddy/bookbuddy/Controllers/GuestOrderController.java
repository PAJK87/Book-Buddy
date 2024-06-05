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
import com.bookbuddy.bookbuddy.exceptions.OrderNotFoundException;
import com.bookbuddy.bookbuddy.repositories.GuestOrderRepository;

@RestController
@RequestMapping("/guest-order")
@CrossOrigin("*")
public class GuestOrderController {

    @Autowired
    GuestOrderRepository guestOrderRepository;

    @PostMapping("/create")
    public ResponseEntity<GuestOrder> saveGuestCheckout(@RequestBody GuestOrder guestCheckout) {
        GuestOrder savedGuestCheckout = guestOrderRepository.save(guestCheckout);
        return new ResponseEntity<>(savedGuestCheckout, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<GuestOrder> getGuestOrder(@PathVariable Long guestOrderId) {
        GuestOrder guestOrder = guestOrderRepository.findById(guestOrderId)
                .orElseThrow(() -> new OrderNotFoundException(guestOrderId));
        return ResponseEntity.ok(guestOrder);

    }
}
