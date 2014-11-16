package Actors;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CGrep {

	// The default file if no files are provided
	public static final String DEFAULT_FILE="";

	public CGrep(Pattern ptrn, List<String> files) {
		// Create a CollectionActor
		CollectionActor collector = new CollectionActor();

		// Create ScanActor(s)
		List<ScanActor> scanners = new ArrayList<ScanActor>();
		for (String fName : files) {
			scanners.add(new ScanActor(ptrn));
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
			files.add(CGrep.DEFAULT_FILE);
		}

		// Create CGrep
		new CGrep(pattern, files);
	}
}
