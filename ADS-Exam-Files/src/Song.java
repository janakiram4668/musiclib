/**
 * The Song class. A song object stores information about an individual song.
 * 
 * The toString() for this class should produce output in a specific format.
 * Check the handout for details.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;
import java.util.Collections;




public class Song {
	private String artist;
    private String title;
    private String album;
    private String genre;
    private List<String> playlists;

	/**
	 * The constructor for a song
	 * 
	 * @param artist The artist of the song
	 * @param title  The title of the song
	 * @param album  The album the song is in
	 * @param genre  The genre of the song
	 */
	public Song(String artist, String title, String album, String genre, String[] playlists) {
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.genre = genre;
        if (playlists != null) {
            this.playlists = new ArrayList<>(Arrays.asList(playlists));
        } else {
            this.playlists = new ArrayList<>();
        }
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public List<String> getPlaylists() {
        return playlists;
    }

	@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Song [artist=").append(artist)
            .append(", title=").append(title)
            .append(", album=").append(album)
            .append(", genre=").append(genre)
            .append(", playlists=");
    if (playlists.isEmpty()) {
        sb.append("[]");
    } else {
        List<String> sortedPlaylists = new ArrayList<>(playlists);
        Collections.sort(sortedPlaylists); // Sort playlists alphabetically
        sb.append(sortedPlaylists.toString());
    }
    sb.append("]");
    return sb.toString();
}

	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Song song = (Song) o;
		return Objects.equals(artist, song.artist) &&
				Objects.equals(title, song.title) &&
				Objects.equals(album, song.album) &&
				Objects.equals(genre, song.genre) &&
				Objects.equals(playlists, song.playlists);
	}

	@Override
	public int hashCode() {
		return Objects.hash(artist, title, album, genre, playlists);
	}
}


