package Actors;

import akka.actor.ActorRef;

/**
 * Created by kocsen on 11/14/14.
 * Message sent over to scan actors
 */
public class ConfigureMessage {

    public final String filename;
    public final ActorRef collectionActor;


    public ConfigureMessage(String filename, ActorRef collectionActor) {
        this.filename = filename;
        this.collectionActor = collectionActor;
    }

    public String getFilename() {
        return filename;
    }

    public ActorRef getCollectionActor() {
        return collectionActor;
    }
}
