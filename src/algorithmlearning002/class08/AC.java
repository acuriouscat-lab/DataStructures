package algorithmlearning002.class08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AC {


    public static class Node {
        public String end;
        public boolean endUse;
        public Node fail;
        public Node[] nexts;

        public Node() {
            endUse = false;
            end = null;
            fail = null;
            nexts = new Node[26];
        }
    }

    public static class ACAutomation {
        public Node root;

        public ACAutomation() {
            root = new Node();
        }

        public void insert(String value) {
            char[] str = value.toCharArray();
            Node cur = root;
            for (int i = 0; i < str.length; i++) {
                int index = str[i] - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
            }
            cur.end = value;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
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

        public List<String> containWords(String content) {
            char[] str = content.toCharArray();
            Node cur = root;
            Node foller = null;
            int index = 0;
            List<String> res = new ArrayList<>();
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                foller = cur;
                while (foller != root) {
                    if (foller.endUse) {
                        break;
                    }
                    if (foller.end != null) {
                        res.add(foller.end);
                        foller.endUse = true;
                    }
                    foller = foller.fail;
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        ACAutomation ac = new ACAutomation();
        ac.insert("dhs");
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
