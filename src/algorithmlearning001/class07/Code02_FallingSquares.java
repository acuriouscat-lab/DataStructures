package algorithmlearning001.class07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Code02_FallingSquares {
    /*
    想象一下标准的俄罗斯方块游戏，X轴是积木最终下落到底的轴线
        下面是这个游戏的简化版：
        1）只会下落正方形积木
        2）[a,b] -> 代表一个边长为b的正方形积木，积木左边缘沿着X = a这条线从上方掉落
        3）认为整个X轴都可能接住积木，也就是说简化版游戏是没有整体的左右边界的
        4）没有整体的左右边界，所以简化版游戏不会消除积木，因为不会有哪一层被填满。

        给定一个N*2的二维数组matrix，可以代表N个积木依次掉落，
        返回每一次掉落之后的最大高度
     */
    public static class SegmentTree {
        private int[] max;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int N) {
            int size = N + 1;
            max = new int[size << 2];
            change = new int[size << 2];
            update = new boolean[size << 2];
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] = true;
                max[rt] += C ;
                change[rt] += C;
                return;
            }
            int mid = (l + r ) >>1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 |1);
            }
            pushUp(rt);
        }

        public int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }

            int mid = (l +r) >>1;
            pushDown(rt, mid - l + 1, r - mid);
            int left = 0;
            int right= 0;
            if (L <= mid) {
                left = query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = query(L,R,mid+1,r,rt<<1|1);
            }
            return Math.max(left,right);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt]) {
                update[rt << 1] = true;
                update[rt<<1 |1] = true;
                max[rt << 1] = change[rt];
                max[rt << 1 | 1] = change[rt];
                change[rt << 1] = change[rt];
                change[rt << 1 | 1] = change[rt];
                update[rt] = false;
            }
        }
    }


    public List<Integer> fallingSquares(int[][] positions) {
        HashMap<Integer, Integer> map = index(positions);
        List<Integer> res = new ArrayList<>();
        int size = map.size();
        SegmentTree st = new SegmentTree(size);
        int max = 0;
        for (int[] arr : positions) {
            int L = map.get(arr[0]);
            int R = map.get(arr[1] + arr[0] - 1);
            int height = st.query(L,R,1,size,1) + arr[1];
            max = Math.max(max,height);
            res.add(max);
            st.update(L,R,height,1,size,1);
        }
        return res;
    }

    //数据预处理
    //将坐标转化为更小的
    public HashMap<Integer, Integer> index(int[][] positions) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] arr : positions) {
            set.add(arr[0]);
            set.add(arr[1] + arr[0] - 1);
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (Integer index : set) {
            map.put(index, ++count);
        }

        return map;
    }
}
