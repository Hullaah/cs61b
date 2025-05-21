package gitlet;

import java.io.File;

public class CommandValidator {
    private final String[] args;

    public CommandValidator(String[] args) {
        this.args = args;
    }

    private void checkWhetherInInitializedGitletDirectory() {
        if (!Repository.GITLET_DIR.exists()) {
            System.out.println("Not in an initialized Gitlet directory.");
            System.exit(0);
        }
    }

    public void validateNotEmpty() {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }
    }

    private void validateLength(int expectedLength) {
        if (args.length != expectedLength) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
    }

    public void validateInit() {
        validateLength(1);
    }

    public void validateAdd() {
        validateLength(2);
        checkWhetherInInitializedGitletDirectory();
    }

    public void validateCommit() {
        validateLength(2);
        checkWhetherInInitializedGitletDirectory();
    }

    public void validateLog() {
        validateLength(1);
        checkWhetherInInitializedGitletDirectory();
    }

    public void validateGlobalLog() {
        validateLength(1);
        checkWhetherInInitializedGitletDirectory();
    }

    public void validateRm() {
        validateLength(2);
        checkWhetherInInitializedGitletDirectory();
    }

    public void validateFind() {
        validateLength(2);
        checkWhetherInInitializedGitletDirectory();
    }
}
