package greatoffer.class05;

import java.util.Stack;

public class Code01_ConstructBinarySearchTreeFromPreorderTraversal {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //O(N^2)
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null) {
            return null;
        }
        return process(preorder, 0, preorder.length - 1);
    }

    public TreeNode process(int[] preorder, int index, int end) {
        if (index > end) {
            return null;
        }
        int firstBig = index + 1;
        for (; firstBig <= end; firstBig++) {
            if (preorder[firstBig] > preorder[index]) {
                break;
            }
        }
        TreeNode node = new TreeNode(preorder[index]);
        node.left = process(preorder, index + 1, firstBig - 1);
        node.right = process(preorder, firstBig, end);
        return node;
    }

    //O(N)
    public TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null) {
            return null;
        }
        int N = preorder.length;
        int[] nearBig = new int[N];
        for (int i = 0; i < N; i++) {
            nearBig[i] = -1;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < preorder.length; i++) {
            while (!stack.isEmpty() && preorder[stack.peek()] < preorder[i]) {
                nearBig[stack.pop()] = i;
            }
            stack.push(i);
        }
        return process2(preorder, 0, N - 1, nearBig);
    }

    public TreeNode process2(int[] preorder, int index, int end, int[] nearBig) {
        if (index > end) {
            return null;
        }
        int firstBig = nearBig[index] == -1 ? end + 1 : nearBig[index];
        TreeNode node = new TreeNode(preorder[index]);
        node.left = process2(preorder, index + 1, firstBig - 1, nearBig);
        node.right = process2(preorder, firstBig, end, nearBig);
        return node;
    }

    // 最优解
    public static TreeNode bstFromPreorder3(int[] pre) {
        if (pre == null || pre.length == 0) {
            return null;
        }
        int N = pre.length;
        int[] nearBig = new int[N];
        for (int i = 0; i < N; i++) {
            nearBig[i] = -1;
        }
        int[] stack = new int[N];
        int size = 0;
        for (int i = 0; i < N; i++) {
            while (size != 0 && pre[stack[size - 1]] < pre[i]) {
                nearBig[stack[--size]] = i;
            }
            stack[size++] = i;
        }
        return process3(pre, 0, N - 1, nearBig);
    }

    public static TreeNode process3(int[] pre, int L, int R, int[] nearBig) {
        if (L > R) {
            return null;
        }
        int firstBig = (nearBig[L] == -1 || nearBig[L] > R) ? R + 1 : nearBig[L];
        TreeNode head = new TreeNode(pre[L]);
        head.left = process3(pre, L + 1, firstBig - 1, nearBig);
        head.right = process3(pre, firstBig, R, nearBig);
        return head;
    }


}
