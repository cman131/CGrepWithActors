package Actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
//import akka.actor.Actors;


import java.util.HashMap;


public class CollectionActor extends UntypedActor {
    private int fileCount;
    private HashMap<String, FoundMessage> founds;

    public CollectionActor() {
    }

    @Override
    public void onReceive(Object o) throws Exception {
    	if(o instanceof FileCountMessage){
    		this.fileCount = ((FileCountMessage)o).count;
    	}
    	else{
    		founds.put(((FoundMessage)o).filename, (FoundMessage)o);
    		getSender().tell(akka.actor.PoisonPill.getInstance(), ActorRef.noSender());
    		System.out.println(o.toString());
    		if(fileCount==founds.size()){
    			System.exit(0);
    		}
    	}
    }

    
}
