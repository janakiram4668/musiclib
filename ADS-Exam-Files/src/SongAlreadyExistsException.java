/**
 * The exception to throw when a duplicate song is being added to the library.
 * 
 * Do not change this file.
 */
public class SongAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public SongAlreadyExistsException() {
		super("Song already exists");
	}

}
