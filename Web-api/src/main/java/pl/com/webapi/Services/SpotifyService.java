package pl.com.webapi.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.com.data.Entities.Song;
import pl.com.data.Repositories.SongRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpotifyService {

    private final RestTemplate restTemplate;
    private final String accessToken;
    private final SongRepository songRepository;

    @Value("${spotify.api-url:https://api.spotify.com/v1}")
    private String spotifyApiUrl;

    public SpotifyService(RestTemplate restTemplate, String accessToken, SongRepository songRepository) {
        this.restTemplate = restTemplate;
        this.accessToken = accessToken;
        this.songRepository = songRepository;
    }

    public void saveLadyGagaSongs(String artistName) {
//        String artistName = "Lady Gaga";
        List<String> tracks = getTracksByArtist(artistName);

        tracks.forEach(track -> {
            Song song = new Song();
            song.setName(track);
            song.setArtist(artistName);
            songRepository.save(song);
        });
    }

    public List<String> getTracksByArtist(String artistName) {
        String searchUrl =  spotifyApiUrl + "/search?q=" + artistName + "&type=artist&limit=1";

        ResponseEntity<Map> response = restTemplate.exchange(
                searchUrl, HttpMethod.GET, new HttpEntity<>(getAuthHeader()), Map.class);
        List<Map> artists = (List<Map>) ((Map) response.getBody().get("artists")).get("items");

        if (artists.isEmpty()) {
            throw new RuntimeException("Artist not found");
        }

        String artistId = (String) artists.get(0).get("id");

        // Pobieranie utworów
        String tracksUrl = spotifyApiUrl + "/artists/" + artistId + "/top-tracks?country=US";
        ResponseEntity<Map> tracksResponse = restTemplate.exchange(
                tracksUrl, HttpMethod.GET, new HttpEntity<>(getAuthHeader()), Map.class);

        List<Map> tracks = (List<Map>) tracksResponse.getBody().get("tracks");

        return tracks.stream()
                .map(track -> (String) track.get("name"))
                .collect(Collectors.toList());
    }

    private HttpHeaders getAuthHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        return headers;
    }
}