package algorithmlearning003.class06;

public class Code03_JumpGame {

    //给出一组正整数arr，你从第0个数向最后一个数，
    //每个数的值表示你从这个位置可以向右跳跃的最大长度
    //计算如何以最少的跳跃次数跳到最后一个数。

    public static int jump(int[] arr) {
        if(arr == null || arr.length == 0)
            return -1;

        int step = 0; // 跳了多少步
        int cur = 0; // step步内，右边界
        int next = 0;// step+1步内，右边界
        // next 一直更新，cur 只在当前来到位置 i 小于当前 cur 时更新成 next,代表此时多跳了一步 step++
        for (int i = 0; i < arr.length; i++) {
            if (cur < i) {
                step ++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }

        return step;

    }

    public static void main(String[] args) {
        int[] arr = { 3, 2, 3, 1, 1, 4 };
        System.out.println(jump(arr));

    }


}
