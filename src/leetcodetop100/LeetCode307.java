package leetcodetop100;

/**
 * @author zhanglizhi
 * @Description
 * @create 2021-07-01 21:29
 */
public class LeetCode307 {

    public static void main(String[] args) {
        NumArray na = new NumArray(new int[]{1, 3, 5});
        System.out.println(na.sumRange(0, 2));
    }



}

class NumArray {

    int[] tree;
    int[] arr;
    int size;
    public NumArray(int[] nums) {
        arr = nums;
        int size = nums.length;
        tree = new int[size + 1];
        for(int i = 0; i < nums.length; i++){
            add(i + 1,nums[i]);
        }
    }

    public void add(int index,int val){
        while(index <= size){
            tree[index] += val;
            index += index & -index;
        }
    }

    public int sum(int index){
        int ret = 0;
        while(index > 0){
            ret += tree[index];
            index -= (index & -index);
        }
        return ret;
    }

    public void update(int index, int val) {
        add(index + 1,val - arr[index]);
        arr[index] = val;
    }

    public int sumRange(int left, int right) {
        return sum(right + 1) - sum(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */