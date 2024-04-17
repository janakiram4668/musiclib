/**
 * The exception to throw when a playlist is expect to be found, but isn't.
 * 
 * Do not change this file.
 */
public class PlaylistNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlaylistNotFoundException() {
		super("Playlist not found.");
	}

}
