package Interview;

import java.util.LinkedList;

/*
 * 一张扑克有3个属性，每种属性有3种值（A、B、C）
 * 比如"AAA"，第一个属性值A，第二个属性值A，第三个属性值A
 * 比如"BCA"，第一个属性值B，第二个属性值C，第三个属性值A
 * 给定一个字符串类型的数组cards[]，每一个字符串代表一张扑克
 * 从中挑选三张扑克，每种属性达标的条件是：这个属性在三张扑克中全一样，或全不一样
 * 挑选的三张扑克达标的要求是：每种属性都满足上面的条件
 * 比如："ABC"、"CBC"、"BBC"
 * 第一张第一个属性为"A"、第二张第一个属性为"C"、第三张第一个属性为"B"，全不一样
 * 第一张第二个属性为"B"、第二张第二个属性为"B"、第三张第二个属性为"B"，全一样
 * 第一张第三个属性为"C"、第二张第三个属性为"C"、第三张第三个属性为"C"，全一样
 * 每种属性都满足在三张扑克中全一样，或全不一样，所以这三张扑克达标
 * 返回在cards[]中任意挑选三张扑克，达标的方法数
 * */
public class Code05_CardsProblem {

    public static int ways1(String[] cards) {
        return process(cards, 0, new LinkedList<String>());
    }

    public static int process(String[] cards, int index, LinkedList<String> que) {
        if (que.size() == 3) {
            return getWays(que);
        }
        if (index == cards.length) {
            return 0;
        }
        int way = process(cards, index + 1, que);
        que.addLast(cards[index]);
        way += process(cards, index + 1, que);
        que.pollLast();
        return way;
    }

    private static int getWays(LinkedList<String> que) {
        char[] s1 = que.get(0).toCharArray();
        char[] s2 = que.get(1).toCharArray();
        char[] s3 = que.get(2).toCharArray();
        for (int i = 0; i < 3; i++) {
            if ((s1[i] == s2[i] && s2[i] == s3[i]) || (s1[i] != s2[i] && s1[i] != s3[i] && s2[i] != s3[i])) {
                continue;
            }
            return 0;
        }
        return 1;
    }

    // 如果数组很大的话，上面的方法就不适合了
    // 因为只有三种属性 最多只有27种可能 所以按照属性去划分
    public static int ways2(String[] cards) {
        int[] count = new int[27];
        for (String str : cards) {
            char[] crr = str.toCharArray();
            count[(crr[0] - 'A') * 9 + (crr[1] - 'A') * 3 + (crr[2] - 'A')]++;
        }
        int way = 0;
        // 三张牌只选一样的
        for (int i = 0; i < count.length; i++) {
            int n = count[i];
            if (n > 2) {
                way += n == 3 ? 1 : (n * (n - 1) * (n - 2)) / 6;
            }
        }
        //三张牌选择不一样的
        // 深度优先遍历
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < 27; i++) {
            if (count[i] != 0) {
                que.addLast(i);
                way += process2(que, count, i + 1);
                que.pollLast();
            }
        }
        return way;
    }

    public static int process2(LinkedList<Integer> que, int[] count, int index) {
        if (que.size() == 3) {
            return getWay2(count, que);
        }
        int way = 0;
        for (int next = index; next < count.length; next++) {
            que.add(next);
            way += process2(que, count, next + 1);
            que.pollLast();
        }
        return way;
    }

    public static int getWay2(int[] counts, LinkedList<Integer> que) {
        int num1 = que.get(0);
        int num2 = que.get(1);
        int num3 = que.get(2);
        for (int i = 9; i > 0; i /= 3) {
            int cur1 = num1 / i;
            int cur2 = num2 / i;
            int cur3 = num3 / i;
            num1 %= i;
            num2 %= i;
            num3 %= i;
            if ((cur1 == cur2 && cur2 == cur3) || (cur1 != cur2 && cur2 != cur3 && cur1 != cur3)) {
                continue;
            }
            return 0;
        }
        num1 = que.get(0);
        num2 = que.get(1);
        num3 = que.get(2);
        return counts[num1] * counts[num2] * counts[num3];
    }

    // for test
    public static String[] generateCards(int size) {
        int n = (int) (Math.random() * size) + 3;
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            char cha0 = (char) ((int) (Math.random() * 3) + 'A');
            char cha1 = (char) ((int) (Math.random() * 3) + 'A');
            char cha2 = (char) ((int) (Math.random() * 3) + 'A');
            ans[i] = String.valueOf(cha0) + String.valueOf(cha1) + String.valueOf(cha2);
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int size = 20;
        int testTime = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String[] arr = generateCards(size);
            int ans1 = ways1(arr);
            int ans2 = ways2(arr);
            if (ans1 != ans2) {
                for (String str : arr) {
                    System.out.println(str);
                }
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test finish");
        long start = 0;
        long end = 0;
        String[] arr = generateCards(10000000);
        System.out.println("arr size : " + arr.length + " runtime test begin");
        start = System.currentTimeMillis();
        ways2(arr);
        end = System.currentTimeMillis();
        System.out.println("run time : " + (end - start) + " ms");
        System.out.println("runtime test end");
    }


}
