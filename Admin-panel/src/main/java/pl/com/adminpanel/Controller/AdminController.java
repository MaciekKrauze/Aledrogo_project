package pl.com.adminpanel.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.data.Entities.Product;
import pl.com.data.Entities.Song;
import pl.com.data.Entities.User;
import pl.com.webapi.Exceptions.AlreadyExistException;
import pl.com.webapi.Exceptions.NotFoundException;
import pl.com.webapi.Exceptions.WrongFormatException;
import pl.com.webapi.Services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Pattern;

@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final UserService userService;
    private final ProductService productService;
    private final SongService songService;

    @Autowired
    public AdminController(UserService userService,
                           ProductService productService, SongService songService) {
        this.userService = userService;
        this.productService = productService;
        this.songService = songService;
    }

    @GetMapping("/admin/adminPanel")
    public String adminPanelAdmin(Model model, HttpSession session) {
        String category = (String) session.getAttribute("category");

        model.addAttribute("users", null);
        model.addAttribute("products", null);
        model.addAttribute("songs", null);

        if (category != null) {
            switch (category) {
                case "users":
                    List<User> usersList = userService.getUsersList();
                    model.addAttribute("users", usersList);
                    break;
                case "products":
                    List<Product> productsList = (List<Product>) productService.getAllProducts();
                    model.addAttribute("products", productsList);
                    break;
                case "songs":
                    List<Song> songsList = songService.getSongsList();
                    model.addAttribute("songs", songsList);
                    break;
                default:
                     logger.warn("Unexpected category: " + category);
                    break;
            }
        }
        return "/Admin/Admin_panel";
    }

    @PostMapping("/admin/adminPanel")
    public RedirectView adminPanelAdmin(@RequestParam("category") String category, HttpSession session) {
        session.setAttribute("category", category);
        return new RedirectView("/admin/adminPanel");
    }

    @PostMapping("/admin/song/add")
    public String addSong(@Valid @ModelAttribute Song song, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            model.addAttribute("songs", songService.getSongsList());
            return "/Admin/Admin_panel";
        }

        try {
            Song songByNameAndArtist = songService.getSongByNameAndArtist(song.getName(), song.getArtist());
            if (songByNameAndArtist == null) {
                songService.addSong(song);
            } else {
                throw new AlreadyExistException("Song");
            }
        } catch (WrongFormatException e) {
            logger.warn("Wrong format: " + e.getMessage());
            return "/Admin/Admin_panel";
        } catch (AlreadyExistException e) {
            logger.warn("Song already exists: " + e.getMessage());
            return "/Admin/Admin_panel";
        } catch (Exception e) {
            logger.warn("Unexpected error: " + e.getMessage());
            return "/Admin/Admin_panel";
        }

        return "redirect:/admin/adminPanel";
    }

    @DeleteMapping("/admin/delete/song")
    public RedirectView deleteSong(@RequestParam Long id, HttpSession session) {
        try {
            Song songById = songService.getSongById(id);
            if (songById == null) {
                throw new NotFoundException("Song");
            }
            songService.delete(id);
        } catch (NotFoundException e) {
             logger.warn("Song not found: ", e);
            session.setAttribute("errorMessage", "Song not found.");
        } catch (Exception e) {
             logger.warn("An error occurred: ", e);
            session.setAttribute("errorMessage", "An unexpected error occurred.");
        }
        return new RedirectView("/admin/adminPanel");
    }

    @PutMapping("/admin/song/update")
    public RedirectView updateSong(@RequestParam Long id,
                                   @RequestParam String name, @RequestParam String artist,
                                   @RequestParam String album, @RequestParam String releaseDate,
                                   HttpSession session) {
        try {
            Song songById = songService.getSongById(id);

            if (songById == null) {
                throw new NotFoundException("Song");
            }

            Song song = new Song();
            song.setId(id);
            if (name == null || name.isEmpty()) {
                song.setName(songById.getName());
            } else {
                song.setName(name);
            }

            if (artist == null || artist.isEmpty()) {
                song.setArtist(songById.getArtist());
            } else {
                song.setArtist(artist);
            }

            if (album == null || album.isEmpty()) {
                song.setAlbum(songById.getAlbum());
            } else {
                song.setAlbum(album);
            }

            if (releaseDate == null || releaseDate.isEmpty()) {
                song.setReleaseDate(songById.getReleaseDate());
            } else {
                if (!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", releaseDate)) {
                    throw new WrongFormatException();
                }
                song.setReleaseDate(releaseDate);
            }

            songService.update(id, song);
        } catch (NotFoundException e) {
             logger.warn("Song not found: ", e);
        }catch (WrongFormatException e) {
            logger.warn("Wrong format: " + e.getMessage());
        }
        catch (Exception e) {
             logger.warn("An error occurred: ", e);
        }
        return new RedirectView("/admin/adminPanel");
    }

}
