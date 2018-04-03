package week10;

import java.util.*;

/**
 * Skeleton of the recursive implementation of a general tree.
 * 
 * @author Michael Albert
 * @param <T> The type of values stored in the tree.
 */
public class Tree<T> {

    /** variable of type T that represents the roots value. */
    private T rootValue;
    /** create a list of Trees of type T called children. */
    private List<Tree<T>> children;

    /** constructs a Tree from the root anf children parameters.
        @param rootValue is the value of the rood node
        @param children is a list of children
    */
    public Tree(T rootValue, List<Tree<T>> children) {
        this.rootValue = rootValue;
        this.children = children;
    }
    /** Constructs a tree form olny a root value.
        @param rootValue is the value of the root node
    */
    public Tree(T rootValue) {
        this(rootValue, new ArrayList<Tree<T>>());
    }

    /** counts the size of the tree.
        @return int to represent the size of the tree
    */
    public int size() {
        if (rootValue == null){
            return 0;
        }
        int s = 1;
        for(Tree<T> child : children){
            s += child.size();
        }
        return s;
    }

    /** work out the maximun number of children a parent has.
        @return int the max children a parent has
    */
    public int maxDegree() {
        int max = children.size();
        if (rootValue == null){
            return 0;
        }
        if (children.isEmpty()){
            return 0;
        }
        for(Tree<T> child : children){
            if (child.maxDegree() > max){
                max = child.maxDegree();
            }
        }
        return max;
    }

    /** add a child to a tree.
        @param child is the node to be added
    */
    public void add(Tree<T> child) {
        this.children.add(child);
    }

    /** finds a node within the tree.
        @param value is the node to be found
        @return the Tree that contains that node
    */
    public Tree<T> find(T value) {
        if (rootValue.equals(value)) {
            return this;
        }
        for (Tree<T> child : children) {
            Tree<T> match = child.find(value);
            if (match != null) {
                return match;
            }
        }
        return null;
    }

    /** traverse and print out the contents of the tree in post order.
        @return a list of the trees contents
    */
    public List<T> postOrder() {
        ArrayList<T> result = new ArrayList<T>();
        if (rootValue == null){
            return result;
        }
        for (Tree<T> child : children){
            result.addAll(child.postOrder());
        }
        result.add(rootValue);
            
        return result;
    }

    /** print the contents of the tree as a String.
        @return String that is a representation of the trees contents
    */
    public String toString() {
        if (children.isEmpty()) {
            return rootValue.toString();
        }
        return rootValue.toString() + ' ' + children.toString();
    }

    /** indents the string that represents the tree.
        @return String representation of the tree indented
    */
    public String toIndentedString() {
        return toIndentedString("");
    }

    /** works out how much indentatation is needed.
        @param indent number of indents
        @return String to represent the indented tree
    */
    private String toIndentedString(String indent){
        String out = indent + rootValue + '\n';
        for (Tree<T> child : children) {
            out += child.toIndentedString(indent + "  ");
        }
        return out;
    }

    /** A helper method for testing (used by main).  Searches tree for
     *  the given target and adds white space separated children to
     *  the tree matching target if there is one.
     *
     * @param target the root value to seach for.
     * @param children a white space separated list of children to add
     * to the tree whose value matches target.
     */
    private static void addChildren(String target, String children) {
        Tree<String> parent = tree.find(target);
        if (parent != null) {
            for (String child : children.split(" ")) {
                parent.add(new Tree<>(child));
            }
        }
    }

    /** A tree instance used for testing. */
    private static Tree<String> tree;

    /**
     * Entry point of the program (used for testing).
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        System.out.println("Creating tree\n-------------");
        tree = new Tree<>("food");
        System.out.print(tree + "\nsize: " + tree.size());
        System.out.println(", max degree: " + tree.maxDegree());
        System.out.println("\nAdding children\n----------------");
        addChildren("food", "meat fruit vegetable");
        System.out.print(tree + "\nsize: " + tree.size());
        System.out.println(", max degree: " + tree.maxDegree());
        System.out.println("\nAdding deeper children\n----------------------");
        addChildren("meat", "chicken beef fish");
        addChildren("fish", "salmon cod tuna shark");
        addChildren("vegetable", "cabbage");
        System.out.print(tree + "\nsize: " + tree.size());
        System.out.println(", max degree: " + tree.maxDegree());
        System.out.println("\nPostorder\n---------");
        System.out.println(tree.postOrder());
        System.out.println("\nIndented string\n---------------");
        System.out.print(tree.toIndentedString());
    }

}
