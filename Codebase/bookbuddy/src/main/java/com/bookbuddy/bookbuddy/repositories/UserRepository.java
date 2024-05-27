package com.bookbuddy.bookbuddy.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbuddy.bookbuddy.entities.User;
import com.bookbuddy.bookbuddy.exceptions.UserNotFoundException;

public interface UserRepository extends JpaRepository<User, Long> {

    default User findByEmail(String email) {
        return Optional.ofNullable(findByEmailIgnoreCase(email))
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    User findByEmailIgnoreCase(String email);
}
