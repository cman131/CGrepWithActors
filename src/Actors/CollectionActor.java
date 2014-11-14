package Actors;

import akka.actor.UntypedActor;

import java.util.HashMap;


public class CollectionActor extends UntypedActor {
    private int fileCount;
    private HashMap<String, FoundMessage> founds;

    public CollectionActor() {
    }


    @Override
    public void onReceive(Object o) throws Exception {

    }

    public void sendFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public void sendFound(FoundMessage found) {
        founds.put(Thread.currentThread().getName(), found);
    }


}
