package com.bookbuddy.bookbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookbuddy.bookbuddy.entities.GuestCheckout;

@Repository
public interface GuestCheckoutRepository extends JpaRepository<GuestCheckout, Long> {

}