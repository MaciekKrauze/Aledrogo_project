package pl.com.webapi.Contracts;

public class SongDTO {

    private Long id;
    private String name;
    private String album;
    private String artist;
    private String releaseDate;

    public SongDTO() {
    }

    public SongDTO(Long id, String name, String album, String artist, String releaseDate) {
        this.id = id;
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.releaseDate = releaseDate;
    }

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