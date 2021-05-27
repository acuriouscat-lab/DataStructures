package leetcodetop100;

import java.util.Arrays;

public class AOE {



    public static int minAoe2(int[] x, int[] hp, int range) {
        int ans = 0;
        int index = 0;
        while (index < x.length) {
            if (hp[index] != 0) {
                int trigger = index;
                while (trigger < x.length && x[trigger] - x[index] <= range) {
                    trigger++;
                }
                ans += hp[index];
                aoe(x, hp, trigger - 1, index, range);
            }
            index++;
        }
        return ans;
    }

    public static void aoe(int[] x,int[] hp, int trigger, int left,int range) {
        int right = trigger;
        while (right < x.length && x[right] - x[trigger] <= range) {
            right++;
        }
        int h = hp[left];
        for (int i = left; i < right; i++) {
            hp[i] = Math.max(0,hp[i] - h);
        }
    }


    public static int minAoe3(int[] x, int[] hp, int range) {
        int N = x.length;
        int[] coverLeft = new int[N + 1];
        int[] coverRight = new int[N + 1];
        int left = 0;
        int right = 0;
        for (int i = 0; i < N; i++) {
            while (x[i] - x[left] > range) {
                left++;
            }
            while (right < N && x[right] - x[i] <= range) {
                right++;
            }
            coverLeft[i + 1] = left + 1;
            coverRight[i + 1] = right;
        }
        int[] best = new int[N + 1];
        int trigger = 0;
        for (int i = 0; i < N; i++) {
            while (trigger < N && x[trigger] - x[i] <= range) {
                trigger++;
            }
            best[i + 1] = trigger;
        }
        SegmentTree sg = new SegmentTree(hp);
        sg.build(1, N, 1);
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            int query = sg.query(i, i, 1, N, 1);
            if (query > 0) {
                ans += query;
                int t = best[i];
                int l = coverLeft[t];
                int r = coverRight[t];
                sg.add(l, r, -query, 1, N, 1);
            }
        }
        return ans;
    }

    public static class SegmentTree{
        public int[] arr;
        public int LEN;
        public int[] sum;
        public int[] lazy;

        public SegmentTree(int[] hp) {
            int LEN = hp.length + 1;
            arr = new int[LEN];
            for (int i = 1; i < arr.length; i++) {
                arr[i] = hp[i - 1];
            }
            sum = new int[LEN << 2];
            lazy = new int[LEN << 2];
        }


        public void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }

        public void pushDown(int ln, int rn, int rt) {
            if (lazy[rt] != 0) {
                lazy[rt << 1] +=lazy[rt];
                lazy[rt << 1 | 1] +=lazy[rt];
                sum[rt << 1] += ln * lazy[rt];
                sum[rt << 1 | 1] += rn * lazy[rt];
                lazy[rt] = 0;
            }
        }

        public void build(int l,int r,int rt){
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                lazy[rt] += C;
                sum[rt] += C * (l - r + 1);
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(mid - l + 1, r - mid, rt);
            if(L <= mid){
                add(L,R,C,l,mid,rt << 1);
            }
            if (mid < R) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int L,int R,int l,int r,int rt){
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(mid - l + 1, r - mid, rt);
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (mid < R) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }


    // for test
    public static int[] randomArray(int n, int valueMax) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * valueMax) + 1;
        }
        return ans;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        int N = arr.length;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 500;
        int X = 10000;
        int H = 50;
        int R = 10;
        int time = 5000;
        System.out.println("test begin");
        for (int i = 0; i < time; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] x = randomArray(len, X);
            Arrays.sort(x);
            int[] hp = randomArray(len, H);
            int range = (int) (Math.random() * R) + 1;
            int[] x2 = copyArray(x);
            int[] hp2 = copyArray(hp);
            int ans2 = minAoe2(x2, hp2, range);
            // 已经测过下面注释掉的内容，注意minAoe1非常慢，
            // 所以想加入对比需要把数据量(N, X, H, R, time)改小
//			int[] x1 = copyArray(x);
//			int[] hp1 = copyArray(hp);
//			int ans1 = minAoe1(x1, hp1, range);
//			if (ans1 != ans2) {
//				System.out.println("Oops!");
//				System.out.println(ans1 + "," + ans2);
//			}
            int[] x3 = copyArray(x);
            int[] hp3 = copyArray(hp);
            int ans3 = minAoe3(x3, hp3, range);
            if (ans2 != ans3) {
                System.out.println("Oops!");
                System.out.println(ans2 + "," + ans3);
            }
        }
        System.out.println("test end");
    }


}
