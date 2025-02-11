package pl.com.webapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.com.data.Entities.Address;
import pl.com.data.Entities.Favorite;
import pl.com.data.Entities.Product;
import pl.com.data.Entities.ShopHistory;
import pl.com.data.Entities.User;
import pl.com.data.Repositories.UserRepository;
import pl.com.webapi.Exceptions.NotFoundException;
import pl.com.webapi.Services.ProductService;
import pl.com.webapi.Services.UserService;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser() {
        User user = new User();
        userService.add(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserById_existingUser() {
        User user = new User();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User foundUser = userService.getById(1L);
        assertEquals(user, foundUser);
    }

    @Test
    void getUserById_nonExistingUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.getById(1L));
    }

    @Test
    void findByEmail_existingEmail() {
        User user = new User();
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        User foundUser = userService.findByEmail("test@example.com");
        assertEquals(user, foundUser);
    }

    @Test
    void findByName() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findByFirstName(anyString())).thenReturn(users);

        List<User> foundUsers = userService.findByName("John");
        assertEquals(users, foundUsers);
    }

    @Test
    void getUsersList() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        List<User> foundUsers = userService.getUsersList();
        assertEquals(users, foundUsers);
    }

    @Test
    void updateUser_existingUser() {
        User existingUser = new User();
        existingUser.setFirstName("Existing");
        User updatedUser = new User();
        updatedUser.setFirstName("Updated");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));

        userService.update(1L, updatedUser);

        verify(userRepository, times(1)).save(existingUser);
        assertEquals("Updated", existingUser.getFirstName());
    }

    @Test
    void updateUser_nonExistingUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        User updatedUser = new User();
        assertThrows(EntityNotFoundException.class, () -> userService.update(1L, updatedUser));
    }

    @Test
    void deleteUser_existingUser() {
        when(userRepository.existsById(anyLong())).thenReturn(true);
        userService.delete(1L);
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteUser_nonExistingUser() {
        when(userRepository.existsById(anyLong())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> userService.delete(1L));
    }

    @Test
    void createUser_success() {
        User user = new User();
        user.setEmail("new@example.com");
        user.setIfSeller(false);

        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        verify(userRepository, times(1)).save(user);
        assertEquals("USER", createdUser.getRole());
    }

    @Test
    void createUser_emailAlreadyInUse() {
        User user = new User();
        user.setEmail("existing@example.com");

        when(userRepository.findByEmail(anyString())).thenReturn(user);

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(user));
    }

    @Test
    void purchaseProduct_success() {
        User user = new User();
        user.setBalance(100.0);
        ShopHistory history = new ShopHistory();
        history.setProductsList(new ArrayList<>());
        user.setHistory(history);

        Product product = new Product();
        product.setPrice(50.0);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(productService.getById(anyLong())).thenReturn(product);

        userService.purchaseProduct(1L, 1L, false);

        verify(userRepository, times(1)).save(user);
        assertEquals(50.0, user.getBalance());
        assertTrue(user.getHistory().getProductsList().contains(product));
    }
    @Test
    void purchaseProduct_insufficientBalance() {
        User user = new User();
        user.setBalance(30.0);
        Product product = new Product();
        product.setPrice(50.0);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(productService.getById(anyLong())).thenReturn(product);

        assertThrows(RuntimeException.class, () -> userService.purchaseProduct(1L, 1L, false));
    }

    @Test
    void purchaseProduct_productNotFound() {
        User user = new User();
        user.setBalance(100.0);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(productService.getById(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> userService.purchaseProduct(1L, 1L, false));
    }
}
