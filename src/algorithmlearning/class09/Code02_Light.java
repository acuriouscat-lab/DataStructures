package algorithmlearning.class09;

import java.util.HashSet;

/**
         * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
         * ‘X’表示墙，不能放灯，也不需要点亮
         * ‘.’表示居民点，可以放灯，需要点亮
         * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
         * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code02_Light {

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    private static int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {
                    if (!lights.contains(i - 1)
                            && !lights.contains(i)
                            && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }else {
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(yes, no);
        }

    }

    public static int minLight2(String road) {
        if ( road == null || road.length() == 0 ) {
            return 0;
        }
        int lights = 0;
        int index = 0;
        while (index < road.length()) {
            if (road.charAt(index) == 'X') {
                index++;
            }else{
                lights++;
                if (index + 1 == road.length()) {
                    break;
                }
                if (road.charAt(index + 1) == 'X') {
                    index = index + 2;
                } else {
                    index = index + 3;
                }
            }
        }
        return lights;
    }

    // for test
    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            if (ans1 != ans2) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }

}
