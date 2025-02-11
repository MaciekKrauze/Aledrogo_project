package pl.com.webapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import pl.com.data.Entities.Song;
import pl.com.data.Repositories.SongRepository;
import pl.com.webapi.Services.SpotifyService;

import java.util.*;

class SpotifyServiceTest {

//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private SongRepository songRepository;
//
//    @InjectMocks
//    private SpotifyService spotifyService;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//        spotifyService = new SpotifyService(restTemplate, "dummyAccessToken", songRepository);
//    }
//
//    @Test
//    void saveLadyGagaSongs_success() {
//        List<String> tracks = List.of("Bad Romance", "Poker Face");
//        when(spotifyService.getTracksByArtist("Lady Gaga")).thenReturn(tracks);
//
//        spotifyService.saveLadyGagaSongs("Lady Gaga");
//
//        tracks.forEach(track -> {
//            Song song = new Song();
//            song.setName(track);
//            song.setArtist("Lady Gaga");
//            verify(songRepository, times(1)).save(song);
//        });
//    }
//
//    @Test
//    void getTracksByArtist_success() {
//        String artistName = "Lady Gaga";
//        String searchUrl = "https://api.spotify.com/v1/search?q=" + artistName + "&type=artist&limit=1";
//        String tracksUrl = "https://api.spotify.com/v1/artists/artistId/top-tracks?country=US";
//
//        Map<String, Object> artistMap = new HashMap<>();
//        artistMap.put("id", "artistId");
//
//        Map<String, Object> artistsMap = new HashMap<>();
//        artistsMap.put("items", List.of(artistMap));
//
//        Map<String, Object> bodyMap = new HashMap<>();
//        bodyMap.put("artists", artistsMap);
//
//        ResponseEntity<Map> response = new ResponseEntity<>(bodyMap, HttpStatus.OK);
//        when(restTemplate.exchange(eq(searchUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(Map.class))).thenReturn(response);
//
//        Map<String, Object> trackMap = new HashMap<>();
//        trackMap.put("name", "Bad Romance");
//
//        Map<String, Object> tracksMap = new HashMap<>();
//        tracksMap.put("tracks", List.of(trackMap));
//
//        ResponseEntity<Map> tracksResponse = new ResponseEntity<>(tracksMap, HttpStatus.OK);
//        when(restTemplate.exchange(eq(tracksUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(Map.class))).thenReturn(tracksResponse);
//
//        List<String> tracks = spotifyService.getTracksByArtist(artistName);
//
//        assertEquals(List.of("Bad Romance"), tracks);
//    }
//
//    @Test
//    void getTracksByArtist_artistNotFound() {
//        String artistName = "Unknown Artist";
//        String searchUrl = "https://api.spotify.com/v1/search?q=" + artistName + "&type=artist&limit=1";
//
//        Map<String, Object> artistsMap = new HashMap<>();
//        artistsMap.put("items", List.of());
//
//        Map<String, Object> bodyMap = new HashMap<>();
//        bodyMap.put("artists", artistsMap);
//
//        ResponseEntity<Map> response = new ResponseEntity<>(bodyMap, HttpStatus.OK);
//        when(restTemplate.exchange(eq(searchUrl), eq(HttpMethod.GET), any(HttpEntity.class), eq(Map.class))).thenReturn(response);
//
//        Exception exception = assertThrows(RuntimeException.class, () -> spotifyService.getTracksByArtist(artistName));
//        assertEquals("Artist not found", exception.getMessage());
//    }
}
