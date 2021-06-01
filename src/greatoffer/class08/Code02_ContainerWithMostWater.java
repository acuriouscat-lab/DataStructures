package greatoffer.class08;
// 本题测试链接 : https://leetcode.com/problems/container-with-most-water/
public class Code02_ContainerWithMostWater {



    public static int maxArea1(int[] h) {
        int max = 0;
        int N = h.length;
        for (int i = 0; i < N; i++) { // h[i]
            for (int j = i + 1; j < N; j++) { // h[j]
                max = Math.max(max, Math.min(h[i], h[j]) * (j - i));
            }
        }
        return max;
    }


    public static int maxArea2(int[] h) {
        int left = 0;
        int right = h.length - 1;
        int max = 0;
        while (left < right) {
            int minHeight = h[left] < h[right] ? h[left++] : h[right--];
            max = Math.max(max, minHeight * (right - left + 1));
        }
        return max;
    }


}
