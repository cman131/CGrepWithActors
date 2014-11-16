package Actors;

import java.util.ArrayList;

/**
 * Created by kocsen on 11/14/14.
 * Message that encapsulates results from a scan actor.
 */
public class FoundMessage {

    public final String filename;
    public final ArrayList<String> matchingLines;

    /**
     * constructor
     *
     * @param filename      - filename
     * @param matchingLines - Array of matching lines.
     */
    public FoundMessage(String filename, ArrayList<String> matchingLines) {
        this.filename = filename;
        this.matchingLines = matchingLines;
    }

    public ArrayList<String> getMatchingLines() {
        return this.matchingLines;
    }

    /**
     * Prints a found message to spec.
     *
     * @return prety print
     */
    @Override
    public String toString() {
        // A nice and fancy print out of the results for another class to print
        StringBuilder retString = new StringBuilder();
        if (filename == null) {
            retString.append("\nFile: Standard In\n");

        } else {
            retString.append("\nFile: " + filename + "\n");

        }
        retString.append("----------------\n");
        retString.append("Matching Lines:\n");

        // Totally fancy list of matching lines
        for (String str : matchingLines) {
            retString.append(str + "\n");
        }
        return retString.toString();
    }
}
