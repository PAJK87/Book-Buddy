package com.bookbuddy.bookbuddy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookbuddy.bookbuddy.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);
}
