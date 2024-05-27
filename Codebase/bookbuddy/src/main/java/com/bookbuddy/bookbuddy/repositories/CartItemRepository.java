package com.bookbuddy.bookbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbuddy.bookbuddy.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
