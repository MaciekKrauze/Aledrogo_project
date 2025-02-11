package pl.com.data.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.data.Entities.Song;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByName(String name);

    Song findByNameAndArtist(String name, String artist);
}