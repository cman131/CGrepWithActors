package Actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScanActor extends UntypedActor {

    private Pattern pattern;

    /**
     * On receive message.
     * <p/>
     * WE only receive one message, thats a ConfigureMessage.
     * <p/>
     * When we get the configure message, it will give us enough information
     * to start grepping through the file.
     * <p/>
     * While grepping through the file we pul all results in an array list.
     * <p/>
     * Finally we send the response actor a Found Message with our results.
     *
     * @param o - Message, only of type ConfigureMessage
     * @throws Exception
     */
    @Override
    public void onReceive(Object o) throws Exception {
        ConfigureMessage message = (ConfigureMessage) o;

        System.out.println(String.format("Scan Actor for %s received message. Starting to Grep...", message.getFilename()));
        String fileName = message.getFilename();
        ActorRef responseActor = message.getCollectionActor();
        this.pattern = message.getPattern();

        Scanner sc;

        if (fileName == null) {
            sc = new Scanner(System.in);
        } else {
            sc = new Scanner(new File(fileName));
        }

        ArrayList<String> results = new ArrayList<String>();
        Matcher matcher;
        try {
            int lineCount = 1;

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                matcher = this.pattern.matcher(line);
                if (matcher.find()) {
                    results.add("Line " + lineCount + ": " + line);
                }
                lineCount++;
            }
        } finally {
            // Just in case, close
            if (sc != null) {
                sc.close();
            }
        }

        responseActor.tell(new FoundMessage(fileName, results), getSelf());
    }
}
