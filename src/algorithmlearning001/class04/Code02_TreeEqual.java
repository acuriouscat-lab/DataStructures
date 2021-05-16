package algorithmlearning001.class04;

import java.util.ArrayList;

public class Code02_TreeEqual {
    //给定两棵二叉树的头节点head1和head2
    //想知道head1中是否有某个子树的结构和head2完全一样
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean containsTree1(Node big, Node small) {
        if(small == null) return true;
        if(big == null) return false;

        if (isSmaeValueStructure(big, small)) {
            return true;
        }

        return containsTree1(big.left, small) || containsTree1(big.right, small);
    }

    private static boolean isSmaeValueStructure(Node big, Node small) {
        if (big == null && small != null) {
            return false;
        }
        if (small == null && big != null) {
            return false;
        }
        if (small == null && big == null) {
            return true;
        }
        if (big.value != small.value) {
            return false;
        }
        return isSmaeValueStructure(big.left, small.left) && isSmaeValueStructure(big.right, small.right);
    }


    public static boolean containsTree2(Node big, Node small) {
        if (small == null) {
            return true;
        }
        if (big == null) {
            return false;
        }

        ArrayList<String> bigArr = preSerial(big);
        ArrayList<String> smallArr = preSerial(small);

        String[] str1 = new String[bigArr.size()];
        for (int i = 0; i < str1.length; i++) {
            str1[i] = bigArr.get(i);
        }

        String[] match = new String[smallArr.size()];
        for (int i = 0; i < match.length; i++) {
            match[i] = smallArr.get(i);
        }

        return getIndexOf(str1, match) != -1;


    }

    private static int getIndexOf(String[] str1, String[] match) {
        if (str1 == null || match == null || str1.length < 1 || str1.length < match.length) {
            return -1;
        }
        int[] nextArr = getNextArr(match);
        int p1 = 0 ;
        int p2 = 0;
        while (p1 < str1.length && p2 < match.length) {
            if (isEqual(str1[p1],match[p2])) {
                p1++;
                p2++;
            } else if (nextArr[p2] == -1) {
                p1++;
            }else {
                p2 = nextArr[p2];
            }
        }

        return p2 == match.length ? p1 - p2 : -1;

    }

    public static int[] getNextArr(String[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int index = 2;
        while (index < ms.length) {
            if (isEqual(ms[index - 1],ms[cn])) {
                next[index++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            }else{
                index++;
            }
        }
        return next;
    }

    private static boolean isEqual(String m, String m1) {
        if (m == null && m1 == null) {
            return true;
        }else {
            if (m == null || m1 == null) {
                return  false;
            }else{
                return m.equals(m1);
            }
        }
    }

    public static ArrayList<String> preSerial(Node head) {
        ArrayList<String> ans = new ArrayList<>();
        pres(head, ans);
        return ans;
    }

    private static void pres(Node head, ArrayList<String> ans) {
        if(head == null){
            ans.add(null);
            return;
        }
        ans.add(String.valueOf(head.value));
        pres(head.left, ans);
        pres(head.right, ans);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int bigTreeLevel = 7;
        int smallTreeLevel = 4;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node big = generateRandomBST(bigTreeLevel, nodeMaxValue);
            Node small = generateRandomBST(smallTreeLevel, nodeMaxValue);
            boolean ans1 = containsTree1(big, small);
            boolean ans2 = containsTree2(big, small);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
}






















