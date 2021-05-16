package guigu.tree;

import java.util.HashMap;
import java.util.Map;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "song");
        binaryTree.setRoot(root);
        HeroNode node2 = new HeroNode(2, "wu");
        HeroNode node3 = new HeroNode(3, "lu");
        HeroNode node4 = new HeroNode(4, "lin");
        HeroNode node5 = new HeroNode(5, "guang");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        //测试
        //前序
        // binaryTree.preOrder();  12354

        //zhong
        //binaryTree.infixOrder(); //21534

        //后续
        //binaryTree.postOrder(); //25431

        //前序查找
//        HeroNode heroNode = binaryTree.preOrderSearch(6);
//        System.out.println(heroNode);

        //infixOrderSearch
        // HeroNode heroNode = binaryTree.infixOrderSearch(5);
//        System.out.println(heroNode);

        //delNode
        binaryTree.preOrder();
        binaryTree.delNode(5);
        binaryTree.preOrder();

    }
}

class BinaryTree {

    public HeroNode root;

    public void setRoot(HeroNode node) {
        this.root = node;
    }


    //delNode

    public void delNode(int no) {
        if (this.root != null) {
            if (this.root.getNo() == no) {
                this.root = null;
            } else {
                this.root.delNode(no);
            }
        } else {
            System.out.println("this guigu.tree is empty");
        }
    }

    //前序
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("节点为空无法遍历");
        }
    }

    //中序
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("节点为空无法遍历");
        }
    }

    //后序
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("节点为空无法遍历");
        }
    }

    //preOrder Search
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //infixOrderSearch
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            return null;
        }
    }
}

class HeroNode {
    private int no;
    private String name;

    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //编写zhongxu遍历的方法
    public void infixOrder() {

        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //编写houxu遍历的方法
    public void postOrder() {

        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }


    //编写前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        //说明左子树找到了
        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    //infixOrder Search
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;

        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //delNode
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }

        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        if (this.left != null) {
            this.left.delNode(no);
        }

        if (this.right != null)
            this.right.delNode(no);
    }
}

class Solution {
    /**
     *
     * @param s string字符串
     * @return int整型
     */
    public int lengthOfLongestSubstring (String s) {
        // write code here
        if(s==null || s.length()==0){
            return 0;
        }
        Map<Character,Integer> map = new HashMap<>();//map集合用来记录已经遍历过的字符
        int start=0;//每个子串开始的下标
        int maxlen=0;////记录最长不重复的子串长度
        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){//第i个字符已经遍历过
                //之所以取最大值，是避免不在当前子串，但是已经遍历过在map中存在的字符
                start = Math.max(map.get(s.charAt(i))+1,start);// 如果重复位置大于start则需要更新start,若小于则不用
            }
            map.put(s.charAt(i),i);//将遍历过的字符存放在map集合中
            maxlen=(i-start+1)>maxlen?(i-start+1):maxlen;//获取最大的子串长度

        }//for
        return maxlen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("hello"));
    }
}
