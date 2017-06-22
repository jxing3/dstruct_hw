/* Jesse Xing
   jxing3@jhu.edu
*/

import java.util.Scanner;

/**Unique takes arguments from standard input and outputs each "unique" integer once
*/

public class Unique {
   private static Array<Integer> num;
   private static int numindex=0;

  /*Main program takes arguments from standard input and outputs
  only the integers that are unique.*/

  public static void main(String[] args) throws IndexException, LengthException{

    num = new SimpleArray<Integer>(10, 0);


    Scanner kb= new Scanner(System.in);
   //loop takes arguments and places "unique" ones in simple array num

    while(kb.hasNext()){
      exparr();
      try{
        int i = kb.nextInt();
        insert(i);
      }catch(java.util.InputMismatchException e){
       System.err.println("Non integer argument '"+kb.next()+"' has been detected and ignored");
      }
    }

  // output unique numbers in array order
        for (int i = 0; i < numindex; i++) {
            System.out.println(num.get(i));
        }

  }

  /**Method expands numarray once the index hits the full length
  of the array- It makes it twice as large*/

  private static void exparr() throws IndexException,LengthException {
    if(numindex ==num.length()-1){
        Array<Integer> temp = new SimpleArray<Integer>(num.length()*2, 0);
       for (int c=0; c <= numindex; c++){
          temp.set(c, num.get(c));
       }
       num= temp;
    }
  }

  /** Returns position of given value in simple array num, -1 if not found
      @param value the value we're looking for in simple array num
      @return position of the value in simple array num, 
       -1 if the value was not found in array
  */
    private static int find(int value) throws IndexException {
        for (int i = 0; i < numindex; i++) {
            if (num.get(i) == value) {
                return i;
            }
        }
        return -1;
    }
  

    /** Inserts value into simple array num if not already present
        @param value the value to be inserted
    */
    private static void insert(int value) throws LengthException,IndexException{
        int position = find(value);
        if (position < 0) {
            exparr();
            num.set(numindex, value);
            numindex++;
        }
    }

}
