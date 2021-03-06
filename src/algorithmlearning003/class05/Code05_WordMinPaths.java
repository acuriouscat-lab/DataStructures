package algorithmlearning003.class05;

import java.util.*;

public class Code05_WordMinPaths {

    /*
    给定两个字符串，记为start和to，再给定一个字符串列表list，list中一定包含to list中没有重复字符串，所有的字符串都是小写的。
    规定: start每次只能改变一个字符，最终的目标是彻底变成to，但是每次变成的新字符串必须在list 中存在。
    请返回所有最短的变换路径。
    【举例】
        start="abc",end="cab",list={"cab","acc","cbc","ccc","cac","cbb","aab","abb"}
        转换路径的方法有很多种，但所有最短的转换路径如下:
        abc -> abb -> aab -> cab
        abc -> abb -> cbb -> cab
        abc -> cbc -> cac -> cab
        abc -> cbc -> cbb -> cab
     */

    public static List<List<String>> findMinPaths(String start, String to, List<String> list) {
        list.add(start);
        // 得到每个字符 只变动一个字符能到达的在list中的邻居
        HashMap<String, ArrayList<String>> nexts = getNexts(list);
        // 每个字符离 start 的距离表
        HashMap<String, Integer> distances = getDistances(start, nexts);
        LinkedList<String> path = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortesPath(start, to, nexts, distances, path, res);
        return res;
    }

    public static HashMap<String, ArrayList<String>> getNexts(List<String> words) {
        Set<String> dict = new HashSet<>(words);
        // next: key=当前字符串 val=当前字符串只变动一个字符能达到的字符串
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            nexts.put(words.get(i), getNext(words.get(i), dict));
        }
        return nexts;
    }

    /**
     *
     * @param word 当前字符串
     * @param dict 字典
     * @return 找出当前字符串只变动一个字符能在dict中找到的字符创
     */
    public static ArrayList<String> getNext(String word, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (char cur = 'a'; cur <= 'z'; cur++) {
            for (int i = 0; i < word.length(); i++) {
                if (chars[i] != cur) {
                    char temp = chars[i];
                    chars[i] = cur;
                    if (dict.contains(String.valueOf(chars))) {
                        res.add(String.valueOf(chars));
                    }
                    chars[i] = temp;
                }
            }
        }
        return res;
    }

    /**
     *  宽度优先遍历
     * @param start start
     * @param nexts 每个字符串一步能到的字符
     * @return  每个字符离 start 的距离
     */
    public static HashMap<String, Integer> getDistances(String start, HashMap<String, ArrayList<String>> nexts) {

        HashMap<String, Integer> distance = new HashMap<>();//返回的结果表， key 当前string val 当前string距离start的距离
        distance.put(start, 0);
        Queue<String> queue = new LinkedList<>();//深度优先遍历
        queue.offer(start);
        HashSet<String> set = new HashSet<>();//避免重复加入
        set.add(start);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String next : nexts.get(cur)) {
                if (!set.contains(next)) {
                    queue.offer(next);
                    set.add(next);
                    distance.put(next, distance.get(cur) + 1);
                }
            }
        }

        return distance;

    }

    /**
     *  深度优先遍历
     * @param start 当前来到的字符串
     * @param to    要变成的字符创
     * @param nexts 每个字符串以及它的下一级（只变动一个字符）
     * @param distances 每个字符串距离start的距离表
     * @param path 沿途的路径
     * @param res 收集的结果表
     */
    public static void getShortesPath(String start, String to,
                                      HashMap<String, ArrayList<String>> nexts,
                                      HashMap<String, Integer> distances,
                                      LinkedList<String> path,
                                      List<List<String>> res) {
        path.add(start);
        if (to.equals(start)) {
            res.add(new LinkedList<>(path));
        } else {
            for (String next : nexts.get(start)) {
                if (distances.get(next) == distances.get(start) + 1) {
                    getShortesPath(next, to, nexts, distances, path, res);
                }
            }
        }
        path.pollLast();

    }

    public static void main(String[] args) {
        String start = "abc";
        String end = "cab";
        String[] test = {"abc", "cab", "acc", "cbc", "ccc", "cac", "cbb",
                "aab", "abb"};
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            list.add(test[i]);
        }
        List<List<String>> res = findMinPaths(start, end, list);
        for (List<String> obj : res) {
            for (String str : obj) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }

    }


}
