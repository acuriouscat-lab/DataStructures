package test;

public class LeetCode403 {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 3, 5, 6, 8, 12, 17};
        canCross(arr);
    }

    public static boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        if (stones.length == 1) {
            return true;
        }
        if (stones.length == 2) {
            return stones[1] == 1;
        }
        return ifCanCross(stones, 1, 1);
    }

    public static boolean ifCanCross(int[] stones, int index, int k) {
        if (index == stones.length - 1) {
            return true;
        }
        int cur = stones[index];
        System.out.println(cur);
        for (int i = index + 1; i < stones.length - 1; i++) {
            int next = stones[i];
            int gap = next - cur;
            System.out.println(next);
            if (k - 1 == gap && (ifCanCross(stones, i, k - 2)
                    || ifCanCross(stones, i, k - 1) || ifCanCross(stones, i, k))) {
                return true;
            }
            if (k == gap && (ifCanCross(stones, i, k - 1)
                    || ifCanCross(stones, i, k) || ifCanCross(stones, i, k + 1))) {
                return true;
            }
            if (k + 1 == gap && (ifCanCross(stones, i, k)
                    || ifCanCross(stones, i, k + 1) || ifCanCross(stones, i, k + 2))) {
                return true;
            }
        }
        return false;
    }
}
