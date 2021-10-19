package LeetCodeTest;

public class Code2 {

    public static void main(String[] args) {
        for(int i = 1; i < 5000; i++){
            System.out.println((i ) + " " +NS_LIS(i + ""));
        }
        System.out.println(NS_LIS(994 + ""));
        System.out.println(NS_LIS(9994 + ""));
        System.out.println(Long.MAX_VALUE);
//        System.out.println(10e100000);
    }

    public static int NS_LIS (String n) {
        // write code here
        int len = Integer.valueOf(n);
        int[] nums = new int[len];
        for(int i = 0; i < len; i++){
            nums[i] = get(i + 1);
        }
        return maxLength(nums);
    }

    public static int get(int i){
        int res = 0;
        while(i != 0){
            res += i % 10;
            i /= 10;
        }
        return res;
    }

    public static int maxLength(int[] nums){
        int N = nums.length;
        int[] ends = new int[N];
        ends[0] = nums[0];
        int right = 0;
        for(int i = 1; i < nums.length; i++){
            int l = 0;
            int r = right;
            while(l <= r){
                int m = (l + r) >> 1;
                if (ends[m] < nums[i]) {
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            right = Math.max(right,l);
            ends[l] = nums[i];
        }
        return right + 1;
    }

}
