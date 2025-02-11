package pl.com.core.Controllers.General;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import pl.com.core.Components.SeedScheduler;
import pl.com.webapi.Services.SpotifyService;

import java.util.ArrayList;

@Controller
public class LoteryController {

    private final SeedScheduler seedScheduler;
    private final SpotifyService spotifyService;

    public LoteryController(SeedScheduler seedScheduler, SpotifyService spotifyService) {
        this.seedScheduler = seedScheduler;
        this.spotifyService = spotifyService;
    }

    @GetMapping("/Lotery")
    public String lotery(Model model, HttpSession session) {

        Boolean userGuess = (Boolean) session.getAttribute("userGuess");
        if (userGuess != null && userGuess) {
            model.addAttribute("userGuess", true);
        } else if (userGuess == null) {
            model.addAttribute("userGuess", null);
        } else {
            model.addAttribute("userGuess", false);
        }
        return "General/Main/Lotery";
    }

    @PostMapping("/Lotery")
    public RedirectView lotery(Model model, HttpSession session , @RequestParam String userGuess) {
        ArrayList<String> ladyGagaSongsList = (ArrayList<String>) spotifyService.getTracksByArtist("Lady Gaga");

        long seed = seedScheduler.getSeed();

        String songOfDay = ladyGagaSongsList.get((int) seed);
        System.out.println(songOfDay);
        System.out.println(userGuess);

        if (songOfDay.equals(userGuess)){
            session.setAttribute("userGuess", true);
        }
        else {
            session.setAttribute("userGuess", false);

        }
        return new RedirectView("/Lotery");
    }


}
