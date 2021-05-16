package algorithmlearning.class05;

import java.util.HashMap;

public class Code_TrieTree {

    public static class Node1 {

        private int pass;
        private int end;
        private Node1[] next;

        public Node1() {
            pass = 0;
            end = 0;
            next = new Node1[26];
        }
    }

    public static class Trie1 {

        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String value) {
            if (value == null) {
                return;
            }
            char[] chars = value.toCharArray();
            root.pass++;
            Node1 node = root;
            int path = 0;
            for (int i = 0; i < chars.length; i++) {
                path = chars[i] - 'a';
                if (node.next[path] == null) {
                    node.next[path] = new Node1();
                }
                node = node.next[path];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                root.pass--;
                Node1 node = root;
                int index = 0;
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    if (--node.next[index].pass == 0) {
                        node.next[index] = null;
                        return;
                    }
                    node = node.next[index];
                }
                node.end--;
            }
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.end;
        }

        public int prefixNumber(String value) {
            if (value == null) {
                return 0;
            }
            char[] chars = value.toCharArray();
            int index = 0;
            Node1 node = root;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.next[index] == null) {
                    return 0;
                }
                node = node.next[index];
            }
            return node.pass;
        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> next;

        public Node2() {
            pass= 0;
            end = 0;
            next = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        //增加
        public void insert(String value) {
            if (value == null) {
                return;
            }
            char[] chars = value.toCharArray();
            Node2 node = root; //临时节点
            node.pass++;
            int index = 0; //每个字符的大小
            for (int i = 0; i < chars.length; i++) {
                index = (int) chars[i];
                if (!node.next.containsKey(index)) {
                    node.next.put(index, new Node2());
                }
                node = node.next.get(index);
                node.pass++;
            }
            node.end++;
        }

        //删除
        public void delete(String value) {
            if (search(value) != 0) {
                Node2 node = root;
                node.pass--;
                char[] chars = value.toCharArray();
                int index;
                for (char aChar : chars) {
                    index = (int)aChar;
                    if (--node.next.get(index).pass == 0) {
                        node.next.remove(index);
                        return;
                    }
                    node = node.next.get(index);
                }
                node.end--;
            }
        }

        //查询出现过几次
        public int search(String value) {
            if (value == null) {
                return 0;
            }
            Node2 node = root;
            char[] chars = value.toCharArray();
            int index;
            for (int i = 0; i < chars.length; i++) {
                index = (int)chars[i];
                if (!node.next.containsKey(index)) {
                    return 0;
                }
                node = node.next.get(index);
            }
            return node.end;
        }

        //查询前缀
        public int prefixNumber(String value) {
            if (value == null) {
                return 0;
            }
            Node2 node = root;
            char[] chars = value.toCharArray();
            int index;
            for (char ch : chars) {
                index = (int)ch;
                if (!node.next.containsKey(index)) {
                    return 0;
                }
                node = node.next.get(index);
            }
            return node.pass;
        }
    }

    public static class Right {

        private HashMap<String, Integer> box;

        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");


    }
}
