package Actors;

import akka.actor.ActorRef;

import java.util.regex.Pattern;

/**
 * Created by kocsen on 11/14/14.
 * Message sent over to scan actors
 */
public class ConfigureMessage {

    public final String filename;
    public final Pattern pattern;
    public final ActorRef collectionActor;


    /**
     * Basic way to keep track of a configuration, with filename, the actor ref and the pattern.
     *
     * @param filename
     * @param collectionActor
     * @param pattern
     */
    public ConfigureMessage(String filename, ActorRef collectionActor, Pattern pattern) {
        this.filename = filename;
        this.collectionActor = collectionActor;
        this.pattern = pattern;
    }

    /**
     * Getters and setters
     *
     * @return the appropriate instance variable
     */
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
