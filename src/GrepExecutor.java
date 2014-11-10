import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * Created by Geoff on 10/31/14.
 * <p/>
 * The executor
 */
public class GrepExecutor {
	
	private int files = -1;
	
    public GrepExecutor() {
    }
    
    public void sendFiles(int filecount){
    	files = filecount;
    }

    /**
     * Method called to start grepping the filenames with our Grepper!
     *
     * @param filenames
     * @param pattern
     */
    public void beginSearch(CompletionService completionService) {

    	while(files==-1){
    		try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
			}
    	}

		/* completionService.take() will wait until the next Found is completed
            which satisfied the requirement that we print the results as they
			are completed */
        for (int i = 0; i < files; i++) {
            Future<Found> future = null;
            try {
                // This will wait until we get a result
                future = completionService.take();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            // Now we have our future in our hands, get the final resulting Found object
            Found result = null;
            try {
                result = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            // Print the results of this query
            assert result != null;
            if (!result.getMatchingLines().isEmpty()) {
                System.out.println(result);
            }

        }
    }
}
