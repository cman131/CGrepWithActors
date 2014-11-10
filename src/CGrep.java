import java.io.Console;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


/**
 * Main CGREP class for concurrent lock
 * /
 */

/* We don't need an entire class implementation, just seemed like a lot
 * of stuff to put in one main() method.
 */
public class CGrep {

    private static ExecutorCompletionService<Found> completionService;
	
    final static int THREAD_POOL_SIZE = 3;
    final static int NUM_SEARCHES = 3;
    
    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        //Check if no args plz
        if (args.length <= 0) {
            System.out.println("Please argumentize.");
            System.exit(1);
        }

        ArrayList<String> files = new ArrayList<String>();
        Pattern pattern = Pattern.compile(args[0]);

        if (args.length == 1) {
            String input;
            Console console = System.console();
            input = console.readLine("Enter input:");
        } else {
            files.addAll(Arrays.asList(args).subList(1, args.length));
        }

        // Create our executor
        final ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        // Now wrap it in a nice cozy completion service
        completionService = new ExecutorCompletionService<Found>(executor);
        
        // We will want this so that the executor shuts down properly if the
        // application is closed while the threads are running.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                executor.shutdown();
                while (true) {
                    try {
                        // Wait for the service to exit
                        if (executor.awaitTermination(5, TimeUnit.SECONDS)) {
                            break;
                        }
                    } catch (InterruptedException ignored) {
                    }
                }
                System.out.println("Closing now");
            }
        }));

        // Create the Grep Executor
        GrepExecutor ge = new GrepExecutor();
        for (String filename : files) {
            System.out.println("Submitting " + filename + " for search");
            completionService.submit(new Grepper(filename, pattern));  // This will start running immediately
        }
        ge.beginSearch(completionService);

    }
}
