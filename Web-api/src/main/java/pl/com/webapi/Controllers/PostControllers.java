package pl.com.webapi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.data.Entities.*;
import pl.com.webapi.Services.*;

@RestController
@RequestMapping("/api/post")
public class PostControllers {

    private final UserService userService;
    private final ProductService productService;
    private final SongService songService;

    public PostControllers(UserService userService, ProductService productService, SongService songService) {
        this.userService = userService;
        this.productService = productService;
        this.songService = songService;
    }

    @PostMapping("user")
    public ResponseEntity<Void> postUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("product")
    public ResponseEntity<Void> postProduct(@RequestBody Product product) {
        productService.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("songs")
    public ResponseEntity<Void> postSongs(@RequestBody Song song) {
        songService.add(song);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
