package algorithmlearning001.class06;

import java.util.*;

public class Code04_CoverMax {
    public static class Rectangle{
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

        Arrays.sort(recs, (o1, o2) -> o1.down - o2.down);
        TreeSet<Rectangle> leftOrdered = new TreeSet<>((o1, o2) -> o1.left - o2.left);
        int ans = 0;
        for (int i = 0; i < recs.length; i++) {
            do {
                leftOrdered.add(recs[i++]);
            } while (i < recs.length && recs[i - 1].down == recs[i].down);

            removeLowerCurDown(leftOrdered, recs[i - 1].down);
            TreeSet<Rectangle> rightOrdered = new TreeSet<>(((o1, o2) -> o1.right - o2.right));

            for (Rectangle rec : leftOrdered) {
                removeLeftOnCurLeft(rightOrdered, rec.left);
                rightOrdered.add(rec);
                ans = Math.max(ans, rightOrdered.size());
            }

        }
        return ans;
    }

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
