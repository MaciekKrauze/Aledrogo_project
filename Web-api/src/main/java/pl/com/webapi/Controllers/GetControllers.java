package pl.com.webapi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.data.Entities.*;
import pl.com.data.Repositories.SongRepository;
import pl.com.webapi.Contracts.ProductDTO;
import pl.com.webapi.Contracts.SongDTO;
import pl.com.webapi.Contracts.UserDTO;
import pl.com.webapi.Mappers.ProductMapper;
import pl.com.webapi.Mappers.SongMapper;
import pl.com.webapi.Mappers.UserMapper;
import pl.com.webapi.Services.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/get")
public class GetControllers {

    private final UserService userService;
    private final ProductService productService;
    private final SongRepository songRepository;

    public GetControllers(UserService userService, ProductService productService,  SongRepository songRepository) {
        this.userService = userService;
        this.songRepository = songRepository;
        this.productService = productService;
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = (List<User>) this.userService.getUsersList();
        List<UserDTO> userDTOs = users.stream()
                .map(UserMapper::userToUserDTO)
                .toList();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping("user/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = this.userService.getById(id);
        return new ResponseEntity<>(UserMapper.userToUserDTO(user), HttpStatus.OK);
    }

    @GetMapping("users/name/{name}")
    public ResponseEntity<List<UserDTO>> getUserByName(@PathVariable String name) {
        List<User> users = (List<User>) this.userService.findByName(name);
        List<UserDTO> userDTOs = users.stream()
                .map(UserMapper::userToUserDTO)
                .toList();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }




    @GetMapping("products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<Product> products = (List<Product>) this.productService.getAllProducts();
        List<ProductDTO> productDTOs = products.stream()
                .map(ProductMapper::toDTO)
                .toList();
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @GetMapping("product/id/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = this.productService.getById(id);
        ProductDTO productDTO = ProductMapper.toDTO(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("products/name/{name}")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String name) {
        List<Product> products = (List<Product>) this.productService.findByName(name);
        List<ProductDTO> productDTOs = products.stream()
                .map(ProductMapper::toDTO)
                .toList();
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }



    @GetMapping("songs")
    public ResponseEntity<List<SongDTO>> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        List<SongDTO> songDTOs = songs.stream()
                .map(SongMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(songDTOs, HttpStatus.OK);
    }

    @GetMapping("songs/id/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Song not found with id: " + id));
        SongDTO songDTO = SongMapper.toDTO(song);
        return new ResponseEntity<>(songDTO, HttpStatus.OK);
    }

    @GetMapping("songs/name/{name}")
    public ResponseEntity<List<SongDTO>> getSongsByName(@PathVariable String name) {
        List<Song> songs = songRepository.findByName(name);
        List<SongDTO> songDTOs = songs.stream()
                .map(SongMapper::toDTO)
                .toList();
        return new ResponseEntity<>(songDTOs, HttpStatus.OK);
    }
}
