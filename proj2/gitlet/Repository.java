package gitlet;

import java.io.File;
import java.util.TreeMap;

import static gitlet.Utils.*;

/** Represents a gitlet repository.
 *
 *  @author Umar Adelowo
 */
public class Repository {
    /**
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** The commits directory. */
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");
    /** The branches directory */
    public static final File BRANCHES_DIR = join(GITLET_DIR, "branches");
    /** The blobs directory */
    public static final File BLOBS_DIR = join(GITLET_DIR, "blobs");

    public static void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }
        COMMITS_DIR.mkdirs();
        BRANCHES_DIR.mkdir();
        BLOBS_DIR.mkdir();
        Commit c = new Commit("Initial commit", null);
        String id = c.save();
        File masterBranch = join(BRANCHES_DIR, "master");
        writeContents(masterBranch, id + "\n");
        File head = join(GITLET_DIR, "HEAD");
        writeContents(head, "master\n");
    }

    public static void add(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) {
            System.out.println("File does not exist.");
            System.exit(0);
        }
        Blob b = new Blob(fileName);
        Commit headCommit = getHeadCommit();
        TreeMap<String, String> stagedFiles = getStagedFiles();
        if (b.sha1().equals(headCommit.getFileBlobs().get(fileName))) {
            System.exit(0);
        } else if (b.sha1().equals(stagedFiles.get(fileName))) {
            System.exit(0);
        } else {
            String blobId = b.save();
            stagedFiles.put(fileName, blobId);
            writeObject(join(GITLET_DIR, "index"), stagedFiles);
        }
    }

    private static Commit getHeadCommit() {
        File head = join(GITLET_DIR, "HEAD");
        File branch = join(BRANCHES_DIR, readContentsAsString(head).strip());
        return readObject(join(COMMITS_DIR, readContentsAsString(branch).strip()), Commit.class);
    }

    private static TreeMap<String, String> getStagedFiles() {
        File index = join(GITLET_DIR, "index");
        if (!index.exists()) {
            return new TreeMap<>();
        }
        return readObject(index, TreeMap.class);
    }
}
