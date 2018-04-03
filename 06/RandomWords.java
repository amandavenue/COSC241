package week06;

import java.util.*;

/**
 *  An application to test various word generators.
 *
 * @author Iain Hewson
 */
public class RandomWords {

    /** The available types of word generator. */
    private enum Generator { BASIC, FREQUENCY, DIGRAM };

    /** The type of generator used to generate words. */
    private static Generator generator = Generator.BASIC;

    /** A source of randomness to use when generating words. */
    private static Random random = new Random(241);

    /** The length of words to generate. */
    private static int length = 10;

    /** The number of words to generate. */
    private static int count = 5;

    /** Whether to print generation method name to stderr. */
    private static boolean verbose = false;

    /**
     *  Entry point of the program.  Handles command line arguments
     *  then creates the required word generator and prints
     *  <code>count</code> randomly generated words to stdout.
     *  Prints the generator name to stderr When verbose is true.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        handleCommandLine(args);
        WordGenerator wg;
        if (generator == Generator.FREQUENCY) {
            wg = new FrequencyGenerator(random);
        } else if (generator == Generator.DIGRAM) {
            wg = new DigramGenerator(random);
        } else {
            wg = new BasicGenerator(random);
        }
        if (verbose) {
            System.err.println(generator);
        }
        for (int i = 0; i < count; i++) {
            System.out.println(wg.nextWord(length));
        }        
    }

    /**
     *  Returns the next integer read from <code>args</code> if there
     *  is one, otherwise prints an error message and exits.
     *
     * @param args should contain a integer as the next token.
     * @return the next integer from args.
     */
    public static int getIntArg(Scanner args) {
        if (args.hasNextInt()) {
            return args.nextInt();
        }
        System.err.println("Error: integer argument required");
        System.exit(1);
        return -1;  // return needed to keep the compiler happy
    }

    /**
     *  Handle the command line arguments.  Various options are set as
     *  defined in the usage string:
     * <pre>
     * Usage: java week07.RandomWords [options] [N]
     * Print N randomly generated words (default 5)
     * -b      Basic generator
     * -f      Frequency generator
     * -d      Digram generator
     * -l N    Generate words of length N (default 10)
     * -s N    Seed random sequence with N (default 241)
     * -v      Print type of generator used to stderr
     * -h      Print this help message
     * </pre>
     *
     * @param args the command line arguments.
     */
    public static void handleCommandLine(String[] args) {
        Scanner commandLine = new Scanner(String.join(" ", args));
        while (commandLine.hasNext()) {
            if (commandLine.hasNextInt()) {
                count = commandLine.nextInt();
                continue;
            }
            String arg = commandLine.next();
            switch (arg) {
                case "-b":
                    generator = Generator.BASIC;
                    break;
                case "-d":
                    generator = Generator.DIGRAM;
                    break;
                case "-f":
                    generator = Generator.FREQUENCY;
                    break;
                case "-l":
                    length = getIntArg(commandLine);
                    break;
                case "-s":
                    random = new Random(getIntArg(commandLine));
                    break;
                case "-v":
                    verbose = true;
                    break;
                case "-h": default:
                    String usage =
                        "Usage: java week07.RandomWords [options] [N]\n\n" +
                        "Print N randomly generated words (default 5)\n\n" +
                        "-b      Basic generator\n" +
                        "-f      Frequency generator\n" +
                        "-d      Digram generator\n" +
                        "-l N    Generate words of length N (default 10)\n" +
                        "-s N    Seed random sequence with N (default 241)\n" +
                        "-v      Print type of generator used to stderr\n" +
                        "-h      Print this help message\n";
                    System.err.println(usage);
                    System.exit(0);
            }
        }
    }
}
