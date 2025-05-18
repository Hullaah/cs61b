package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;
import static gitlet.Utils.readContents;

/**
 * Represents a gitlet blob object
 *
 * @author Umar Adelowo
 */
public class Blob implements Serializable {
    private String contents;
    private String fileName;

    public Blob(String fileName) {
        contents = readContentsAsString(new File(fileName));
        this.fileName = fileName;
    }

    public String sha1() {
        File tmp = join(Repository.BLOBS_DIR, "tmp");
        writeObject(tmp, this);
        String id = Utils.sha1(readContents(tmp));
        tmp.delete();
        return id;
    }

    public String save() {
        String id = sha1();
        File blobFile = join(Repository.BLOBS_DIR, id);
        writeObject(blobFile, this);
        return id;
    }
}
