package com.bookbuddy.bookbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbuddy.bookbuddy.entities.UserCheckout;

public interface UserCheckoutRepository extends JpaRepository<UserCheckout, Long> {

}
