package greatoffer;

import java.util.*;

/**
 * @author Administrator
 * @Description
 * @create 2021-08-10 21:47
 */
public class Test {

    public static void main(String[] args) {
//        Solution s = new Solution();
//        System.out.println(s.binSearch(new int[]{3, 4, 5, 6, 7, 9}, 6));
        Random r = new Random();
    }




}


class Solution {
    /**
     * 在输入数组中的查找所在元素的位置以及查找次数,返回格式String ："i,c"  (i为元素下表，c为查找次数)
     * @param arr int整型一维数组 数组
     * @param key int整型 待查找的元素
     * @return string字符串
     */

    int idx;
    int cnt;
    public String binSearch (int[] arr, int key) {
        // write code here
        int N = arr.length;
        idx = 0;
        cnt = 1;
        search(arr,key,0,N - 1);
        return String.valueOf(idx + "," + cnt);
    }

    public boolean search(int[] arr,int key,int left,int right){
        if(left == right){
            if(arr[left] == key){
                idx = left;
                return true;
            }
            return false;
        }
        int mid = (right + left) >> 1;
        cnt++;
        if (arr[mid] == key) {
            idx = mid;
            return true;
        } else if (arr[mid] > key) {
            return search(arr, key, left, mid - 1);
        }else{
            return search(arr, key, mid + 1, right);
        }

    }
}
