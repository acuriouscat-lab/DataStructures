package algorithmlearning.class10;

import java.util.*;

//undirected guigu.graph only
public class Code04_Kruskal {

    //最小生成树算法之Kruskal
    /*
        1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
        2）当前的边要么进入最小生成树的集合，要么丢弃
        3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
        4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
        5）考察完所有边之后，最小生成树的集合也得到了
     */

    public static class UnionFind {
        private HashMap<Node, Node> fatherMap;
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node a, Node b) {
            if (!fatherMap.containsKey(a) || !fatherMap.containsKey(b)) {
                return false;
            }

            return findFather(a) == findFather(b);
        }

        private Node findFather(Node node) {
            Stack<Node> path = new Stack<>();
            while (node != fatherMap.get(node)) {
                path.add(node);
                node = fatherMap.get(node);
            }

            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), node);
            }
            return node;
        }

        private void union(Node a, Node b) {
            if (!fatherMap.containsKey(a) || !fatherMap.containsKey(b)) {
                return;
            }
            Node aHead = findFather(a);
            Node bHead = findFather(b);

            if (aHead != bHead) {
                int aSize = sizeMap.get(aHead);
                int bSize = sizeMap.get(bHead);

                Node big = aSize > bSize ? aHead : bHead;
                Node small = big == aHead ? bHead : aHead;
                fatherMap.put(small, big);
                sizeMap.remove(small);
                sizeMap.put(big, aSize + bSize);
            }
        }
    }


    public static Set<Edge> kruskalMST(Graph graph) {
        if (graph == null) return null;
        UnionFind unionFind = new UnionFind();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge); // O(logM)
        }
        Set<Edge> result = new HashSet<>();
        while (priorityQueue.size() != 0) {
            Edge edge = priorityQueue.poll(); // O(logM)
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                unionFind.union(edge.from, edge.to);
                result.add(edge);
            }
        }
        return result;
    }


}
