package pl.com.core.Components;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.com.webapi.Services.SpotifyService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

@Component
public class SeedScheduler {

    private final SpotifyService spotifyService;
    private volatile long seed;

    public SeedScheduler(SpotifyService spotifyService) {
        this.spotifyService = spotifyService;
    }

    @Scheduled(cron="0 0 0 * * ?")
    public void calculateSeed() {
        ArrayList<String> ladyGagaSongsList = (ArrayList<String>) spotifyService.getTracksByArtist("Lady Gaga");

        LocalDate today = LocalDate.now();
        LocalDate startOfYear = LocalDate.of(today.getYear(), 1, 1);
        long dayOfYear = ChronoUnit.DAYS.between(startOfYear, today) + 1;

        this.seed = (dayOfYear * 1230123 * 73274) % ladyGagaSongsList.size();
    }

    public long getSeed() {
        return seed;
    }
}
