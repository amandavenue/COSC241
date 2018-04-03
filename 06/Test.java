package week06;

import java.util.*;
import java.io.*;
import week06.DigramGenerator;

public class Test{

    public static void main (String [] args){
        Random r = new Random();
        // int num = r.nextInt();
        DigramGenerator dg = new DigramGenerator(r);
        System.out.println(dg.popNext().length);
    }
}
