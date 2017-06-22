/* Jesse Xing
   jxing3@jhu.edu
*/

public class Unique {
   public static int num[] = new int[10];
   public static int numindex;
   public static int uni[];
   public static int uniindex;

  /*Main program takes arguments from command line and outputs
  only the integers that are unique.*/

  public static void main(String[] args){

    boolean flag=true;

   //loop takes arguments and places legitimate ones in array num[] while incrementing numindex

    for(int i=0; i<args.length;i++){
      exparr();
      try{
       num[numindex] = Integer.parseInt(args[i]);
       numindex++;
      } catch (NumberFormatException e) {
        System.err.println("Non integer argument '"+args[i]+"' has been detected and ignored");
      }

    }

    uni = new int[num.length];

  //loop only adds the elements that are unique from num[] array into uni[] array
    for(int i=0; i<numindex; i++){
      for(int j=0; j<uniindex;j++){
        if(num[i]==uni[j]){
           flag = false;
        }
      }
      if(flag == true){
        uni[uniindex]= num[i];
        uniindex++;
      }
      flag = true;
    }

   //loop prints out all elements from uni[] array
    for (int i=0; i<uniindex; i++){
      System.out.println(uni[i]);
    }

  }

  /*Method expands array once the index hits the full length
  of the array- It makes it twice as large*/

  public static void exparr(){
    if(numindex ==num.length-1){
       int[] temp = new int[num.length*2];
       for (int c=0; c <= numindex; c++){
          temp[c] = num[c];
       }
       num= temp;
    }
  }

}
