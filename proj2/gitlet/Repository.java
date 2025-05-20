package gitlet;

import java.io.File;
import java.util.TreeMap;
import java.util.TreeSet;

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
        Commit headCommit = Commit.getHeadCommit();
        TreeMap<String, String> stagedFilesForAddition = getStagedFilesForAddition();
        TreeSet<String> stagedFilesForRemoval = getStagedFilesForRemoval();
        String blobId = b.sha1();
        String headBlobId = headCommit.getFileBlobs().get(fileName);
        if (blobId.equals(headBlobId)) {
            stagedFilesForAddition.remove(fileName);
            stagedFilesForRemoval.remove(fileName);
            writeObject(join(GITLET_DIR, "index"), stagedFilesForAddition);
            writeObject(join(GITLET_DIR, "removal"), stagedFilesForRemoval);
        } else if (blobId.equals(stagedFilesForAddition.get(fileName))) {
            return;
        } else {
            b.save();
            stagedFilesForAddition.put(fileName, blobId);
            writeObject(join(GITLET_DIR, "index"), stagedFilesForAddition);
        }
    }

    public static void rm(String fileName) {
        TreeMap<String, String> stagedFilesForAddition = getStagedFilesForAddition();
        TreeSet<String> stagedFilesForRemoval = getStagedFilesForRemoval();
        Commit headCommit = Commit.getHeadCommit();
        boolean stagedForAddition = stagedFilesForAddition.containsKey(fileName);
        boolean trackedByHeadCommit = headCommit.getFileBlobs().containsKey(fileName);
        if (!stagedForAddition && !trackedByHeadCommit) {
            System.out.println("No reason to remove the file.");
            System.exit(0);
        } else if (stagedForAddition) {
            stagedFilesForAddition.remove(fileName);
        } else {
            restrictedDelete(fileName);
            stagedFilesForRemoval.add(fileName);
        }
    }

    private static TreeMap<String, String> getStagedFilesForAddition() {
        File index = join(GITLET_DIR, "index");
        if (!index.exists()) {
            return new TreeMap<>();
        }
        return readObject(index, TreeMap.class);
    }

    private static TreeSet<String> getStagedFilesForRemoval() {
        File removal = join(GITLET_DIR, "removal");
        if (!removal.exists()) {
            return new TreeSet<>();
        }
        return readObject(removal, TreeSet.class);
    }

    public static void commit(String message) {
        if (message.isEmpty()) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }
        TreeMap<String,String> stagedFilesForAddition = getStagedFilesForAddition();
        TreeSet<String> stagedFilesForRemoval = getStagedFilesForRemoval();
        if (stagedFilesForAddition.isEmpty() && stagedFilesForRemoval.isEmpty()) {
            System.out.println("No changes added to the commit.");
            return;
        }
        Commit headCommit = Commit.getHeadCommit();
        Commit commit = new Commit(message, headCommit.sha1(), headCommit);
        for (var entry: stagedFilesForAddition.entrySet()) {
            commit.addFile(entry.getKey(), entry.getValue());
        }
        for (var stagedFileForRemoval: stagedFilesForRemoval) {
            commit.removeFile(stagedFileForRemoval);
        }
        commit.save();
        Commit.setHeadCommit(commit);
        join(GITLET_DIR, "index").delete();
        join(GITLET_DIR, "removal").delete();
    }

    public static void log() {
        for (Commit c = Commit.getHeadCommit(); c != null; c = c.getParent()) {
            System.out.println("===");
            System.out.println(c);
        }
    }

    public static void globalLog() {
        for (var fileName: plainFilenamesIn(COMMITS_DIR)) {
            System.out.println("===");
            System.out.println(Commit.fromFile(fileName));
        }
    }
}
