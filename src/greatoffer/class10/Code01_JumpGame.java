package greatoffer.class10;

public class Code01_JumpGame {

    // https://leetcode-cn.com/problems/jump-game-ii/
    public int jump(int[] nums) {
        int cur = 0;// 当前step步数内的最右边界
        int next = 0;// 当前step + 1步数内的最右边界
        int step = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > cur){
                step++;
                cur = next;
            }
            next = Math.max(next,i + nums[i]);
        }
        return step;
    }

}
