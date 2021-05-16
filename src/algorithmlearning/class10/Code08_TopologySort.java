package algorithmlearning.class10;


import java.util.*;

//https://www.lintcode.com/problem/topological-sorting/description
public class Code08_TopologySort {
    //图的拓扑排序算法
    /*
        1）在图中找到所有入度为0的点输出
        2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
        3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
        要求：有向图且其中没有环
        应用：事件安排、编译顺序
     */
    //directed guigu.graph and no loop
    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                queue.add(node);
            }
        }

        //结果依次放在result中
        List<Node> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, next.in - 1);
                if (inMap.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        return result;
    }
}
