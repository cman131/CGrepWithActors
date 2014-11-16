package Actors;

import java.util.regex.Pattern;

import akka.actor.ActorRef;

/**
 * Created by kocsen on 11/14/14.
 * Message sent over to scan actors
 */
public class ConfigureMessage {

    public final String filename;
    public final Pattern pattern;
    public final ActorRef collectionActor;


    public ConfigureMessage(String filename, ActorRef collectionActor, Pattern pattern) {
        this.filename = filename;
        this.collectionActor = collectionActor;
        this.pattern = pattern;
    }

    public String getFilename() {
        return filename;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public ActorRef getCollectionActor() {
        return collectionActor;
    }
}
