package algorithmlearning;

import java.util.*;

public class test {

    public static void main(String[] args) {
//        String s = "abcd";
//        String ss = "efg";
//        System.out.println(s.indexOf(ss));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(-23%10);
//
//        Stack<Character> ch = new Stack<>();
//        ch.push('z');
//        char pop = ch.pop();
//        System.out.println(pop);
        String[] words = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(alienOrder(words));
        words = new String[]{"z", "x"};
        System.out.println(alienOrder(words));
    }


    //拓扑排序
    public static String alienOrder(String[] words){
        if(words == null || words.length == 0){
            return "";
        }
        int N = words.length;
        // 把字典中的所有字符当做图中的一个节点
        // 初始状态所有节点的入度都为零
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            for(char str : words[i].toCharArray()){
                map.put(str,0);
            }
        }
        // grahp
        // key 图中所有的点
        // value 当前节点指向的下一个节点
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        // 当前字符串只能和下一个比较
        // words[i] --? words[i + 1]
        for(int i = 0; i < N - 1; i++){
            char[] cur = words[i].toCharArray();
            char[] next = words[i + 1].toCharArray();
            // 需要比较的是最小长度
            int len = Math.min(cur.length, next.length);
            int j = 0;
            // 开始比较cur 和 next
            for(; j < len; j++){
                // 第一个字符不相等的地方 可以比较出一个顺序
                if(cur[j] != next[j]){
                    // 当前节点需要建立
                    if(!graph.containsKey(cur[j])){
                        graph.put(cur[j], new HashSet<>());
                    }
                    // cur[j] 第一次指向 next[j] 的时候才需要记录
                    if(!graph.get(cur[j]).contains(next[j])){
                        graph.get(cur[j]).add(next[j]);
                        map.put(next[j],map.get(next[j]) + 1);
                    }
                    break;
                }
            }
            // 不存在 abcde 比 abc 更小的顺序
            if(j < cur.length && j == next.length){
                return "";
            }
        }
        // 开始拓扑排序
        LinkedList<Character> q = new LinkedList<>();
        // 拓扑排序的顺序就是该词典的顺序
        StringBuilder strb = new StringBuilder();

        // 将所有入度为0 的点加入队列
        for (Character character : map.keySet()) {
            if(map.get(character) == 0){
                q.offer(character);
            }
        }
        while(!q.isEmpty()){
            Character chars = q.poll();
            strb.append(chars);
            //
            if(graph.containsKey(chars)){
                for (Character next : graph.get(chars)) {
                    map.put(next,map.get(next) - 1);
                    if(map.get(next) == 0){
                        q.offer(next);
                    }
                }
            }
        }
        return map.size() == strb.length() ? strb.toString() : "";
    }
}

class solution{


}

