package greatoffer.class09;

import java.util.HashMap;

public class Code07_IsStepSum {

    //定义何为step sum？
    //比如680，680 + 68 + 6 = 754，680的step sum叫754
    //给定一个正数num，判断它是不是某个数的step sum
    public static boolean isStepSum(int stepSum) {
        // B 是 A 的 step sum，那么 A < B
        // 如果 A < C < B 那么，D是 C的step num的话
        // 存在 A < C < D < B
        int left = 0;
        int right = stepSum;
        int m = 0;
        while (left <= right) {
            m = (left + right) >> 1;
            int curSum = stepSum(m);
            if (curSum < stepSum) {
                left = m + 1;
            } else if (curSum > stepSum) {
                right = m - 1;
            }else{
                return true;
            }
        }
        return false;
    }

    public static int stepSum(int cur) {
        int res = cur;
        while (cur != 0) {
            cur /= 10;
            res += cur;
        }
        return res;
    }


    // for test
    public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= numMax; i++) {
            map.put(stepSum(i), i);
        }
        return map;
    }

    // for test
    public static void main(String[] args) {
        int max = 1000000;
        int maxStepSum = stepSum(max);
        HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
        System.out.println("测试开始");
        for (int i = 0; i <= maxStepSum; i++) {
            if (isStepSum(i) ^ ans.containsKey(i)) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }

}
