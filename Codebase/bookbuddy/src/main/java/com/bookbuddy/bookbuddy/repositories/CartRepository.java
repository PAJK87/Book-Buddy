package com.bookbuddy.bookbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbuddy.bookbuddy.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
