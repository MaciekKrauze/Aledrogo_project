package pl.com.webapi.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.com.data.Entities.Song;
import pl.com.data.Repositories.SongRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;
    public Song getSongById(long id){
        return songRepository.findById(id).orElse(null);
    };

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void delete(Long id) {
        this.songRepository.deleteById(id);
    }

    public void add(Song song) {
        if (song != null) {
            songRepository.save(song);
        }
        else {
            throw new IllegalArgumentException("Song cannot be null");
        }
    }

    public void update(Long id, Song song) {
        Optional<Song> songOptional = songRepository.findById(id);
        if (songOptional.isPresent()) {
            Song existingSong = songOptional.get();

            if (song.getName() != null) {
                existingSong.setName(song.getName());
            }
            if (song.getAlbum() != null) {
                existingSong.setAlbum(song.getAlbum());
            }
            if (song.getArtist() != null) {
                existingSong.setArtist(song.getArtist());
            }
            if (song.getReleaseDate() != null) {
                existingSong.setReleaseDate(song.getReleaseDate());
            }

            songRepository.save(existingSong);
        } else {
            throw new EntityNotFoundException("Song with ID " + id + " not found");
        }
    }

    public List<Song> getSongsList() {
        return songRepository.findAll();
    }

    public void addSong(Song song) {
        songRepository.save(song);
    }

    public Song getSongByNameAndArtist(String name, String artist) {
        return songRepository.findByNameAndArtist(name, artist);
    }
}
