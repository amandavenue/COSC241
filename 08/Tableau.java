package week08;

import java.util.Scanner;
import java.util.function.Function;

/**
 *  An implementation of Young's tableau using linked cells.
 *
 * @author Iain Hewson
 */
public class Tableau {

    /** The smallest value (or root) of this Tableau. */
    private Cell smallest = null;

    /**
     *  Adds the given value to this tableau.
     *
     * @param value the value to be added to this tableau.
     */
    public void addValue(Integer value) {
        if (smallest == null) {
            smallest = new Cell(value);
            return;
        }
        // keep coding from here on
        Integer v = value;

        for (Cell r = smallest; ; r = r.below){

            v = addToRow(r, v);

            if (v== null){
                return;
            }
            if (r.below == null){
                //insert link above, below and left
                Cell next = new Cell(v);
                r.below = next;
                next.above = r;
                return;
            }
        }
        //return;
    }

    /**
     *  Adds the given value to the row beginning with
     *  <code>curr</code>, keeping the row in ascending order.  If the
     *  value gets added to the end of the row <code>null</code> is
     *  returned, otherwise the bumped value is returned.
     * 
     * @param curr the first cell in the current row.
     * @param value the value to be added to the row.
     * @return the bumped value, or null if the value was added to the
     *         end of the row.
     */
    protected Integer addToRow(Cell curr, int value){
        
        for (Cell c = curr; c != null; c = c.right){
            if (c.value > value){
                int t = c.value;
                c.value = value;
                return t;
            }
            if(c.right == null){
                Cell end = new Cell(value);
                //make links above and below
                c.right = end;
                end.left = c;
                if (c.above !=null){
                    end.above = c.above.right;
                    end.above.below = end;
                }
                if (c.below != null){
                    end.below = c.below.right;
                    if(end.below != null){
                        end.below.above = end;
                    }
                }
                return null;
            }
        }
        return null;
    }

    /**
     *  Interate through every cell in the tableau printing them using
     *  the given function.
     *
     * @param f a function which when applied to a cell should return
     *        an integer.
     */
    protected void print(Function<Cell,Integer> f) {
        for (Cell i = smallest; i != null; i = i.below) {
            for (Cell j = i; j != null; j = j.right) {
                System.out.printf("[%2d]", f.apply(j));
            }
            System.out.println();
        }
    }
    
    /**
     *  Entry point of the program.  Reads numbers from stdin and adds
     *  them to a Tableau.  If <code>p</code> is input then the
     *  tableau is printed.  If <code>c</code> is input then a count
     *  of the neighbours of each cell is printed.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        Tableau t = new Tableau();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                t.addValue(input.nextInt());
            } else {
                String command = input.next();
                if ("p".equals(command)) {
                    t.print(cell -> cell.value);
                } else if ("c".equals(command)) {
                    t.print(cell -> cell.neighbours());
                }                
            }
        }
    }

    /**
     *  A cell which holds a value and links to neighbouring cells.
     */
    protected static class Cell {
        /** The value held by this cell. */
        int value;
        /** The cell above this cell. */
        Cell above;
        /** The cell below this cell. */
        Cell below;
        /** The cell to the left of this cell. */
        Cell left;
        /** The cell to the right of this cell. */
        Cell right;

        /** Creates a new cell with the given value.
         * @param value the value contained in this cell.
         */
        Cell(int value) {
            this.value = value;
        }

        /** Returns how many horizontal and vertical (but not diagonal)
         *  neighbours this cell has.
         * @return how many neighbours this cell has.
         */
        int neighbours() {
            int count = left != null ? 1 : 0;
            count += right != null ? 1 : 0;
            count += above != null ? 1 : 0;
            count += below != null ? 1 : 0;
            return count;
        }
    } 
        
}
