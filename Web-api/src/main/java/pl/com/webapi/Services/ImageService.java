package pl.com.webapi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.com.data.Entities.Image;
import pl.com.data.Repositories.ImageRepository;

import java.util.Optional;

@Controller
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;


    public void saveImage(Image image) { imageRepository.save(image); }

    public Image getImage(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        return image.orElse(null);
    }

}