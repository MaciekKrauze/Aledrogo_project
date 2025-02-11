package pl.com.data.Entities;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;


@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tytuł nie może być pusty")
    private String name;
    private String album;
    @NotEmpty(message = "Imię i nazwisko artysty są wymagane")
    private String artist;
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Data wydania musi być w formacie dd/mm/yyyy")
    private String releaseDate;

    public Song() {
    }

    public Song(String name, String album, String artist, String releaseDate) {
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.releaseDate = releaseDate;
    }

    // Gettery i Settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

}

