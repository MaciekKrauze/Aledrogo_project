package pl.com.webapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.com.data.Entities.Product;
import pl.com.data.Entities.Song;
import pl.com.data.Entities.User;
import pl.com.data.Repositories.SongRepository;
import pl.com.webapi.Contracts.ProductDTO;
import pl.com.webapi.Contracts.SongDTO;
import pl.com.webapi.Contracts.UserDTO;
import pl.com.webapi.Controllers.GetControllers;
import pl.com.webapi.Services.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GetControllersTest {

    @InjectMocks
    private GetControllers getControllers;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @Mock
    private SongRepository songRepository;

    @BeforeEach
    public void openMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getUsersTest() {
        when(userService.getUsersList()).thenReturn(Arrays.asList(new User()));
        ResponseEntity<List<UserDTO>> responseEntity = getControllers.getUsers();
        verify(userService, times(1)).getUsersList();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getUserByIdTest() {
        when(userService.getById(anyLong())).thenReturn(new User());
        ResponseEntity<UserDTO> responseEntity = getControllers.getUserById(1L);
        verify(userService, times(1)).getById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getUserByNameTest() {
        when(userService.findByName(anyString())).thenReturn(Arrays.asList(new User()));
        ResponseEntity<List<UserDTO>> responseEntity = getControllers.getUserByName("test");
        verify(userService, times(1)).findByName("test");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getProductsTest() {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(new Product()));
        ResponseEntity<List<ProductDTO>> responseEntity = getControllers.getProducts();
        verify(productService, times(1)).getAllProducts();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getProductByIdTest() {
        when(productService.getById(anyLong())).thenReturn(new Product());
        ResponseEntity<ProductDTO> responseEntity = getControllers.getProductById(1L);
        verify(productService, times(1)).getById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getProductsByNameTest() {
        when(productService.findByName(anyString())).thenReturn(Arrays.asList(new Product()));
        ResponseEntity<List<ProductDTO>> responseEntity = getControllers.getProductsByName("test");
        verify(productService, times(1)).findByName("test");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getAllSongsTest() {
        when(songRepository.findAll()).thenReturn(Arrays.asList(new Song()));
        ResponseEntity<List<SongDTO>> responseEntity = getControllers.getAllSongs();
        verify(songRepository, times(1)).findAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getSongByIdTest() {
        when(songRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Song()));
        ResponseEntity<SongDTO> responseEntity = getControllers.getSongById(1L);
        verify(songRepository, times(1)).findById(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getSongsByNameTest() {
        when(songRepository.findByName(anyString())).thenReturn(Arrays.asList(new Song()));
        ResponseEntity<List<SongDTO>> responseEntity = getControllers.getSongsByName("test");
        verify(songRepository, times(1)).findByName("test");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}