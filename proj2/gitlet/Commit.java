package gitlet;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
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
        this.fileBlobs = (TreeMap<String, String>) other.fileBlobs.clone();
    }

    public static Commit getHeadCommit() {
        File head = join(Repository.GITLET_DIR, "HEAD");
        File branch = join(Repository.BRANCHES_DIR, readContentsAsString(head).strip());
        return readObject(
                join(Repository.COMMITS_DIR,readContentsAsString(branch).strip()),
                Commit.class
        );
    }

    public static void setHeadCommit(Commit commit) {
        File head = join(Repository.GITLET_DIR, "HEAD");
        File branch = join(Repository.BRANCHES_DIR, readContentsAsString(head).strip());
        String id = commit.sha1();
        writeContents(branch, id + "\n");
    }

    public void addFile(String file, String blob) {
        fileBlobs.put(file, blob);
    }

    public void removeFile(String file) {
        fileBlobs.remove(file);
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

    public Commit getParent() {
        if (parent == null) {
            return null;
        }
        File commitFile = join(Repository.COMMITS_DIR, parent);
        Commit c = readObject(commitFile, Commit.class);
        return c;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("commit ").append(sha1()).append("\n");

        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        sb.append("Date: ").append(formatter.format(this.date)).append("\n");

        sb.append(message).append("\n");

        return sb.toString();
    }
}
