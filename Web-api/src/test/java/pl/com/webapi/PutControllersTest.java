package pl.com.webapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.com.data.Entities.*;
import pl.com.webapi.Controllers.PutControllers;
import pl.com.webapi.Services.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PutControllersTest {

    @InjectMocks
    private PutControllers putControllers;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private SongService songService;

    @BeforeEach
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        ResponseEntity<Void> responseEntity = putControllers.updateUser(1L, user);

        verify(userService, times(1)).update(1L, user);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateProductTest() {
        Product product = new Product();
        ResponseEntity<Void> responseEntity = putControllers.updateProduct(1L, product);

        verify(productService, times(1)).update(1L, product);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateSongTest() {
        Song song = new Song();
        ResponseEntity<Void> responseEntity = putControllers.updateSong(1L, song);

        verify(songService, times(1)).update(1L, song);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}