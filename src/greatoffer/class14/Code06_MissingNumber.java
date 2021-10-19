package greatoffer.class14;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-11 15:37
 */
public class Code06_MissingNumber {

    //    https://leetcode-cn.com/problems/first-missing-positive/
    public int firstMissingPositive(int[] arr) {
        int l = 0;
        int r = arr.length;
        while(l < r){
            if(arr[l] == l + 1){
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[l] == arr[arr[l] - 1]) {
                swap(arr,l,--r);
            }else{
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
