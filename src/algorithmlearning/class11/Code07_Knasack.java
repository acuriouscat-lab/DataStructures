package algorithmlearning.class11;

public class Code07_Knasack {

    /*
        给定两个长度都为N的数组weights和values，
        weights[i]和values[i]分别代表 i号物品的重量和价值。
        给定一个正数bag，表示一个载重bag的袋子，
        你装的物品不能超过这个重量。
        返回你能装下最多的价值是多少?
     */

    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, 0, bag);
    }

    //0...index - 1 已经形成了alreadyW
    private static int process(int[] w, int[] v, int index, int alredyW, int bag) {
        if (alredyW > bag) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        //不要当前的货物
        int p1 = process(w, v, index + 1, alredyW, bag);
        int p2 = -1;//要当前的货物
        int p2Next = process(w, v, index + 1, alredyW + w[index], bag);
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    public static int maxValue(int[] w, int[] v, int bag) {
        return process(w, v,0, bag);
    }

    //返回index...之后的能放置的最大价值
    private static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == v.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);//当前第index的物品没有拿
        int p2 = -1;
        int p2Next = process(w, v, index + 1, rest - w[index]);
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }

        return Math.max(p1, p2);
    }
}
