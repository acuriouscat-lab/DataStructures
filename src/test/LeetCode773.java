package test;

import java.util.HashMap;
import java.util.HashSet;

public class LeetCode773 {

    public static void main(String[] args) {
        HashSet<int[]> set = new HashSet<>();
        int[] arr1 = new int[]{1, 2, 3};
        int[] arr2 = new int[]{1, 2, 3};
        set.add(arr1);
        System.out.println(set.contains(arr2));
    }


}
