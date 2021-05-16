package guigu.tree.threadedbinaryTree;



public class ThreadedBinaryTree {

    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "song");
        HeroNode node2 = new HeroNode(3, "wu");
        HeroNode node3 = new HeroNode(6, "lu");
        HeroNode node4 = new HeroNode(8, "lin");
        HeroNode node5 = new HeroNode(10, "guang");
        HeroNode node6 = new HeroNode(14, "kim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadeBinaryTree binaryTree = new ThreadeBinaryTree();
        binaryTree.setRoot(root);
        binaryTree.threadBinaryNodes(root);

//        System.out.println("前驱节点：" + node5.getLeft());
//        System.out.println("后继结点："+node5.getRight());

        binaryTree.threadedList();
    }

}

class ThreadeBinaryTree {

    public HeroNode root;

    //需要有一个前驱结点
    private HeroNode pre = null;

    public void setRoot(HeroNode node) {
        this.root = node;
    }
    //遍历线索二叉树的方法
    public void threadedList(){
        HeroNode node  = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);

            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }

            node = node.getRight();
        }

    }
    //实现线索 化功能的中序二叉树
    public void threadBinaryNodes(HeroNode node) {
        //如果为null 则不能线索化
        if (node == null) {
            return;
        }
        //对左子树进行线索化
        threadBinaryNodes(node.getLeft());
        //对当前节点进行线索化
        //处理前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;

        //当右子树进行线索化
        threadBinaryNodes(node.getRight());
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

    /*
    如果为0 则表示指向的是左子树或者柚子树 如果为1则便是指向前驱结点或者后继结点
     */
    private int leftType;
    private int rightType;

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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