package week06;

import java.util.*;

/**
 *  A very basic random word generator.
 *
 * @author Michael Albert, Iain Hewson
 */
public class BasicGenerator implements WordGenerator {

    /** Random source used to generate words. */
    private Random random;

    /**
     *  Constructs a basic random word generator which uses the given
     *  random source.
     *
     * @param r a source of randomness used when generating words.
     */
    public BasicGenerator(Random r) {
        random = r;
    }

    /**
     *  Return a random word of length n.
     *
     * @param n the required length of the word.
     *
     * @return a random word of length n.
     */
    public String nextWord(int n) {
        final int alphabetLength = 26;
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < n; i++) {
            char c = (char) ('a' + random.nextInt(alphabetLength));
            result.append(c);
        }
        return result.toString();
    }

}
