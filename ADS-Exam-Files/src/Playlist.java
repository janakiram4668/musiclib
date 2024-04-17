/**
 * A Playlist class. It stores the songs associated with a playlist.
 * 
 * Important: It must be iterable so that you can use a for-each loop to iterate
 * over the songs in the playlist, in order.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;


public class Playlist implements Iterable<Song> {
	private List<Song> songs;

    public Playlist() {
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

	/**
	 * Get all of the songs that are on this playlist.
	 * 
	 * This must be O(1).
	 * 
	 * @return An array of all the songs on this playlist.
	 */
	public Song[] getSongs() {
        return songs.toArray(new Song[0]);
    }

	/**
	 * Sort the songs in this playlist by artist. Break ties by song title, then
	 * genre.
	 */
	public void sortByArtist() {
        songs.sort((s1, s2) -> {
            int artistComparison = s1.getArtist().compareTo(s2.getArtist());
            if (artistComparison != 0) return artistComparison;
            int titleComparison = s1.getTitle().compareTo(s2.getTitle());
            if (titleComparison != 0) return titleComparison;
            return s1.getGenre().compareTo(s2.getGenre());
        });
    }

	/**
	 * Sort the songs in this playlist by title. Break ties by artist, then genre.
	 */
	public void sortByTitle() {
        songs.sort((s1, s2) -> {
            int titleComparison = s1.getTitle().compareTo(s2.getTitle());
            if (titleComparison != 0) return titleComparison;
            int artistComparison = s1.getArtist().compareTo(s2.getArtist());
            if (artistComparison != 0) return artistComparison;
            return s1.getGenre().compareTo(s2.getGenre());
        });
    }

    @Override
    public java.util.Iterator<Song> iterator() {
        return songs.iterator();
    }
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return Objects.equals(songs, playlist.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songs != null ? Arrays.stream(songs.toArray()).sorted().toArray() : null);
    }
}
