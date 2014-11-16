package Actors;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import java.util.HashMap;


/**
 * The Actor created for collecting found results from each of the scan actors.
 * It is also responsible for printing out each result received and stopping the actors when done.
 * 
 * @author Conor
 *
 */
public class CollectionActor extends UntypedActor {
    private int fileCount = -1;
    private HashMap<String, FoundMessage> founds = new HashMap<String, FoundMessage>();

    @Override
    public void onReceive(Object o) throws Exception {
    	if(o instanceof FileCountMessage){
    		// Set my fileCount or throw an exception if being set again
    		if(this.fileCount!=-1){
    			throw new Exception("Can't set file count twice!");
    		}
    		this.fileCount = ((FileCountMessage)o).count;
    	}
    	else{
    		// Save the FoundMessage sent from a ScanActor
    		String file = ((FoundMessage)o).filename==null ? "-" : ((FoundMessage)o).filename;
    		founds.put(file, (FoundMessage)o);
    		
    		// kill the sending actor with a poison pill
    		getSender().tell(akka.actor.PoisonPill.getInstance(), ActorRef.noSender());
    		
    		// print out the results for this file
    		System.out.println(o.toString());
    		if(fileCount==founds.size()){
    			// Actors.registry() does not exist in the current version of akka
    			System.exit(0);
    		}
    	}
    }

    
}
