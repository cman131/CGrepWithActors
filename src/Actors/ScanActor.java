package Actors;

import akka.actor.ActorPath;
import akka.actor.ActorRef;

public class ScanActor extends ActorRef{
	final String fileName;
	final CollectionActor collector;
	
	public ScanActor(final String filename, final CollectionActor collector){
		this.fileName = filename;
		this.collector = collector;
	}
	
	public void run(){
		
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
