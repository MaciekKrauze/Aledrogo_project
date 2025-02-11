package pl.com.webapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.com.data.Entities.*;
import pl.com.webapi.Controllers.PostControllers;
import pl.com.webapi.Services.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PostControllersTest {

    @InjectMocks
    private PostControllers postControllers;

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
    public void postUserTest() {
        User user = new User();
        ResponseEntity<Void> responseEntity = postControllers.postUser(user);

        verify(userService, times(1)).add(user);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void postProductTest() {
        Product product = new Product();
        ResponseEntity<Void> responseEntity = postControllers.postProduct(product);

        verify(productService, times(1)).add(product);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void postSongsTest() {
        Song song = new Song();
        ResponseEntity<Void> responseEntity = postControllers.postSongs(song);

        verify(songService, times(1)).add(song);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
}