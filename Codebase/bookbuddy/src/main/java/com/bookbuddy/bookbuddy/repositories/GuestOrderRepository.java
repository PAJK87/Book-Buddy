package com.bookbuddy.bookbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookbuddy.bookbuddy.entities.GuestOrder;

@Repository
public interface GuestOrderRepository extends JpaRepository<GuestOrder, Long> {

}