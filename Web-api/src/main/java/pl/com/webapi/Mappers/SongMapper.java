package pl.com.webapi.Mappers;

import pl.com.data.Entities.Song;
import pl.com.webapi.Contracts.SongDTO;

public class SongMapper {

    public static SongDTO toDTO(Song song) {
        return new SongDTO(
                song.getId(),
                song.getName(),
                song.getAlbum(),
                song.getArtist(),
                song.getReleaseDate()
        );
    }

    public static Song toEntity(SongDTO songDTO) {
        Song song = new Song();
        song.setId(songDTO.getId());
        song.setName(songDTO.getName());
        song.setAlbum(songDTO.getAlbum());
        song.setArtist(songDTO.getArtist());
        song.setReleaseDate(songDTO.getReleaseDate());
        return song;
    }
}