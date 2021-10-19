package algorithmlearning.class12;

public class Code04_CardsInLine {
    /*
        给定一个整型数组arr，代表数值不同的纸牌排成一条线，
        玩家A和玩家B依次拿走每张纸牌，
        规定玩家A先拿，玩家B后拿，
        但是每个玩家每次只能拿走最左或最右的纸牌，
        玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
     */
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    //在arr L...R 上 后手拿牌
    private static int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
    }

    //在arr L...R 上 先手拿牌
    private static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
    }

    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
        }
        // s[i][i] = 0;
        for (int i = 1; i < N; i++) {
            int L = 0;
            int R = i;
            while (L < N && R < N) {
                f[L][R] = Math.max(
                        arr[L] + s[L + 1][R],
                        arr[R] + s[L][R - 1]
                );
                s[L][R] = Math.min(
                        f[L + 1][R], // arr[i]
                        f[L][R - 1]  // arr[j]
                );
                L++;
                R++;

            }
        }
        return Math.max(f[0][N - 1], s[0][N - 1]);
    }


}
