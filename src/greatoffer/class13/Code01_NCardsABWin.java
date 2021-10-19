package greatoffer.class13;

public class Code01_NCardsABWin {
    // 谷歌面试题
    // 面值为1~10的牌组成一组，
    // 每次你从组里等概率的抽出1~10中的一张
    // 下次抽会换一个新的组，有无限组
    // 当累加和<17时，你将一直抽牌
    // 当累加和>=17且<21时，你将获胜
    // 当累加和>=21时，你将失败
    // 返回获胜的概率
    public static double f1() {
        return p1(0);
    }

    private static double p1(int cur) {
        if (cur >= 21) {
            return 0.0;
        }
        if (cur >= 17 && cur <= 21) {
            return 1.0;
        }
        double w = 0;
        for (int i = 1; i <= 10; i++) {
            w += p1(cur + i);
        }
        return w / 10;
    }

    // 谷歌面试题扩展版
    // 面值为1~N的牌组成一组，
    // 每次你从组里等概率的抽出1~N中的一张
    // 下次抽会换一个新的组，有无限组
    // 当累加和<a时，你将一直抽牌
    // 当累加和>=a且<b时，你将获胜
    // 当累加和>=b时，你将失败
    // 返回获胜的概率，给定的参数为N，a，b
    public static double f2(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }
        // 所有参数都合法，并且b-a < N
        return p2(0, N, a, b);
    }

    // 游戏规则，如上，int N, int a, int b，固定参数！
    // cur，目前到达了cur的累加和
    // 返回赢的概率
    public static double p2(int cur, int N, int a, int b) {
        if (cur >= a && cur < b) {
            return 1.0;
        }
        if (cur >= b) {
            return 0.0;
        }
        double w = 0.0;
        for (int i = 1; i <= N; i++) {
            w += p2(cur + i, N, a, b);
        }
        return w / N;
    }

    public static double f3(int N, int a, int b) {
        if (N < 1 || a >= b || a < 0 || b < 0) {
            return 0.0;
        }
        if (b - a >= N) {
            return 1.0;
        }
        return p3(0, N, a, b);
    }

    public static double p3(int cur, int N, int a, int b) {
        if (cur >= a && cur < b) {
            return 1.0;
        }
        if (cur >= b) {
            return 0.0;
        }
        if (cur == a - 1) {
            return 1.0 * (b - a) / N;
        }
        double w = p3(cur + 1, N, a, b) + p3(cur + 1, N, a, b) * N;
        if (cur + 1 + N < b) {
            w -= p3(cur + 1 + N, N, a, b);
        }
        return w / N;
    }
}
