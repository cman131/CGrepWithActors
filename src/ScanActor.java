
public class ScanActor extends Thread{
	final String fileName;
	final CollectionActor collector;
	
	public ScanActor(final String filename, final CollectionActor collector){
		this.fileName = filename;
		this.collector = collector;
	}
	
	public void run(){
		
	}
}
