package week03;

/** demonstarte recursive methods.
    @author Amanda Veldman
    Week 03, Cosc241 2017
*/

public class RecursiveApp{
    /** WHAT DOES MAIN METHOD DO?
        @param args (unused)
    */
    public static void main(String [] args){
        System.out.println(sumOfDigits(257));
        System.out.println(sumOfDigits(-257));
    }

    /** returns long equal to the number of digits in it arg.
        @param n (a digit to which we will calculate the length)
        @return long (number of digits in argument)
    */
    public static long digits(long n){
        if (Math.abs(n) < 10){
            return 1l;
        }
        return 1l + digits(n/10);
    }

    /**adds all digits of arg.
       @param n (digit of which sum of digits is to be calculated from)
       @return sum (sum of all digits in arg)
    */
    public static long sumOfDigits(long n){
        if ((n >=0) && (n<10)){
            return n;
        }
        return (n%10 + sumOfDigits(n/10));                 
    }
}
            
