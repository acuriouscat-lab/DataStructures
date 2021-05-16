package algorithmlearning.class10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_Prim {
    //1）可以从任意节点出发来寻找最小生成树
    //2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
    //3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
    //4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
    //5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
    //6）当所有点都被选取，最小生成树就得到了
    public static Set<Edge> primMST(Graph graph) {
        //解锁的边进入小根堆中
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        //解锁的点放在set
        HashSet<Node> nodeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {//随机挑选一个点
                nodeSet.add(node);
                for (Edge edge : node.edges) {//将他的所有边添加到queue
                    priorityQueue.add(edge);
                }

                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();//弹出最小边
                    Node toNode = edge.to;
                    if (!nodeSet.contains(toNode)) {
                        result.add(edge);
                        nodeSet.add(toNode);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }

}
