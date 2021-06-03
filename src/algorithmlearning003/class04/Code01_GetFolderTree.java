package algorithmlearning003.class04;

import java.util.HashMap;

public class Code01_GetFolderTree {
    //给你一个字符串类型的数组arr，譬如:
    //String[] arr = { "b\st", "d\", "a\d\e", "a\b\c" };
    //把这些路径中蕴含的目录结构给打印出来，子目录直接列在父目录下面，并比父目录向右进两格，就像这样:
    //a
    //  b
    //    c
    //  d
    //    e
    //b
    //  cst
    //d
    //同一级的需要按字母顺序排列不能乱。
    public static class Node{
        public String path;
        public HashMap<String,Node> nextMap;

        public Node(String path) {
            this.path = path;
            nextMap = new HashMap<>();
        }
    }

    public static void print(String[] folderPaths) {
        if (folderPaths == null || folderPaths.length == 0) {
            return;
        }

        Node head = generateNode(folderPaths);

        printProcess(head, 0);
    }

    // 每一级目录都创建对应的节点表示
    public static  Node generateNode(String[] folderPath) {
        Node head = new Node("");//系统根目录
        for (String s : folderPath) {
            String[] split = s.split("\\\\");
            Node cur = head;
            for (int i = 0; i < split.length; i++) {
                if (!cur.nextMap.containsKey(split[i])) {
                    cur.nextMap.put(split[i], new Node(split[i]));
                }
                cur = cur.nextMap.get(split[i]);
            }
        }
        return head;
    }


    public static void printProcess(Node head, int level) {
        if (level != 0) {
            System.out.println(get4nSpace(level) + head.path);
        }
        for (Node next : head.nextMap.values()) {
            printProcess(next, level + 1);
        }
            
    }

    public static String get4nSpace(int n) {
        String res = "";
        for (int i = 1; i < n; i++) {
            res += "    ";
        }
        return res;
    }

    public static void main(String[] args) {
        String[] test = new String[ ]{"a\\b\\cd", "a\\c\\d", "c\\b\\cd"};
        print(test);
    }
}
