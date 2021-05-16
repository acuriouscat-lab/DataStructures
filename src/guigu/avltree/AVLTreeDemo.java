package guigu.avltree;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};

        AVLTree avlTree = new AVLTree();

        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("未作处理前");
        System.out.println("get Treeheight:" + avlTree.getRoot().height());
        System.out.println("left:" + avlTree.getRoot().leftHeight());
        System.out.println("right:" + avlTree.getRoot().rightHeight());

    }
}

class AVLTree {
    private Node root;

    //增加节点
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        root.infixOrder();
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 返回最小节点的值
     * 删除node为根节点的二叉排序树的最小节点
     *
     * @param node 传入的结点(当做二叉排序树的根节点)
     * @return 返回的 以node为根节点的二叉排序树的最小节点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            Node targetNode = search(value);
            //如果没找到
            if (targetNode == null) {
                return;
            }
            //如果查找的就是根节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            Node parentNode = searchParent(value);

            //如果删除的结点为根节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断是父节点的左节点还是右结点
                if (parentNode.left != null && parentNode.left.value == value) {
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value) {
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {   //删除有两颗子树的结点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {//删除只有一个子树的结点
                if (targetNode.left != null) {
                    if (parentNode != null) {
                        if (parentNode.left != null && parentNode.left.value == value) {
                            parentNode.left = targetNode.left;
                        } else if (parentNode.right != null && parentNode.right.value == value) {
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parentNode != null) {
                        if (parentNode.left != null && parentNode.left.value == value) {
                            parentNode.left = targetNode.right;
                        } else if (parentNode.right != null && parentNode.right.value == value) {
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }


        }
    }

}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回当前节点的高度，以该结点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转的方法
    public void leftRotate() {
        //创建一个新节点等于根节点的值
        Node node = new Node(value);
        //把新节点的左子树设置成当前节点的左子树
        node.left = left;
        //把新节点的右子树设置为当前结点的右子树的左子树
        node.right = right.left;
        //把当前节点的值设置为右子节点的值
        value = right.value;
        //把当前节点的右子树设置为右子树的右子树
        right = right.right;
        //把当前节点的左子树设置为新节点
        left = node;
    }

    //右旋转
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }


    /**
     * 查找要删除结点的父节点
     *
     * @param value 要找到结点的值
     * @return 返回要删除结点的父节点 没有返回空
     */
    public Node searchParent(int value) {
        if (this.left != null && this.left.value == value || this.right != null && this.right.value == value) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            }
        }
        return null;
    }

    /**
     * //查找要删除的结点
     *
     * @param value 要删除节点的值
     * @return 如果找到返回该结点 否则为空
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {//查找的结点比当前节点小，向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //
    //添加方法
    public void addNode(Node node) {
        if (node == null) {
            return;
        }

        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.addNode(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.addNode(node);
            }
        }
        //判断是否（右-左）的高度大于1 ， 则左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树大于它的右子树的右子树的高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }

        if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于它的左子树的高度
            if (left != null && this.left.leftHeight() < this.right.rightHeight()) {
                //先对当前节点的左节点进行左旋转
                this.left.leftRotate();
                //再进行又旋转
                rightRotate();
            } else {
                rightRotate();
            }
        }

    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}