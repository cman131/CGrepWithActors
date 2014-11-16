package Actors;

import akka.japi.Creator;

/**
 * Created by kocsen on 11/16/14.
 * Collection Acotr Creator
 */
public class CollectionActorCreator implements Creator<CollectionActor> {
    @Override
    public CollectionActor create() throws Exception {
        return new CollectionActor();
    }
}
