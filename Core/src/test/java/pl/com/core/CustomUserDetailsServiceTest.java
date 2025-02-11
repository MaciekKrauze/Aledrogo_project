package pl.com.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.com.core.Services.CustomUserDetailsService;
import pl.com.data.Entities.User;
import pl.com.data.Repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CustomUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;

    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customUserDetailsService = new CustomUserDetailsService(userRepository);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        // Arrange
        String username = "user@example.com";
        User mockUser = new User();
        mockUser.setEmail(username);
        mockUser.setPassword("password");
        mockUser.setRole("USER");

        when(userRepository.findByEmail(username)).thenReturn(mockUser);

        // Act
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String username = "user@example.com";

        when(userRepository.findByEmail(username)).thenReturn(null);

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void testLoadUserByUsername_UserRepositoryThrowsException() {
        // Arrange
        String username = "user@example.com";

        when(userRepository.findByEmail(username)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });
    }
}
