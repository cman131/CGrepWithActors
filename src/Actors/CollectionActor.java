package Actors;

import java.util.HashMap;

import akka.actor.ActorPath;
import akka.actor.ActorRef;


public class CollectionActor extends ActorRef{
	private int fileCount;
	private HashMap<String, FoundMessage> founds;
	
	public CollectionActor(){
	}
	
	public void sendFileCount(int fileCount){
		this.fileCount = fileCount;
	}
	
	public void sendFound(FoundMessage found){
		founds.put(Thread.currentThread().getName(), found);
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ActorPath path() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
