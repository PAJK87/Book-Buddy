package com.bookbuddy.bookbuddy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookbuddy.bookbuddy.entities.UserCheckout;
import com.bookbuddy.bookbuddy.repositories.UserCheckoutRepository;

@Service
public class UserCheckoutService {

    @Autowired
    UserCheckoutRepository userCheckoutRepository;

    public UserCheckout createUserCheckout(UserCheckout userCheckout) {
        return userCheckoutRepository.save(userCheckout);
    }
}
