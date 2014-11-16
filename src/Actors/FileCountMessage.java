package Actors;

/**
 * Created by kocsen on 11/14/14.
 */
public class FileCountMessage {
	public final int count;
	
	/**
	 * Creates a file count message
	 * @param fileCount - the number of inputted files to expect
	 */
	public FileCountMessage(final int fileCount){
		count = fileCount;
	}
}
