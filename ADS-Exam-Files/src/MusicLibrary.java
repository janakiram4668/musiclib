import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MusicLibrary {

	private Map<String, Song> songs;
    private Map<String, Playlist> playlists;

    public MusicLibrary() {
        songs = new HashMap<>();
        playlists = new HashMap<>();
    }

	/**
	 * 
	 * Add a song to the music library. Throws the SongAlreadyExistsException if the
	 * song to be added already exists in the library.
	 * 
	 * This method should not perform linear search through a data structure.
	 * You are free to implement any sub-linear search algorithm.
	 * 
	 * @param artist    The artist of the song
	 * @param title     The title of the song
	 * @param album     The album the song is a part of
	 * @param genre     The genre of the song
	 * @param playlists The array of playlists this song is on. null if the song is
	 *                  not on any playlists.
	 * @throws SongAlreadyExistsException when the song already exists in the
	 *                                    library.
	 */
	public static class SongNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public SongNotFoundException() {
            super("Song not found.");
        }

        public SongNotFoundException(String message) {
            super(message);
        }
    }
	// Inside the MusicLibrary class

	private Playlist getOrCreatePlaylist(String playlistName) {
		playlists.putIfAbsent(playlistName, new Playlist());
		return playlists.get(playlistName);
	}


	public void addSong(String artist, String title, String album, String genre, String[] playlists)
        throws SongAlreadyExistsException {
    Song song = new Song(artist, title, album, genre, playlists);
    // Generate a key based on unique attributes of the song
    String key = artist + "|" + title + "|" + album + "|" + genre;
    if (songs.containsKey(key)) {
        throw new SongAlreadyExistsException();
    }
    songs.put(key, song);
    // Add the song to playlists
    if (playlists != null) {
        Arrays.sort(playlists); // Sort playlists alphabetically
        for (String playlist : playlists) {
            Playlist pl = getOrCreatePlaylist(playlist);
            pl.addSong(song);
        }
    }
}





	/**
	 * Return all of the songs in the library
	 * 
	 * This must be O(N), where N is the number of songs in the library.
	 * 
	 * The order of the songs in the array does not matter.
	 * 
	 * @return Array of songs
	 */
	public Song[] getAllSongs() {
        return songs.values().toArray(new Song[0]);
    }

	/**
	 * Input the music database from the file specified by filename. If the file
	 * does not exist, then add nothing to the library.
	 * 
	 * If a song in the file cannot be added to the library (for example, it already
	 * exists) then just skip that song and process the rest of the file.
	 * 
	 * This method has no specific efficiency requirements, but you should be able to load 250,000 songs in
	 * 10 to 15 seconds.
	 * 
	 * @param filename The file to load the music from
	 */
	public void loadMusicDb(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                String artist = parts[0];
                String title = parts[1];
                String album = parts[2];
                String genre = parts[3];
                String[] playlists = Arrays.copyOfRange(parts, 4, parts.length);

                try {
                    addSong(artist, title, album, genre, playlists);
                } catch (SongAlreadyExistsException e) {
                    // Skip duplicate songs
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Return all of the artists in the library without duplicates
	 * 
	 * This must be O(M), where M is the number of artists.
	 * 
	 * @return Array of artist names
	 */
	public String[] getAllArtists() {
        Set<String> artistSet = new HashSet<>();
        for (Song song : songs.values()) {
            artistSet.add(song.getArtist());
        }
        return artistSet.toArray(new String[0]);
    }

	/**
	 * Retrieve all of the songs by a given artist and return them
	 * 
	 * This must be O(M), where M is the number of songs by that artist.
	 * 
	 * @param artist The artist to search for
	 * @return An array of songs by the given artist. If the artist is not in the
	 *         MusicLibrary, return an empty array.
	 */
	public Song[] getSongsByArtist(String artist) {
        List<Song> artistSongs = new ArrayList<>();
        for (Song song : songs.values()) {
            if (song.getArtist().equals(artist)) {
                artistSongs.add(song);
            }
        }
        return artistSongs.toArray(new Song[0]);
    }

	/**
	 * Retrieve all of the genres in the library without duplicates
	 * 
	 * This must be O(M), where M is the number of genres.
	 * 
	 * @return An array of strings, with each string being a genre
	 */
	public String[] getGenres() {
        Set<String> genreSet = new HashSet<>();
        for (Song song : songs.values()) {
            genreSet.add(song.getGenre());
        }
        return genreSet.toArray(new String[0]);
    }

	/**
	 * Retrieve all of the songs with a given genre and return them
	 * 
	 * This must be O(M), where M is the number of songs within the specified genre.
	 * 
	 * @param genre The genre
	 * @return An array of songs with the given genre. If there are no songs with
	 *         that genre, return an empty array.
	 */
	public Song[] getSongsByGenre(String genre) {
        List<Song> genreSongs = new ArrayList<>();
        for (Song song : songs.values()) {
            if (song.getGenre().equals(genre)) {
                genreSongs.add(song);
            }
        }
        return genreSongs.toArray(new Song[0]);
    }

	/**
	 * Create a new playlist with the specified name, adding it to the list of
	 * playlists. If a playlist with that name already exists, do not create another
	 * one.
	 * 
	 * This method has no specific efficiency requirements, but be reasonable (linear or sub-linear).
	 * 
	 * @param playlistName The name of the playlist to create
	 */
	public void createPlaylist(String playlistName) {
        if (!playlists.containsKey(playlistName)) {
            playlists.put(playlistName, new Playlist());
        }
    }

	/**
	 * Add all songs with the specified title to the specified playlist. If the
	 * playlist does not already exist, throw the appropriate exception.
	 * 
	 * @param title        Title of song to add to the playlist
	 * @param playlistName The name of the playlist to add the song to
	 * @throws PlaylistNotFoundException when the playlist specified does not exist
	 */
	public void addSongToPlaylist(String title, String playlistName) throws PlaylistNotFoundException {
		if (!playlists.containsKey(playlistName)) {
			throw new PlaylistNotFoundException();
		}
		Song song = songs.get(title);
		if (song == null) {
			throw new SongNotFoundException();
		}
		playlists.get(playlistName).addSong(song);
	}
	

	/**
	 * Return all of the playlists in the library without duplicates
	 * 
	 * This must be O(M), where M is the number of playlists.
	 * 
	 * @return Array of playlist names
	 */
	public String[] getPlaylistNames() {
        return playlists.keySet().toArray(new String[0]);
    }

	/**
	 * 
	 * Return a Playlist object containing all of the songs currently in a playlist.
	 * 
	 * This must be O(M), where M is the number of songs in the playlist.
	 * 
	 * @param playlistName The name of the playlist
	 * @return A Playlist containing all of the songs in the Playlist.
	 * @throws PlaylistNotFoundException if the requested playlist doesn't exist
	 */
	public Playlist getPlaylist(String playlistName) throws PlaylistNotFoundException {
        Playlist playlist = playlists.get(playlistName);
        if (playlist == null) {
            throw new PlaylistNotFoundException();
        }
        return playlist;
    }

	/**
	 * Return all songs in the library sorted in a specific way
	 * 
	 * If howSorted is....
	 * 
	 * - "title": Sort by title. Break ties using artist, then genre.
	 * 
	 * - "artist": Sort by artist. Break ties using title, then genre.
	 * 
	 * - "genre": Sort by genre. Break ties using artist, then title.
	 * 
	 * - anything else: Return an empty array.
	 * 
	 * This function should be non-destructive.
	 *
	 * @param howSorted How to sort the songs
	 * @return Array of songs sorted as specified
	 */
	public Song[] getAllSongsSorted(String howSorted) {
		List<Song> sortedSongs = new ArrayList<>(songs.values());
	
		// Perform sorting based on the provided criteria
		switch (howSorted) {
			case "title":
				sortedSongs.sort(Comparator.comparing(Song::getTitle)
					.thenComparing(Song::getArtist)
					.thenComparing(Song::getGenre));
				break;
			case "artist":
				sortedSongs.sort(Comparator.comparing(Song::getArtist)
					.thenComparing(Song::getTitle)
					.thenComparing(Song::getGenre));
				break;
			case "genre":
				sortedSongs.sort(Comparator.comparing(Song::getGenre)
					.thenComparing(Song::getArtist)
					.thenComparing(Song::getTitle));
				break;
			default:
				return new Song[0]; // Return empty array for unsupported sorting criteria
		}
	
		return sortedSongs.toArray(new Song[0]);
	}

	/**
	 * Write the music database to the file specified by filename. The music DB must
	 * be output using the same format as the input. (So, you must be able to input
	 * a file that you output.)
	 * 
	 * @param filename The file to write the music to
	 */
	public void writeMusicDb(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			for (Song song : songs.values()) {
				bw.write(song.getArtist() + ";" + song.getTitle() + ";" + song.getAlbum() + ";" + song.getGenre());
	
				// Get playlists
				List<String> playlistsList = song.getPlaylists();
	
				// Check if playlists exist before converting
				if (playlistsList != null && !playlistsList.isEmpty()) {
					String[] playlists = playlistsList.toArray(new String[playlistsList.size()]);
	
					// Append playlists with delimiter if available
					for (int i = 0; i < playlists.length; i++) {
						bw.write(";" + playlists[i]);
					}
				}
	
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}