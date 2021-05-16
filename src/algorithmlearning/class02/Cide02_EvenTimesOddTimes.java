package algorithmlearning.class02;

class Code02_EvenTimesOddTimes {

    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    //    /
    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000
        int righrOne = eor & (~eor + 1);//结果肯定是一个最右侧为1 一个对于能够为上为0

        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((righrOne & arr[i]) != 0) {//找出对应位为1的
                onlyOne ^= arr[i];
            }
        }

        System.out.println(onlyOne + "--" + (onlyOne ^ eor));


    }


    public static int bit1counts(int N) {
        int count = 0;

        while (N != 0) {
            int rightOne = N & (-N);
            count++;
            N = N ^ rightOne;
        }

        return count;
    }

    public static void main(String[] args) {
//        int a = 5;
//        int b = 7;
//
//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;
//
//        System.out.println(a);
//        System.out.println(b);

        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);

    }


}
