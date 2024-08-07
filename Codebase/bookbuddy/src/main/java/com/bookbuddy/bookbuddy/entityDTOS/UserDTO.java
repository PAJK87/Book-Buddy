package com.bookbuddy.bookbuddy.entityDTOS;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import com.bookbuddy.bookbuddy.entities.User;

import io.swagger.v3.oas.annotations.media.Schema;

// This is the UserDTO class. It is a Data Transfer Object (DTO) that is used to send user information back to the client.
// It has an id, first name, a last name, an email, and a date of birth. 
// This main difference between this class and the User class is that this class does not have a reference to the Order entity, only the user ID.
// Jimmy Karoly

@Schema(name = "UserDTO", description = "A DTO for a user")
public class UserDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Unique ID generated by database corresponding to a user", example = "1")
    private Long userId;

    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @Schema(description = "Email of the user", example = "test@email.com")
    private String email;

    @Schema(description = "Date of birth of the user", example = "1990-01-01")
    private LocalDate dateOfBirth;

    @Schema(description = "Unique ID corresponding to a user's cart", example = "1")
    private Long cartId;

    @Schema(description = "Description of the user", example = "I love reading!")
    private String userDescription;

    @Schema(description = "List of genres that the user is interested in")
    private Set<GenreDTO> genres;

    public static UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setCartId(user.getCart().getCartId());
        userDTO.setDescription(user.getUserDescription());
        Set<GenreDTO> genreDTOs = user.getGenres().stream()
                .map(genre -> {
                    GenreDTO genreDTO = new GenreDTO();
                    genreDTO.setId(genre.getId());
                    genreDTO.setName(genre.getName());
                    return genreDTO;
                })
                .collect(Collectors.toSet());
        userDTO.setGenres(genreDTOs);
        return userDTO;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public Set<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreDTO> genres) {
        this.genres = genres;
    }
}
