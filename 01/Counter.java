package week01;

import java.util.Scanner;
/** Counts lines and words in a file.
    @author AManda Veldman
    Week 01, Cosc241 2017 
    Takes a file as inut and counts and prints out line and word totals
*/

public class Counter{

    /** A file is taken as input and word and line totals are evaluated.
        @param args takes f1, wordTot, lineTot, and l1 
     */
    public static void main( String [] args){
        
        Scanner f1 = new Scanner(System.in);

        int wordTot = 0;
        int lineTot = 0;   // declare total count variables

        /* First while loop iterates through lines of input */
        while (f1.hasNextLine()){
            String line = f1.nextLine();
            lineTot++;

            Scanner l1 = new Scanner(line);

            /* Nested while loop iterates through words of each line */
            while (l1.hasNext()){
                wordTot++;
                l1.next();
            }
        }
        System.out.println("lines: " + lineTot);
        System.out.println("words: " + wordTot);
    }
}
