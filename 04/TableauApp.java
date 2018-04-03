package week04;

/**
 * Skeleton code for an array based implementation of Young's tableau.
 *
 * @author Iain Hewson
 */
public class TableauApp {

    /**
     * The main method is just used for testing.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        final int[][] valid = {{1, 4, 5, 10, 11}, {2, 6, 8}, {3, 9, 12}, {7}};
        final int[][] invalid = {{1,4,5,10,11}, {2,6,8}, {3,9,12,8}, {7}};
        final int[][] n = {};
        System.out.println(TableauApp.toString(valid));
        System.out.println(rowLengthsDecrease(valid));
        System.out.println(rowLengthsDecrease(invalid));
        System.out.println(rowLengthsDecrease(n));
        System.out.println(rowValuesIncrease(valid));
        System.out.println(rowValuesIncrease(invalid));
        System.out.println(rowValuesIncrease(n));

        System.out.println("tt" + isSetOf1toN(valid));
    }

    /**
     * Determines whether the array passed to it is a valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){
        return (rowLengthsDecrease(t) && rowValuesIncrease(t)&&
                columnValuesIncrease(t) && isSetOf1toN(t));
    }

    /**
     *  Returns a string representation of an array based tableau.
     *
     * @param t a two-dimensional array which represents a tableau.
     *
     * @return a string representation of an array based tableau.
     */
    public static String toString(int[][] t) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                result.append(String.format("%-4s", t[i][j]));
            }
            if (i < t.length-1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /** checks all rows are not longer than preceeding rows.
        @param t a two-dimetnional array which represents a tableau.
        @return true if row is no longer than preceeding row, else false.
    */
    public static boolean rowLengthsDecrease(int [][] t){
        if (t.length == 0){
            return false;
        }
        for (int i = 1; i <t.length; i++){
            if (!(t[i].length <= t[i-1].length)){
                return false;
            }
        }
        return true;
    }

    /** checks each integer is smaller than preceeding integer in each row.
        @param t a two-dimentional array which represents a tableau.
        @return true if ints in each row are smaller than precceding int,else f.
    */
    public static boolean rowValuesIncrease(int [][] t){
        if (t.length == 0){
            return false;
        }
        for (int r = 0; r < t.length; r++){
            for (int c = 1; c < t[r].length; c++){
                if (!(t[r][c] > t[r][c-1])){
                    return false;
                }
            }
        }
        return true;
    }

    /** check if ints in columns increase from top to bottom.
        @param t a two-dimentional array whcih represents a tableau.
        @return true if ints increase in columns from top to bottom, else f.
    */
    public static boolean columnValuesIncrease(int[][] t){
        for (int r = 1; r < t.length; r++){
            for (int c = 0; c < t[r].length; c++){
                if (!(t[r][c] > t[r-1][c])){
                    return false;
                }
            }
        }
        return true;
    }

    /** check max integer used is equal to the number of cells.
        @param t a two-dimentional array which represents a tableau.
        @return truw if max int equals total cells, else false.
    */
    public static boolean isSetOf1toN(int [][] t){
        int cells = 0;
        boolean[] set;
        
        for (int r = 0; r < t.length; r++){
            cells += t[r].length;
        }
        
        set = new boolean[cells];
        
        for (int r = 0; r < t.length; r++){
            for (int c = 0; c < t[r].length; c++){
                if (t[r][c] > cells){
                    return false;
                } else {
                    set[(t[r][c])-1] = true;
                }
            }
        }
        
        for (int i = 0; i < set.length; i++){
            if (!set[i]){
                return false;
            }
        }
        return true;
    }
}
