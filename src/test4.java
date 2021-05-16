
import java.util.*;

public class test4 {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // write code here
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isLeftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (isLeftToRight) {
                    tmp.offerLast(node.val);
                } else {
                    tmp.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            isLeftToRight = !isLeftToRight;
            res.add(new ArrayList<>(tmp));
        }
        return res;
    }

    public static void main(String[] args) {

        //char[] str = new char[5];
        //str.toString();
        String str = "aweawe";
        char[] ch = str.toCharArray();
        String s = String.valueOf(ch, 1, ch.length - 1);
        System.out.println(s);

        char ss = 'a';
        int tmp = ss + 0;
        System.out.println(tmp);

//        char sss = tmp ;
//        System.out.println(sss);

        char a = '1';
        int A = a - '0';
        System.out.println(A);

        int b = 1;
        char B = 1 + '0';
        System.out.println(B);

        char c = (char)(1);
        System.out.println(c);


        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1,24);
        map.put(23,34);
        map.put(27,34);
        map.put(15,23);
        System.out.println(map.ceilingKey(16));
    }


}
