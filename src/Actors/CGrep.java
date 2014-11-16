package Actors;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * CGrep.java
 * 
 * This class creates a CollectionActor and ScanActors, specifically one
 * CollectionActor in addition to one ScanActor for each file provided to
 * the constructor.  The ScanActors are given a configuration which provides
 * them with the filename and the pattern they are to use on that file.  The 
 * CollectionActor is sent a message letting it know how many results to wait
 * for.
 * 
 * @author geoffberl
 */
public class CGrep {

	/**
	 * Constructor - Accepts a list of files and a pattern to look for.  A
	 * ScanActor is created for each filename provided.  A CollectionActor
	 * is created to query and handle the results from each ScanActor.
	 * @param ptrn the RegEx pattern to look for in the file(s)
	 * @param files a list of files to analyze.
	 */
	public CGrep(Pattern ptrn, List<String> files) {
		// Create a CollectionActor reference
		ActorSystem system = ActorSystem.create();
		Props porp = Props.create(CollectionActor.class);
		ActorRef collector = system.actorOf(porp);

		// Send the file count message to the collector
		collector.tell(new FileCountMessage(files.size()), ActorRef.noSender());

		// Create ScanActor(s) given the file names provided
		ActorRef scanner;
		for (String fName : files) {
			porp = Props.create(ScanActor.class);
			scanner = system.actorOf(porp);
			scanner.tell(new ConfigureMessage(fName, collector, ptrn), 
					ActorRef.noSender());
		}
	}


	/**
	 * Parse arguments, instantiate new CGREP which will start the actors
	 *
	 * @param args
	 */
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
		// If no files were provided, (list size is 0), ask for a file
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
