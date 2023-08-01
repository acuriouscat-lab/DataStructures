package algorithmlearning001.class08;

//import org.omg.CORBA.PUBLIC_MEMBER;
//import sun.misc.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-02 13:20
 */
public class Code04_AC2 {

    /**
     * Ac 自动机   -- 在前缀树上做 KMP
     * 解决的问题： 一堆敏感词，找出在大段文字中出现了哪些敏感词
     */

    // 前缀树节点
    public static class Node{
        // 只有在上面的end变量不为空的时候，endUse才有意义
        // 表示，这个字符串之前有没有加入过答案
        public boolean endUse;
        public String end;
        // 用于在匹配不出的时候，能够跳转到的下一个可以匹配的前缀
        public Node fail;
        public Node[] nexts;

        public Node(){
            endUse = false;
            end = null;
            nexts = new Node[26];
        }
    }


    public static class ACAutomation{
        public Node root;

        public ACAutomation(){
            root = new Node();
        }

        public void insert(String s){
            char[] str = s.toCharArray();
            Node cur = root;
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
            cur.end = s;
        }

        public void build(){
            LinkedList<Node> que = new LinkedList<>();
            que.add(root);
            Node cur = root;
            Node cfail = null;
            while(!que.isEmpty()){
                cur = que.poll();
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
                        que.add(cur.nexts[i]);
                    }
                }
            }
        }

        public List<String> containWords(String content){
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow = null;
            int index = 0;
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.endUse) {
                        break;
                    }
                    // 不同的需求，在这一段之间修改
                    if (follow.end != null) {
                        ans.add(follow.end);
                        follow.endUse = true;
                    }
                    // 不同的需求，在这一段之间修改
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
        ac.insert("abcdheks");
        // 设置fail指针
        ac.build();

        List<String> contains = ac.containWords("abcdhekskdjfafhasldkflskdjhwqaeruv");
        for (String word : contains) {
            System.out.println(word);
        }
    }




}
