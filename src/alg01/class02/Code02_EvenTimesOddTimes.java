package alg01.class02;

public class Code02_EvenTimesOddTimes {



    // arr中，只有一种数，出现奇数次
   public static void printOddTimesNum1(int[] arr){
       int eor = 0;
       for (int ele : arr) {
           eor ^= ele;
       }
       System.out.println(eor);
   }
    // arr中，有两种数，出现奇数次
   public static void printOddTimesNum2(int[] arr){
       int eor = 0;
       for (int j : arr) {
           eor ^= j;
       }
       int rightOne = eor & (-eor);
       int rightOneHas = 0;
       for (int ele : arr) {
           if((rightOne & ele) != 0){
               rightOneHas ^= ele;
           }
       }

       System.out.println(rightOneHas + " --- " + (rightOneHas ^ eor));
   }

   public static int bit1counts(int N){
       int count = 0;
       while(N !=0 ){
           int rightOne = N & (-N);
           count++;
           N = N ^ rightOne;
       }
       return count;
   }



    public static void main(String[] args) {
        printOddTimesNum1(new int[]{1,2,3,2,3});
        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum2(arr2);
        System.out.println(bit1counts(9));
    }


}
