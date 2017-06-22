public class Select{

  static void printArray(int a[]){
    for(int i=0;i<a.length;i++){
      System.out.print(a[i]+" ");
    }
    System.out.println();
  }
  //return position of minimum in a[from]..a[to]
  static int minimumInRange(int a[], int from, int to){ //"O(to-from+1)" O(m)
    int min = from;
    for(int i = from+1; i<= to; i++){ //n times
      if(a[i]<a[min]){
        min = i;
      }
    }
    return min;
  }

  static void selectionSort(int a[]){
    for(int i=0; i <a.length-1; i++){//O(n) times
      int min = minimumInRange(a, i, a.length-1);//O(n), we're doing O(n) times
      if (min!=i){//constant O(1), we're doing O(n) times
        int t= a[min];
        a[min] = a[i];
        a[i] = t;
      }
    }
  }

  public static void main(String args[]){

    int data[]= {8,6,7,5,3,0,9};
    printArray(data);
    selectionSort(data);
    printArray(data);

  }

}
