package greatoffer.class13;

//https://leetcode-cn.com/problems/super-washing-machines/submissions/
public class Code02_SuperWashingMachines {
    public int findMinMoves(int[] machines) {
        if (machines == null || machines.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int machine : machines) {
            sum += machine;
        }
        int N = machines.length;
        if (sum % N != 0) {
            return -1;
        }
        int even = sum / N;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            // 来到当前位置左边剩下多少
            int leftRest = leftSum - even * i;
            // 右边剩下多少
            int rightRest = (sum - leftSum - machines[i]) - (N - i - 1) * even;
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest + rightRest));
            } else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
            }
            leftSum += machines[i];
        }
        return ans;
    }

}
