package Actors;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import akka.actor.Actor;
import akka.actor.UntypedActor;

public class CGrep {

	// The default file if no files are provided
	public static final String DEFAULT_FILE="";

	public CGrep(Pattern ptrn, List<String> files) {
		// Create a CollectionActor
		Actor collector = new CollectionActor();
		// Start the collection actor
		// TODO: start collection
		// Send the filecount message
		// TODO: send filecount message
		
		// Create ScanActor(s)
		List<ScanActor> scanners = new ArrayList<ScanActor>();
		Actor scanner;
		for (String fName : files) {
			scanner = new ScanActor(ptrn);
			// TODO: Send the config mesage here
		}
		
		// Start scanners ... send references to the collector
	}


	public static void main(String[] args) {
		// Ensure there is at least one arg
		if (args.length <= 0) {
			System.out.println("usage: java <pattern> <filename(s)...>");
			System.exit(1);
		}

		// The pattern is the first arg.
		Pattern pattern = Pattern.compile(args[0]);

		// Create the list of files
		List<String> files = new ArrayList<String>();
		for (int i = 1; i < args.length; i++) {
			files.add(args[i]);
		}
		if (files.size() == 0) {
			// No files were provided, use the default file
			String input;
            Console console = System.console();
            input = console.readLine("Enter a file name:");
			files.add(input);
		}

		// Create CGrep
		new CGrep(pattern, files);
	}
}
