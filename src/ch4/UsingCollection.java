package ch4;

import java.util.*;

/**
 * Example for ch47.groovy
 */
public class UsingCollection {
    // Method in the Collection interface expects an Object, so Java boxes the 0 into an Integer.
    // And since an instance of Integer is not part of the list, it did not remove anything.
    public static void main(String[] args) {
        ArrayList<String> lst = new ArrayList<String>();
        Collection<String> col = lst;
        lst.add("one");
        lst.add("two");
        lst.add("three");
        lst.remove(0);
        col.remove(0);
        System.out.println("Added three items, remove two, so 1 item to remain.");
        System.out.println("Number of elements is: " + lst.size());
        System.out.println("Number of elements is: " + col.size());
    }
}