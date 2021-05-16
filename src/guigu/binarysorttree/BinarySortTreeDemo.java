package guigu.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3};
        BinarySortTree binarySortTree = new BinarySortTree();

        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        binarySortTree.infixOrder();

        // binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        System.out.println("中序遍历");
        binarySortTree.infixOrder();

    }

}

//二叉排序树
class BinarySortTree {
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