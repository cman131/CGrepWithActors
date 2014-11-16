package Actors;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

public class CGrep {

	// The default file if no files are provided
	public static final String DEFAULT_FILE="";

	public CGrep(Pattern ptrn, List<String> files) {
		// Create a CollectionActor
		ActorSystem system = ActorSystem.create();
		Props porp = Props.create(CollectionActor.class);
		ActorRef collector =  system.actorOf(porp);
		
		// Send the filecount message
		collector.tell(files.size(), ActorRef.noSender());
		
		// Create ScanActor(s)
		ActorRef scanner;
		for (String fName : files) {
			porp = Props.create(ScanActor.class);
			scanner = system.actorOf(porp);
			scanner.tell(fName, collector);
		}
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
