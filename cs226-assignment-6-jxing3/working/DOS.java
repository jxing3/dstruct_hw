
import java.util.Scanner;

import java.util.ArrayList;

import java.util.Collections;


public final class DOS {


    private static Tree<Entry> t = new TreeImplementation<Entry>();

    private static Scanner kb = new Scanner(System.in);
    
    private static Position<Entry> current;

    private DOS() {}


    private static String pwd() {

        String s = "";
        Position<Entry> now = current;

        if(t.isRoot(now)) {
            s = now.get().name() + s;
        }
        while(!t.isRoot(now)) {
           s = "/" + now.get().name() + s;
           now = t.parent(now);
        }
        
        return s;

    }


    private static boolean parseOps(String in) {

        if (in.equals("quit")) {

            return true;

        } else if (in.equals("cd")) {

            boolean there = false;
            Position<Entry> it = null;
            Iterable<Position<Entry>> iter;

            String s = kb.next();
            if(s.equals("..")) {
                if(t.hasParent(current)) {
                    current = t.parent(current); 
                }
            } else {

                if(t.hasChildren(current)) {
                    iter = t.children(current);
                } else {
                    System.err.println("? Directory with name '" + s + "' does not exist");
                    return true;
                }

                for(Position<Entry> e: iter) {

                    if(e.get().name().equals(s)) {
                        there = true;
                        it = e;
                    }
                }

                if(!there) {
                    System.err.println("? Directory with name '" + s + "' does not exist");
                } else {
                    if(it.get() instanceof Directory) {
                        current = it;

                    } else {
                        System.err.println("? Directory with name '" + s + "' does not exist");
                    }

                }
            }
            return true;

        } else if (in.equals("ls")) {

            Position<Entry> it = null;
            Iterable<Position<Entry>> iter;
            ArrayList<String> arrstr= new ArrayList<String>();

            try{

                iter = t.children(current);

            } catch (InvalidPositionException e) {

                return true;

            }
            for(Position<Entry> e: iter) {

                if(e.get() instanceof Directory) {
                    arrstr.add(e.get().name()+"/");
                } else {
                    arrstr.add(e.get().name());
                }
                   
            }
            Collections.sort(arrstr);
            for(String e: arrstr) {
                System.out.println(e);
            }

            return true;

        } else if (in.equals("mkdir")) {

            String s = kb.next();
            boolean there = false;
            Iterable<Position<Entry>> iter;

            try{
                iter = t.children(current);
            } catch (InvalidPositionException e) {
                t.insertChild(current, new Directory(s));
                return true;
            }
            for (Position<Entry> e: iter) {

                if(e.get().name().equals(s)) {
                    there = true;
                }
            }
            if (!there) {
                t.insertChild(current, new Directory(s));
            } else {
                System.err.println("? Entry with name '" + s + "' already exists");

            }

            
            return true;

        } else if (in.equals("rmdir")) {

            String s = kb.next();
            boolean there = false;
            Position<Entry> it = null;
            Iterable<Position<Entry>> iter;

            try{
                iter = t.children(current);
            } catch (InvalidPositionException e) {

                System.err.println("? Directory does not exist and cannot be removed");
                return true;

            }
            for(Position<Entry> e: iter) {

                if(e.get().name().equals(s)) {
                    there = true;
                    it = e;
                }
            }
            if(!there) {
                System.err.println("? Directory does not exist and cannot be removed");
            } else {

                if(it.get() instanceof Directory) {
                    try {
                        t.removeAt(it);
                    } catch (RemovalException E) {
                        System.err.println("Directory is not empty");
                    }
                } else {

                    System.err.println("? error " + s + " is a file, use rm instead");

                }
            }

            return true;

        } else if (in.equals("mk")) {

            String s = kb.next();
            boolean there = false;
            Iterable<Position<Entry>> iter;

            try{
                iter = t.children(current);
            } catch (InvalidPositionException e) {
                t.insertChild(current, new File(s));
                return true;
            }
            for (Position<Entry> e: iter) {

                if(e.get().name().equals(s)) {
                    there = true;
                }
            }
            if (!there) {
                t.insertChild(current, new File(s));
            } else {
                System.err.println("? Entry with name '" + s + "' already exists");

            }

            return true;

        } else if (in.equals("rm")) {

            String s = kb.next();

            boolean there = false;
            Position<Entry> it = null;
            Iterable<Position<Entry>> iter;

            try{
                iter = t.children(current);
            } catch (InvalidPositionException e) {

                System.err.println("? File does not exist and cannot be removed");
                return true;

            }
            for(Position<Entry> e: iter) {

                if(e.get().name().equals(s)) {
                    there = true;
                    it = e;
                }
            }
            if(!there) {
                System.err.println("? File does not exist and cannot be removed");
            } else {

                if(it.get() instanceof File) {
                    try {
                        t.removeAt(it);
                    } catch (RemovalException E) {
                        System.err.println("File is not empty");
                    }
                } else {

                    System.err.println("? error " +s+ " is a Directory, use rmdir instead");

                }
            }

            return true;

        } else if (in.equals("pwd")) {

            String s = pwd();
            System.out.println(s);
            
            return true;
        }


        return false;


    }


    /** Main program takes input from user and parses it to perform
    operations outlined in class javadocs.
    @param args Command line arguments
    */

    public static void main(String[] args) {


        String input;

        current = t.insertRoot(new Directory("/"));

        do {
            System.out.print("> ");

            input = kb.next();

            if (!parseOps(input)) {
                System.err.println("? The following input: '" + input
                                   + "' is invalid");
            }

        }
        while (!(input.equals("quit")));

    }

}
