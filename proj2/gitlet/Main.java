package gitlet;

import java.util.Date;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Umar Adelowo
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        CommandValidator cv = new CommandValidator(args);
        cv.validateNotEmpty();
        String firstArg = args[0];
        switch(firstArg) {
            case "init" -> {
                cv.validateInit();
                Repository.init();
            }
            case "add" -> {
                cv.validateAdd();
                String fileName = args[1];
                Repository.add(fileName);
            }
            case "commit" -> {
                cv.validateCommit();
                String message = args[1];
                Repository.commit(message);
            }
            case  "rm" -> {
                cv.validateRm();
                String fileName = args[1];
                Repository.rm(fileName);
            }
            case "log" -> {
                cv.validateLog();
                Repository.log();
            }
            case "global-log" -> {
                cv.validateGlobalLog();
                Repository.globalLog();
            }
            case "find" -> {
                cv.validateFind();
                String message = args[1];
                Repository.find(message);
            }
            default -> {
                System.out.println("No command with that name exists.");
            }
        }
    }

    private static void validateLength(String[] args) {
        if (args.length == 0) {
            System.exit(0);
        }
    }
}
