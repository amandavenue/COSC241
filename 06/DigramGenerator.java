package week06;

import java.util.*;
import java.io.*;

/** Generated gramatically plausable random words.
    @author Amanda Veldman
    241 lab 06
*/
public class DigramGenerator implements WordGenerator {
    
    /** sets the random variable so that it is same through out.
     */
    private Random random;

    /** initialises firstLetter variable.
     */
    private String firstLetter = "";

    /** initialises the array of strings nextLetters.
     */
    private String[] nextLetters = new String[getLines()];

    /** 241-check uses this method to generate a random from a seed.
        @param r is the seed for the random sequence.
    */
    public DigramGenerator(Random r) {
        random = r;
        popFirst();
        popNext();
    }

    /** populates firstLetter string.
        @return string made up of the first letters txt file
    */
    public String popFirst(){
        try{
            Scanner sc = new Scanner (new File("first-letters.txt"));
            firstLetter = sc.nextLine();
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return firstLetter;
    }

    /** populates the array nextLetters.
        @return array of strings that can follow each letter in the alphabet
    */
    public String[] popNext(){
        try{
            Scanner sc = new Scanner(new File("continuations.txt"));
            while(sc.hasNextLine()){
                for (int i = 0; i < nextLetters.length; i++){
                    nextLetters[i] = sc.nextLine();
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return nextLetters;
    }
        
    
    /** count the number of lines in the continuations file.
        @return count so the length of nextLetters[] can be set
    */
    public int getLines(){
        int count = 0;
        try{
            Scanner sc = new Scanner(new File("continuations.txt"));
            while (sc.hasNextLine()){
                count ++;
                sc.nextLine();
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        return count;
    }

    /** works out a random index for the first letter to be calculated from.
        @return char to be the first letter
    */
    public char firstLet (){
        
        int r = random.nextInt(firstLetter.length());
        return firstLetter.charAt(r);
    }

    /** works out a random char to follow car l.
        @param l is the first letter of the random word
        @return char to follow previous word
    */
    public char nextLet(char l){

        int index = (l-'a');
        String line = nextLetters[index];
        int m = random.nextInt(line.length());
        return line.charAt(m);
    }
        
    /** builds a random gramatically plausable word.
        @param length is the length of the desired word
        @return the randomly generated word
    */
    public String nextWord(int length) {
        StringBuilder result = new StringBuilder();
        char current = 'a';
        for (int i = 0; i < length; i++){
            if (i == 0){
                char f = firstLet();
                result.append(f);
                current = f;
            } else {
                char n = nextLet(current);
                result.append(n);
                current = n;
            }
        }
        return result.toString();
    }

}
