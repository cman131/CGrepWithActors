package Actors;

import java.util.HashMap;


public class CollectionActor extends Thread{
	private int fileCount;
	private HashMap<String, Found> founds;
	
	public CollectionActor(){
	}
	
	public void sendFileCount(int fileCount){
		this.fileCount = fileCount;
	}
	
	public void sendFound(Found found){
		founds.put(Thread.currentThread().getName(), found);
	}
	
}
