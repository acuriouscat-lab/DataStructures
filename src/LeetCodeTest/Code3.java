package LeetCodeTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

public class Code3 {

    public int[] Magical_NN2 (int[] h) {
        // write code here
        int[] res = new int[h.length];
        for(int i = 0; i < h.length; i++){
            int sum = 0;
            for(int j = 0; j < h.length; j++){
                sum += Math.abs(h[i] - h[j]);
            }
            res[i] = sum;
        }
        return res;
    }

    public static int[] Magical_NN (int[] h) {
        // write code here
        int[] res = new int[h.length];
        int[] temp = Arrays.copyOf(h, h.length);
        Arrays.sort(temp);
        System.out.println(Arrays.toString(temp));
        int[] arr = new int[h.length];
        arr[0] = temp[0];
        for (int i = 1; i < h.length; i++) {
            arr[i] = arr[i - 1] + temp[i];
        }
        System.out.println(Arrays.toString(arr));
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(temp[0],arr[h.length - 1] - (h.length) * temp[0]);
        for(int i = 1; i < h.length; i++){
            int lessSum = Math.abs((i) * temp[i] - arr[i - 1]);
            System.out.println(lessSum + " " + i);
            int rightSum = Math.abs(arr[h.length - 1] - arr[i] - (temp[i] * (h.length - i - 1)));
            System.out.println(rightSum  + " " + i);
            map.put(temp[i],lessSum + rightSum);
        }
        for(int i = 0; i < res.length; i++){
            res[i] = map.get(h[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(5, 1);
        map.put(3, 2);
        System.out.println(Arrays.toString(Magical_NN(new int[]{3, 1, 4, 5, 3})));
    }



//    public static class SegmentTree{
//        int[] arr;
//        int[] sum;
//        int MAXN;
//
//        public SegmentTree(int[] nums) {
//            int MAXN = nums.length + 1;
//            arr = new int[MAXN];
//        }
//
//    }


}
