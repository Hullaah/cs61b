package gitlet;

// TODO: any imports you need here

import java.util.Calendar;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.HashMap;
import java.util.Map;

/** Represents a gitlet commit object.
 * .
 *  @author Umar Adelowo
 */
public class Commit {

    /** The message of this Commit. */
    private String message;
    /** The date this commit was made. */
    private Date date;
    /** The parent of this commit. */
    private String parent;
    /** Mapping of filename to reference to blobs */
    private Map<String, String> fileBlobs;

    public Commit(String message, String parent) {
        this.message = message;
        this.parent = parent;
        if (parent == null) {
            date = new Date(0);
        } else {
            date = new Date();
        }
    }

    public Commit(String message, String parent, Commit other) {
        this(message, parent);
        this.fileBlobs = other.fileBlobs;
    }
}
