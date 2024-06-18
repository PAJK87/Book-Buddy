package com.bookbuddy.bookbuddy.entityDTOS;

import com.bookbuddy.bookbuddy.entities.Genre;

public class GenreDTO {

    private Long id;
    private String name;

    public static GenreDTO fromEntity(Genre genre) {
        GenreDTO newGenreDTO = new GenreDTO();
        newGenreDTO.setId(genre.getId());
        newGenreDTO.setName(genre.getName());
        return newGenreDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
