package com.bookbuddy.bookbuddy.ServiceTests;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bookbuddy.bookbuddy.entities.Cart;
import com.bookbuddy.bookbuddy.entities.Genre;
import com.bookbuddy.bookbuddy.entities.User;
import com.bookbuddy.bookbuddy.entityDTOS.CreateUserDTO;
import com.bookbuddy.bookbuddy.entityDTOS.UserDTO;
import com.bookbuddy.bookbuddy.exceptions.UserNotFoundException;
import com.bookbuddy.bookbuddy.repositories.UserRepository;
import com.bookbuddy.bookbuddy.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO updatedUser;
    private CreateUserDTO newUser;

    @BeforeEach
    public void SetUp() {
        Long userId = 1L;
        Long cartId = 4L;

        Genre genre1 = new Genre();
        genre1.setId(4L);
        genre1.setName("Rock");
        Genre genre2 = new Genre();
        genre2.setId(8L);
        genre2.setName("Jazz");
        Set<Genre> genres = new HashSet<>();
        genres.add(genre2);
        genres.add(genre1);

        Cart cart = new Cart();
        cart.setCartId(cartId);

        LocalDate dateOfBirth = LocalDate.of(1987, 12, 07);

        user = new User("Jimmy", "Karoly", "pajk87@gmail.com", dateOfBirth);
        user.setId(userId);
        user.setCart(cart);
        user.setUserDescription("I love fantasy books.");
        user.setGenres(genres);

        newUser = new CreateUserDTO();
        newUser.setEmail("newUser@email.com");
        newUser.setFirstName("New");
        newUser.setLastName("User");
        newUser.setDescription("I love action and adventure books");
        LocalDate newUserDateOfBirth = LocalDate.of(1999, 12, 05);
        newUser.setDateOfBirth(newUserDateOfBirth);
        newUser.setGenres("Action, Adventure, Fiction");

    }

    @Test
    public void testGetUserDetails_ValidUserID() {
        Long userId = user.getUserId();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDTO userDTO = userService.getUserDetails(userId);

        assertEquals(userId, userDTO.getUserId());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getDateOfBirth(), userDTO.getDateOfBirth());
        assertEquals(user.getCart().getCartId(), userDTO.getCartId());
        assertEquals(user.getUserDescription(), userDTO.getUserDescription());
    }

    @Test
    public void testGetUserDetails_InvalidUserID() {
        Long userId = 2L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserDetails(userId));
    }

    @Test
    public void testAddNewUser() {
    }

    @Test
    public void testGetUserDetailsWithEmail() {
        String email = "pajk87@gmail.com";
        when(userRepository.findByEmailIgnoreCase(email)).thenReturn(user);

        UserDTO userDTO = userService.getUserDetailsWithEmail(email);

        assertEquals(user.getUserId(), userDTO.getUserId());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getDateOfBirth(), userDTO.getDateOfBirth());
        assertEquals(user.getCart().getCartId(), userDTO.getCartId());
        assertEquals(user.getUserDescription(), userDTO.getUserDescription());
    }

}
