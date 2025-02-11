package pl.com.core.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.com.data.Entities.Image;
import pl.com.webapi.Services.ImageService;


@Controller
public class ImageController {


    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Image image = imageService.getImage(id);

        if (image != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.valueOf(image.getType()));
            return new ResponseEntity<>(image.getData(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}