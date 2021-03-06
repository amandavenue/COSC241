package week06;

import java.util.*;
import java.io.*;

/** Generates a random word based on letter frequencies.
    @author Amanda Veldman
    241 lab 06 
*/
public class FrequencyGenerator implements WordGenerator {

    /** sets the ranodm variable so its the same through out.
     */
    private Random random;

    /** initialises the array.
     */
    private double[] w = new double[getLines("letter-frequencies.txt")];

    /** for the 241-check they use this method.
        @param r is a seed fed into the method so random is consistant.
    */
    public FrequencyGenerator(Random r) {
        random = r;
    }

    /** scans in a file with letter frequencies and makes it an array.
        @return the array so that it can be used later.
    */
    public double[] scan(){
        try{
            Scanner sc = new Scanner(new File("letter-frequencies.txt"));
            while (sc.hasNextDouble()){
                for (int i = 0; i < w.length; i++){
                    w[i] = sc.nextDouble();
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return w;
    }

    /** counts lines in file.
        @param s is the file name.
        @return count so the length of array can be initialised.
    */
    public int getLines(String s){
        int count = 0;
        try{
            Scanner sc = new Scanner(new File(s));
            while (sc.hasNextLine()){
                count++;
                sc.nextLine();
            }
            return count;
        } catch (FileNotFoundException e){
            System.out.println("File Not Foud");
        }
        return count;
    }
        

    /** works out a random index number.
        @param w is the array produced by scan.
        @return int that is the index.
    */
    public int index(double[] w){
        int i = 0;
        double b = random.nextDouble();
        while (b > w[i]){
            b = b-w[i];
            i++;
        }
        return i;
    }

    /** builds a random word based on index and scan.
        @param n is the length of the word.
        @return the randomly generated word.
    */
    public String nextWord(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++){
            char c = (char) ('a' + index(scan()));
            result.append(c);
        }
        return result.toString();
    }

}
