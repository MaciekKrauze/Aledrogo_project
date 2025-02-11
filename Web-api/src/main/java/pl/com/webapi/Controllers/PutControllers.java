package pl.com.webapi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.data.Entities.*;
import pl.com.webapi.Services.*;

@RestController
@RequestMapping("/api/put")
public class PutControllers {

    private final UserService userService;
    private final ProductService productService;
    private final SongService songService;

    public PutControllers(UserService userService, ProductService productService, SongService songService) {
        this.userService = userService;
        this.productService = productService;
        this.songService = songService;
    }

    @PutMapping("user/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody User user) {
        this.userService.update(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("product/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        this.productService.update(id, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("song/{id}")
    public ResponseEntity<Void> updateSong(@PathVariable Long id, @RequestBody Song song) {
        this.songService.update(id, song);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
