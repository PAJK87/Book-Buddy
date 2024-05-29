package com.bookbuddy.bookbuddy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbuddy.bookbuddy.entities.User;
import com.bookbuddy.bookbuddy.entities.UserOrder;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    List<UserOrder> findByUser(User user);
}
