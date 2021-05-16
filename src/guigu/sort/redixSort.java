package guigu.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序：
         * 将所有待排序的数值 统一为同样的长度   从最低位开始一次排序
         * 其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 */
public class redixSort {
    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
//        int arr[] = new int[8000000];
//        for (int i = 0; i < 8000000; i++) {
//            arr[i] = (int) (Math.random() * 8000000);
//        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date1);
        System.out.println(format);
        radixSort2(arr);
        Date date2 = new Date();
        String format1 = simpleDateFormat.format(date2);
        System.out.println(format1);

        System.out.println(Arrays.toString(arr));

    }

//    private static void radixSort(int[] arr) {
//        int max = arr[0];
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] > max) {
//                max = arr[i];
//            }
//        }
//        int maxLength = (max + "").length();    //最大数的位数
//
//        int bucket[][] = new int[10][arr.length];   //存放数据的桶
//        int bucketElementNum[] = new int[10];   //每个桶存放数据的个数
//
//        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
//            //将位数上相同的数放在同一个桶里
//            for (int j = 0; j < arr.length; j++) {
//                //取出每个元素的对应位上的数
//                int digitOfElement = arr[j] / n % 10;
//                //存入对应的桶
//                bucket[digitOfElement][bucketElementNum[digitOfElement]] = arr[j];
//                bucketElementNum[digitOfElement]++;
//            }
//            //取出
//            int index = 0;
//            for (int j = 0; j < bucketElementNum.length; j++) {
//                if (bucketElementNum[j] != 0) {
//                    for (int k = 0; k < bucketElementNum[j]; k++) {
//                        arr[index++] = bucket[j][k];
//                    }
//                }
//                //每次取出以后 需要将个数清零
//                bucketElementNum[j] = 0;
//            }
//
//        }
//    }


    public static void radixSort2(int[] arr) {
        //找出最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //获取最大值的位数
        int len = (max + "").length();

        //存放数据的桶
        int[][] bucket = new int[10][arr.length];
        //每个桶存放数据的个数
        int[] bucketElementNum = new int[10];

        for (int i = 0, n = 1; i < len; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //遍历数组的数 取出该数的对应位数上的值
                int digit = arr[j] / n % 10;
                //存放数据到桶
                bucket[digit][bucketElementNum[digit]++] = arr[j];
            }

            //取出数据
            int index = 0;
            for (int j = 0; j < arr.length; j++) {
                if (bucketElementNum[j] != 0) {//说明 位数上为j的数为bucketElementNum[j]
                    for (int k = 0; k < bucketElementNum[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                //需要清空
                bucketElementNum[j] = 0;
            }
        }


    }
}
