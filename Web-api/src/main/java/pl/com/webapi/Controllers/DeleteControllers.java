package pl.com.webapi.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.webapi.Exceptions.NotFoundException;
import pl.com.webapi.Services.*;

@RestController
@RequestMapping("/api/delete")
public class DeleteControllers {

    private static final Logger logger = LoggerFactory.getLogger(DeleteControllers.class);

    private final UserService userService;
    private final ProductService productService;
    private final SongService songService;

    public DeleteControllers(UserService userService,
                             ProductService productService, SongService songService) {
        this.userService = userService;
        this.productService = productService;
        this.songService = songService;
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            if (userService.getById(id) == null) {
                throw new NotFoundException("User");
            }
            userService.delete(id);
        } catch (NotFoundException e) {
            logger.warn("User not found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.warn("An error occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            if (productService.getById(id) == null) {
                throw new NotFoundException("Product");
            }
            productService.delete(id);
        } catch (NotFoundException e) {
            logger.warn("Product not found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.warn("An error occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("song/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        try {
            if (songService.getSongById(id) == null) {
                throw new NotFoundException("Song");
            }
            songService.delete(id);
        } catch (NotFoundException e) {
            logger.warn("Song not found: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.warn("An error occurred: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
