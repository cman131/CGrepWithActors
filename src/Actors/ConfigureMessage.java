package Actors;

import akka.actor.ActorRef;

/**
 * Created by kocsen on 11/14/14.
 */
public class ConfigureMessage {

    public final String filename;
    public final ActorRef collectionActor;

    public ConfigureMessage(String filename, ActorRef collectionActor) {
        this.filename = filename;
        this.collectionActor = collectionActor;
    }
}
