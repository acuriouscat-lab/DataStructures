package algorithmlearning001.class06;

import java.util.*;

public class Code04_CoverMax {
    public static class Rectangle {
        public int up;
        public int down;
        public int left;
        public int right;

        public Rectangle(int up, int down, int left, int right) {
            this.up = up;
            this.down = down;
            this.left = left;
            this.right = right;
        }
    }


    //最大矩形重叠个数  ----->  转化为最大线段重合问题
    public static int maxCover(Rectangle[] recs) {
        if (recs == null || recs.length == 0) {
            return 0;
        }
        // 按照每一个矩形的下边界为一条直线，找出这条之间最多的线段重合个数
        // 所以按照矩形的下边界进行排序，这样在考虑每个边界的时候可以是从小到大的，过程中可以去除掉位于当前矩形下边界的所有矩形
        Arrays.sort(recs, (o1, o2) -> o1.down - o2.down);
        TreeSet<Rectangle> leftOrdered = new TreeSet<>((o1, o2) -> o1.left - o2.left);
        int ans = 0;
        for (int i = 0; i < recs.length; i++) {
            // 和当前一样下边界的矩阵加入
            do {
                leftOrdered.add(recs[i++]);
            } while (i < recs.length && recs[i - 1].down == recs[i].down);

            removeLowerCurDown(leftOrdered, recs[i - 1].down);
            TreeSet<Rectangle> rightOrdered = new TreeSet<>(((o1, o2) -> o1.right - o2.right));

            // 虽然在 y 轴方向，他们是相交的，但是还要判断 x 轴方向 可能在 x 轴方向离得很远
            // leftOrdered 是按照左边界进行排序的
            // rightOrdered 是按照右边界进行排序的
            // 只要在 leftOrdered 中元素加入前，删除掉表里矩形右边界小于当前矩形左边界的 rightOrdered 的大小就是当前重合的个数
            for (Rectangle rec : leftOrdered) {
                removeLeftOnCurLeft(rightOrdered, rec.left);
                rightOrdered.add(rec);
                ans = Math.max(ans, rightOrdered.size());
            }

        }
        return ans;
    }

    // 将表里所有右边界小于 当前的 left 的矩形去除
    private static void removeLeftOnCurLeft(TreeSet<Rectangle> rightOrdered, int left) {
        List<Rectangle> removes = new ArrayList<>();
        for (Rectangle rec : rightOrdered) {
            if (rec.right > left) {
                break;
            }
            removes.add(rec);
        }
        for (Rectangle rec : removes) {
            rightOrdered.remove(rec);
        }
    }

    // 将表里所有上边界小于 当前的 down 的矩形去除
    private static void removeLowerCurDown(TreeSet<Rectangle> leftOrdered, int down) {
        List<Rectangle> removes = new ArrayList<>();
        for (Rectangle re : leftOrdered) {
            if (re.up <= down) {
                removes.add(re);
            }
        }

        for (Rectangle rec : removes) {
            leftOrdered.remove(rec);
        }
    }
}
