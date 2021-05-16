package algorithmlearning001.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code01_CoverMax {

    //最长线段重合问题
    public static int maxCover1(int[][] lines) {
        if(lines == null || lines.length <2){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    //最长线段重合问题
    public static int maxCover2(int[][] m) {
        if(m == null || m.length <2){
            return 0;
        }

        Line[] lines = new Line[m.length];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(lines, (o1, o2) -> o1.start - o2.start);//按照开始升序排序
        PriorityQueue<Line> queue = new PriorityQueue<>((o1, o2) -> o1.end - o2.end);
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            while (!queue.isEmpty() && queue.peek().end <= lines[i].start) {//看看堆里有哪些会对我产生影响
                queue.poll();
            }
            queue.add(lines[i]);
            max = Math.max(max,queue.size());
        }
        return max;
    }




    public  static class Line{
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end  = end;
        }
    }


    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

}
