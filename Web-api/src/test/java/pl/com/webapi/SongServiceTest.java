package pl.com.webapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.EntityNotFoundException;
import pl.com.data.Entities.Song;
import pl.com.data.Repositories.SongRepository;
import pl.com.webapi.Services.SongService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongService songService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSong_success() {
        Song song = new Song();
        songService.add(song);
        verify(songRepository, times(1)).save(song);
    }

    @Test
    void addSong_nullSong() {
        assertThrows(IllegalArgumentException.class, () -> songService.add(null));
    }

    @Test
    void getSongById_existingSong() {
        Song song = new Song();
        when(songRepository.findById(anyLong())).thenReturn(Optional.of(song));

        Song foundSong = songService.getSongById(1L);
        assertEquals(song, foundSong);
    }

    @Test
    void getSongById_nonExistingSong() {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());

        Song foundSong = songService.getSongById(1L);
        assertNull(foundSong);
    }

    @Test
    void deleteSong_existingSong() {
        songService.delete(1L);
        verify(songRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateSong_existingSong() {
        Song existingSong = new Song();
        existingSong.setName("Existing Song");
        Song updatedSong = new Song();
        updatedSong.setName("Updated Song");

        when(songRepository.findById(anyLong())).thenReturn(Optional.of(existingSong));

        songService.update(1L, updatedSong);

        verify(songRepository, times(1)).save(existingSong);
        assertEquals("Updated Song", existingSong.getName());
    }

    @Test
    void updateSong_nonExistingSong() {
        when(songRepository.findById(anyLong())).thenReturn(Optional.empty());
        Song updatedSong = new Song();

        assertThrows(EntityNotFoundException.class, () -> songService.update(1L, updatedSong));
    }

    @Test
    void getSongsList() {
        List<Song> songs = new ArrayList<>();
        when(songRepository.findAll()).thenReturn(songs);

        List<Song> foundSongs = songService.getSongsList();
        assertEquals(songs, foundSongs);
    }

    @Test
    void getSongByNameAndArtist_existingSong() {
        Song song = new Song();
        when(songRepository.findByNameAndArtist(anyString(), anyString())).thenReturn(song);

        Song foundSong = songService.getSongByNameAndArtist("Song Name", "Artist Name");
        assertEquals(song, foundSong);
    }
}
