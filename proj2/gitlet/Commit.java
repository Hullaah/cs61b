package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;
import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *
 *  @author Umar Adelowo
 */

public class Commit implements Serializable {

    /** The message of this Commit. */
    private String message;
    /** The date this commit was made. */
    private Date date;
    /** The parent of this commit. */
    private String parent;
    /** Mapping of filename to reference to blobs */
    private TreeMap<String, String> fileBlobs = new TreeMap<>();

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

    public void addFile(String file, String blob) {
        fileBlobs.put(file, blob);
    }

    public String save() {
        String id = sha1();
        File commitFile = join(Repository.COMMITS_DIR, id);
        writeObject(commitFile, this);
        return id;
    }

    public String sha1() {
        File tmp = join(Repository.COMMITS_DIR, "tmp");
        writeObject(tmp, this);
        String id = Utils.sha1(readContents(tmp));
        tmp.delete();
        return id;
    }

    public TreeMap<String, String> getFileBlobs() {
        return fileBlobs;
    }
}
