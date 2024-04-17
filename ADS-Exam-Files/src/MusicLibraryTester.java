import java.util.Arrays;

public class MusicLibraryTester {

	/*
	 * Convert an array of songs to an array of strings. The calls toString on each
	 * item in the array. That means toString needs to work properly for Song.
	 */
	private static String[] songToString(Song[] arr) {
		String[] ret = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			ret[i] = arr[i].toString();
		}
		return ret;
	}

	/*
	 * Compare two arrays, arr1 and arr2, and make sure they are identical, but
	 * ignore the order. So, ["a","b","c"] is the same as ["b","a","c"].
	 * 
	 * This method is destructive.
	 * 
	 * This is useful for our testcases below.
	 */
	private static boolean compareArraysIgnoreOrder(String[] arr1, String[] arr2) {
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		return Arrays.equals(arr1, arr2);
	}

	public static void main(String[] args) throws Exception {

		System.out.print("Testing addSong without playlists...");
		// Create a new music library
		MusicLibrary test1MusicLibrary = new MusicLibrary();
		// add two songs (they don't have playlists
		test1MusicLibrary.addSong("Johnny Cash", "I Walk the Line", "The Broadcast Archive (Live)", "Blues", null);
		test1MusicLibrary.addSong("Rick Astley", "Never Gonna Give You Up", "Whenever You Need Somebody", "Pop", null);
		// Get back the list of songs
		Song[] test1Results = test1MusicLibrary.getAllSongs();
		// What do I expect as the result?
		String[] test1ExpectedRet = new String[] {
				"Song [artist=Johnny Cash, title=I Walk the Line, album=The Broadcast Archive (Live), genre=Blues, playlists=[]]",
				"Song [artist=Rick Astley, title=Never Gonna Give You Up, album=Whenever You Need Somebody, genre=Pop, playlists=[]]" };
		// Let's compare
		if (compareArraysIgnoreOrder(test1ExpectedRet, songToString(test1Results))) {
			System.out.println("passed.");
		} else {
			System.out.println("failed.");
			System.out.println(test1ExpectedRet);
			System.out.println(Arrays.toString(test1Results));
			return;
		}

		System.out.print("Testing addSong with playlists...");
		// Create a new music library
		MusicLibrary test2MusicLibrary = new MusicLibrary();
		// add two songs (they don't have playlists
		test2MusicLibrary.addSong("Johnny Cash", "I Walk the Line", "The Broadcast Archive (Live)", "Blues",
				new String[] { "Tunes", "Country Classics" });
		test2MusicLibrary.addSong("Rick Astley", "Never Gonna Give You Up", "Whenever You Need Somebody", "Pop",
				new String[] { "Bad", "For Enemies" });
		// Get back the list of songs
		Song[] test2Results = test2MusicLibrary.getAllSongs();
		// What do I expect as the result?
		String[] test2ExpectedRet = new String[] {
				"Song [artist=Johnny Cash, title=I Walk the Line, album=The Broadcast Archive (Live), genre=Blues, playlists=[Country Classics, Tunes]]",
				"Song [artist=Rick Astley, title=Never Gonna Give You Up, album=Whenever You Need Somebody, genre=Pop, playlists=[Bad, For Enemies]]" };
		// Let's compare
		if (compareArraysIgnoreOrder(test2ExpectedRet, songToString(test2Results))) {
			System.out.println("passed.");
		} else {
			System.out.println("failed.");
			System.out.println(test2ExpectedRet);
			System.out.println(Arrays.toString(test2Results));
			return;
		}

		// Your additional testcases go here.
		return;
	}
}