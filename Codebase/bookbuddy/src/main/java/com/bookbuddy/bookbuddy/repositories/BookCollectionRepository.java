package com.bookbuddy.bookbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbuddy.bookbuddy.entities.BookCollection;
import com.bookbuddy.bookbuddy.entities.User;

public interface BookCollectionRepository extends JpaRepository<BookCollection, Long> {
    BookCollection findByUserAndCollectionName(User user, String name);
}