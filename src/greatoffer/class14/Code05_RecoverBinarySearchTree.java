package greatoffer.class14;

/**
 * @author Administrator
 * @Description
 * @create 2021-07-11 15:39
 */
public class Code05_RecoverBinarySearchTree {

    // 不要提交这个类
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            val = v;
        }
    }


    //https://leetcode-cn.com/problems/recover-binary-search-tree/
    public void recoverTree(TreeNode root) {
        if(root == null){
            return ;
        }
        TreeNode[] errors = twoErrors(root);
        if(errors[0] != null && errors[1] != null){
            int tmp = errors[0].val;
            errors[0].val = errors[1].val;
            errors[1].val = tmp;
        }
    }


    public TreeNode[] twoErrors(TreeNode node){
        TreeNode cur = node;
        TreeNode mostRight = null;
        TreeNode e1 = null;
        TreeNode e2 = null;
        TreeNode pre = null;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right == null){
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    mostRight.right = null;
                }
            }
            if(pre != null && pre.val >= cur.val){
                e1 = e1 == null ? pre : e1;
                e2 = cur;
            }
            pre = cur;
            cur = cur.right;
        }
        return new TreeNode[]{e1,e2};
    }




}
