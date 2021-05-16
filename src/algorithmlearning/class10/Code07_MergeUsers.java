package algorithmlearning.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code07_MergeUsers {

    public static class Node<V>{
        V value;

        public Node(V v) {
            this.value = v;
        }
    }


    public static class UnionSet<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> values) {
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node<V> findFather(Node<V> node) {
            Stack<Node<V>> path = new Stack<>();
            while (node != parents.get(node)) {
                path.push(node);
                node = parents.get(node);
            }
            while (!path.isEmpty()) {
                parents.put(path.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(V a, V b) {

            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }

            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!parents.containsKey(a) || !parents.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));

            if (aHead != bHead) {
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);
                Node<V> big = aSize > bSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizeMap.remove(small);
                sizeMap.put(big, aSize + bSize);
            }
        }

        public int getSetNum() {
            return sizeMap.size();
        }

    }

    public static class User {
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

    }

    // (1,10,13) (2,10,37) (400,500,37)
    // 如果两个user，a字段一样、或者b字段一样、或者c字段一样，就认为是一个人
    // 请合并users，返回合并之后的用户数量
    public static int mergeUsers(List<User> users) {
        UnionSet<User> union = new UnionSet<>(users);

        HashMap<String, User> mapA = new HashMap<>();
        HashMap<String, User> mapB = new HashMap<>();
        HashMap<String, User> mapC = new HashMap<>();

        for (User user : users) {
            if (mapA.containsKey(user.a)) {
                union.union(user, mapA.get(user.a));
            }else{
                mapA.put(user.a, user);
            }

            if (mapB.containsKey(user.b)) {
                union.union(user, mapB.get(user.b));
            }else{
                mapB.put(user.a, user);
            }
            if (mapC.containsKey(user.c)) {
                union.union(user, mapC.get(user.c));
            }else{
                mapC.put(user.c, user);
            }
        }
        return union.getSetNum();
    }
}
