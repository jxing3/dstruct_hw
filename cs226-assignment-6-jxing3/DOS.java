/* Jesse Xing
   jxing3@jhu.edu
*/


import java.util.Scanner;

import java.util.ArrayList;

import java.util.Collections;
/**

This program is a simulation of a simple file system.

Operations:
ls: print the names of all entries
cd name: make "name" (which must be a directory) the current directory
         or if ".." return to previous directory
mkdir name: create a directory called "name" in the current directory
rmdir name: remove a directory called "name" from the current directory
mk name: create a file called "name" in the current directory
rm name: remove a file called "name" from the current directory
pwd: print the absolute path to the current working directory
quit: exit the program, stop the simulation
*/

public final class DOS {


    private static Tree<Entry> t = new TreeImplementation<Entry>();

    private static Scanner kb = new Scanner(System.in);

    private static Position<Entry> current;

    private static class Entry {

        String name;

        public Entry(String s) {
            this.name = s;
        }

        String name() {
            return this.name;
        }

    }
    private static class File extends Entry {
        public File(String s) {
            super(s);
        }
    }

    private static class Directory extends Entry {

        public Directory(String s) {
            super(s);
        }
    }

    private DOS() {}

    //finds and return a certain entry of a name
    private static Position<Entry> find(String s) {
        Iterable<Position<Entry>> iter;
        Position<Entry> it = null;
        try {
            iter = t.children(current);
        } catch (InvalidPositionException e) {
            return null;
        }
        for (Position<Entry> e: iter) {

            if (e.get().name().equals(s)) {
                it = e;
            }
        }
        return it;
    }

    private static void cd() {

        String s = kb.next();
        if (s.equals("..")) {
            if (t.hasParent(current)) {
                current = t.parent(current);
            }
        } else {
            Position<Entry> it = find(s);
            if (it == null) {
                System.err.println("? error: directory with name '" + s
                                  + "' does not exist");
            } else {
                if (it.get() instanceof Directory) {
                    current = it;
                } else {
                    System.err.println("? error: '" + s
                                      + "' is a file not a directory");
                }
            }
        }
    }

    private static void ls() {
        Position<Entry> it = null;
        Iterable<Position<Entry>> iter;
        ArrayList<String> arrstr = new ArrayList<String>();

        try {
            iter = t.children(current);
        } catch (InvalidPositionException e) {
            return;
        }
        for (Position<Entry> e: iter) {

            if (e.get() instanceof Directory) {
                arrstr.add(e.get().name() + "/");
            } else {
                arrstr.add(e.get().name());
            }
        }
        Collections.sort(arrstr);
        for (String e: arrstr) {
            System.out.println(e);
        }

    }

    private static void mkdir() {

        String s = kb.next();

        if (find(s) == null) {
            t.insertChild(current, new Directory(s));
        } else {
            System.err.println("? error: entry with name '" + s
                               + "' already exists");

        }


    }
    private static void rmdir() {

        String s = kb.next();
        Position<Entry> it = find(s);
        if (it == null) {
            System.err.println("? error: directory does not exist and "
                              + "cannot be removed");
        } else {

            if (it.get() instanceof Directory) {
                try {
                    t.removeAt(it);
                } catch (RemovalException e) {
                    System.err.println("? error: directory is not empty");
                }
            } else {

                System.err.println("? error: " + s + " is a file,"
                                  + " use rm instead");

            }
        }

    }
    private static void mk() {
        String s = kb.next();
        if (find(s) == null) {
            t.insertChild(current, new File(s));
        } else {
            System.err.println("? error: entry with name '" + s
                               + "' already exists");

        }

    }

    private static void rm() {
        String s = kb.next();
        Position<Entry> it = find(s);
        if (it == null) {
            System.err.println("? error: file does not exist"
                              + "and cannot be removed");
        } else {

            if (it.get() instanceof File) {
                try {
                    t.removeAt(it);
                } catch (RemovalException e) {
                    System.err.println("? error: file is not empty");
                }
            } else {

                System.err.println("? error: " + s + " is a directory,"
                                  + " use rmdir instead");

            }
        }
    }

    private static String pwd() {

        String s = "";
        Position<Entry> now = current;

        if (t.isRoot(now)) {
            s = now.get().name() + s;
        }
        while (!t.isRoot(now)) {
            s = "/" + now.get().name() + s;
            now = t.parent(now);
        }

        return s;

    }



    /** Main program takes input from user and parses it to perform
    operations outlined in class javadocs.
    @param args Command line arguments
    */

    public static void main(String[] args) {


        String in;

        current = t.insertRoot(new Directory("/"));

        do {
            System.out.print("> ");

            in = kb.next();

            if (in.equals("cd")) {
                cd();

            } else if (in.equals("ls")) {

                ls();

            } else if (in.equals("mkdir")) {

                mkdir();

            } else if (in.equals("rmdir")) {

                rmdir();

            } else if (in.equals("mk")) {

                mk();

            } else if (in.equals("rm")) {

                rm();

            } else if (in.equals("pwd")) {

                String s = pwd();
                System.out.println(s);

            } else if (!in.equals("quit")) {
                System.err.println("? error: the following input: '" + in
                                   + "' is invalid");
            }

        }
        while (!(in.equals("quit")));

    }

}
