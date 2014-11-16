package Actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScanActor extends UntypedActor {

    private Pattern pattern;

    @Override
    public void onReceive(Object o) throws Exception {
        ConfigureMessage message = (ConfigureMessage) o;

        System.out.println(String.format("Scan Actor for %s received message. Starting to Grep...", message.getFilename()));
        String fileName = message.getFilename();
        ActorRef responseActor = message.getCollectionActor();
        this.pattern = message.getPattern();

        ArrayList<String> results = new ArrayList<String>();
        Matcher matcher;
        File file = new File(fileName);
        Scanner sc = null;
        try {
            int lineCount = 1;
            sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                matcher = this.pattern.matcher(line);
                if (matcher.find()) {
                    results.add("Line " + lineCount + ": " + line);
                }
                lineCount++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Sorry, just that if there is an exception, this would never have been closed.
            if (sc != null) {
                sc.close();
            }
        }

        responseActor.tell(new FoundMessage(fileName, results), getSelf());
    }
}
