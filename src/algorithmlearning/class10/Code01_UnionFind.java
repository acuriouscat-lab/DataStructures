package algorithmlearning.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code01_UnionFind {
    /*
    1）每个节点都有一条往上指的指针
    2）节点a往上找到的头节点，叫做a所在集合的代表节点
    3）查询x和y是否属于同一个集合，就是看看找到的代表节点是不是一个
    4）把x和y各自所在集合的所有点合并成一个集合，只需要小集合的代表点挂在大集合的代表点的下方即可
     */
    /*
        有若干个样本a、b、c、d…类型假设是V
        在并查集中一开始认为每个样本都在单独的集合里
        用户可以在任何时候调用如下两个方法：
         boolean isSameSet(V x, V y) : 查询样本x和样本y是否属于一个集合
         void union(V x, V y) : 把x和y各自所在集合的所有样本合并成一个集合
        isSameSet和union方法的代价越低越好

     */
    public static class Node<V>{
        V value;

        public Node(V value) {
            this.value = value;
        }
    }
    
    public static class UnionSet<V>{
        public HashMap<V,Node<V>> nodes;//点的结合
        public HashMap<Node<V>,Node<V>> parents;//存放各节点的父节点
        public HashMap<Node<V>,Integer> sizeMap;//代表节点的大小

        public UnionSet(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> node) {
            Stack<Node<V>> stack = new Stack<>();
            while (node != parents.get(node)) {//将自己和所有的父节点都压入栈中
                stack.push(node);
                node = parents.get(node);
            }
            //当前node来到代表节点
            //优化----扁平化  将之前所有节点的父节点都换成为代表节点
            while (stack.size() != 0) {
                parents.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            //第一次调用的时候会很痛 但是后续都是o(1)
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return ;
            }
            //先找到代表节点
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            //判断代表节点是否相等
            //优化：小集合挂在大集合的下面
            if (aHead != bHead) {//将小的挂到大的下面
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                Node<V> big = aSize > bSize ? aHead:bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                //挂到大集合下 要修改父节点和代表节点
                parents.put(small, big);
                sizeMap.remove(small);
                sizeMap.put(big, aSize + bSize);
            }
            
        }
    }
}
