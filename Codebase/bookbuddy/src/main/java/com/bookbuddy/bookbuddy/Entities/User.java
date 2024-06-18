package com.bookbuddy.bookbuddy.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

// This is the User Entity. It represents a user of our application. We rarely return this whole object, but instead use it to represent a user in the database.
// It has a one-to-many relationship with BookCollection, a one-to-one relationship with Cart, and a one-to-many relationship with Order.
// It has a unique ID generated by the database, a unique ID generated by Firebase, a first name, a last name, an email, and a date of birth.
// It has a list of book collections, a cart, and a list of orders.
// For returning user information to the client, we use the UserDTO class.
// Jimmy Karoly

@Entity
@Schema(name = "User", description = "A user of the application")
@Table(name = "users")
public class User {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Unique ID generated by database corresponding to a user", example = "1")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Schema(description = "First name of the user", example = "John")
    @Column(name = "first_name")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    @Column(name = "last_name")
    private String lastName;

    @Schema(description = "Email of the user", example = "test@email.com")
    @Column(name = "email")
    private String email;

    @Schema(description = "Date of birth of the user", example = "1990-01-01")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Schema(description = "Description of the user", example = "I love reading!")
    @Column(name = "user_description")
    private String userDescription;

    @ManyToMany
    @JsonIgnoreProperties("users")
    @Schema(description = "List of favorite genres of the user", example = "[\"Fantasy\", \"Science Fiction\"]")
    @JoinTable(name = "user_genres", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @Schema(description = "List of book collections of the user")
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BookCollection> bookCollections;

    @Schema(description = "Cart of the user")
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserOrder> orders;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        if (cart != null) {
            cart.setUser(this);
        }
    }

    public User(String firstName, String lastName, String email, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {

    }

    public BookCollection findCollectionById(Long collectionId) {
        return this.bookCollections.stream()
                .filter(collection -> collection.getId().equals(collectionId))
                .findFirst()
                .orElse(null);
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
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

    public List<BookCollection> getBookCollections() {
        return bookCollections;
    }

    public void setBookCollections(List<BookCollection> bookCollections) {
        this.bookCollections = bookCollections;
    }

    // @Override
    // public String toString() {
    // return "User [id=" + id + ", firebaseUID=" + firebaseUID + ", firstName=" +
    // firstName + ", lastName=" + lastName
    // + ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", bookCollections="
    // + bookCollections
    // + ", cart=" + cart + "]";
    // }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public List<UserOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrder> orders) {
        this.orders = orders;
    }
}
