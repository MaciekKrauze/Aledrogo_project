package pl.com.core.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.com.webapi.Services.SpotifyService;

import java.util.List;

@RestController
public class SpotifyController {

    private final SpotifyService spotifyService;

    public SpotifyController(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @GetMapping("/tracks")
    public List<String> getTracksByArtist() {
        return spotifyService.getTracksByArtist("Lady Gaga");
    }

    @GetMapping("/save_songs")
    public String saveSongs(@RequestParam String artistName) {
        spotifyService.saveLadyGagaSongs(artistName);
        return "Lady Gaga songs saved to the database!";
    }
}
