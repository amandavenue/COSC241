package week02;

import java.util.Random;
/** Creates a series of coin tosses.
    @author Amanda Veldman
    Week 02, Cosc241 2017
*/

public class Coins{
    /** Testing instances of coins.
        @param args (unused)
    */
    public static void main(String [] args){
        boolean[] b = {HEADS, TAILS, HEADS, HEADS, TAILS};
        Coins c = new Coins(b); // manually make a coin toss array
        System.out.println(c.countHeads());
        Coins str = new Coins("TTHHHTTH"); // test string constructor
        str.toString();
        Coins len = new Coins(0); // test length constructor
        len.toString();
    }

    /** HEADS is equivelant to true as a boolean value.
     */
    public static final boolean HEADS = true;
    /** TAILS is equivelant to false as a boolean value.
     */
    public static final boolean TAILS = false;
    /** an array of boolean values will be called coins.
     */
    private boolean[] coins;

    /** Constructs a coins object from an input.
        @param coins (array of coin tosses as booleans)
    */
    public Coins(boolean[] coins) {
        this.coins = coins;
    }
    
    /** Counts the total heads in coins.
        @param h
        @return number of heads
    */
    public int countHeads(){
        int h = 0;
        for (boolean c: coins){ // iterate through coins
            if (c){ // if true increment h
                h++;
            }
        }
        return h;
    }

    /** prints out the contents of coins as a string.
        @return coins contents as a string
    */
    public String toString(){
        String tosses = "";
        for (boolean c: coins){ // iterate thorough coins
            if (c){
                tosses += "H"; // if true add H to tosses
            } else {
                tosses += "T"; // else false add T to tosses
            }
        }
        return tosses;
    }

    /** constructs a coins object from a string.
        @param c (string coin toss sequence)
    */
    public Coins(String c){
        coins = new boolean[c.length()]; // initialise array to length of string
        for(int i = 0; i <c.length(); i++){
            char ch = c.charAt(i);
            if (ch == 'H'){ // convert char to boolean equivelant 
                coins[i] = true;
            } else {
                coins[i] = false;
            }
        }
    }

    /** constructs a coins object from an int.
        @param length (desired length of coins)
    */
    public Coins(int length){
        coins = new boolean[length];
        Random rand = new Random();
        for (int i = 0; i < coins.length; i++){
            coins[i] = rand.nextBoolean(); // set coins[i] to random boolean
        }
    }

    /** Count the number of T or H sequences in coins.
        @return runCount (total number of runs in coins)
    */
    public int countRuns(){
        int runCount = 1;
        for(int i = 1; i < this.coins.length; i++){
            if(coins[i] != coins[i-1]){ // check if char is the same as past one
                runCount++; // if run finishes increment total runs
            }
        }
        return runCount;
    }
}
