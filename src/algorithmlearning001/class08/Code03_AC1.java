package algorithmlearning001.class08;

import java.util.LinkedList;

/**
 * @author zhanglizhi
 * @Description
 * @create 2021-07-02 13:14
 */
public class Code03_AC1 {

    /**
     * Ac 自动机   -- 在前缀树上做 KMP
     * 解决的问题：
     * 1、一堆敏感词，找出在大段文字中出现了哪些敏感词
     * 2、出现了几次
     */

    public static class Node{
        public int end;
        public Node fail;
        public Node[] nexts;
        public Node(){
            end = 0;
            fail = null;
            nexts = new Node[26];
        }
    }

    public static class ACAutomation {

        Node root;
        public ACAutomation(){
            root = new Node();
        }

        public void insert(String s){
            Node cur = root;
            char[] str = s.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
            cur.end++;
        }

        public void build(){
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cfail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.nexts[i] != null) {
                        cur.nexts[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            if (cfail.nexts[i] != null) {
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public int containNum(String content){
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int ans = 0;
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                while (cur.nexts[path] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[path] != null ? cur.nexts[path] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.end == -1) {
                        break;
                    }
                    if(follow.end > 0){
                        ans += follow.end;
                        follow.end = -1;
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhe");
        ac.insert("he");
        ac.insert("c");
        ac.build();
        System.out.println(ac.containNum("cdhe"));
    }

}
