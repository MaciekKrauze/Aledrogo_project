package pl.com.webapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.com.data.Entities.*;
import pl.com.webapi.Controllers.DeleteControllers;
import pl.com.webapi.Exceptions.NotFoundException;
import pl.com.webapi.Services.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteControllersTest {

    @InjectMocks
    private DeleteControllers deleteControllers;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private SongService songService;

    @BeforeEach
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteUserExistsTest() {
        when(userService.getById(anyLong())).thenReturn(new User());
        ResponseEntity<Void> response = deleteControllers.deleteUser(1L);

        verify(userService, times(1)).delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteUserNotFoundTest() {
        when(userService.getById(anyLong())).thenReturn(null);

        ResponseEntity<Void> response = deleteControllers.deleteUser(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteProductExistsTest() {
        when(productService.getById(anyLong())).thenReturn(new Product());
        ResponseEntity<Void> response = deleteControllers.deleteProduct(1L);

        verify(productService, times(1)).delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteProductNotFoundTest() {
        when(productService.getById(anyLong())).thenReturn(null);

        ResponseEntity<Void> response = deleteControllers.deleteProduct(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteSongExistsTest() {
        when(songService.getSongById(anyLong())).thenReturn(new Song());
        ResponseEntity<Void> response = deleteControllers.deleteSong(1L);

        verify(songService, times(1)).delete(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void deleteSongNotFoundTest() {
        when(songService.getSongById(anyLong())).thenReturn(null);

        ResponseEntity<Void> response = deleteControllers.deleteSong(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}