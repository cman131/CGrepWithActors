package Actors;

import akka.actor.UntypedActor;

public class ScanActor extends UntypedActor {
    final String fileName;
    final CollectionActor collector;

    public ScanActor(final String filename, final CollectionActor collector) {
        this.fileName = filename;
        this.collector = collector;
    }

    @Override
    public void onReceive(Object o) throws Exception {

    }
}