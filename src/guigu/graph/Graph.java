package guigu.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的领结矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] visited; //表示某个结点是否被访问

    public static void main(String[] args) {
        String Vertexs[] = {"A","B","C","D","E"};
        Graph graph = new Graph(5);
        //添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }

        //添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        graph.showGraph();

        graph.dfs();

    }

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges=new int[n][n];
        numOfEdges = 0;
        visited = new boolean[n];
    }

    //得到第一个领接3结点的下标w
    public int getFirstNeighbor(int index) {
        for (int i = index; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //通过前一个领结结点的下标来获取下一个领结结点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //深度优先遍历算法
    public void dfs(boolean[] isVisited, int index) {
        System.out.print(getValueByIndex(index) + " -> ");
        //将结点设置为已访问
        isVisited[index]=true;
        //查找结点i的下一个邻接节点
        int w = getFirstNeighbor(index);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(index,w);
        }
    }
    public void dfs(){
        visited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!visited[i]) {
                dfs(visited, i);
            }
        }
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //返回结点的个数
    public int getNumOfEdges() {
        return  numOfEdges;
    }

    //返回结点对应的数据 0->"A" 1->"B"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }
    //显示对应的矩阵
    public void showGraph(){
        for (int[] arr : edges) {
            System.out.println(Arrays.toString(arr));
        }
    }
    //得到边的数目
    public int getNumOfVertex(){
        return vertexList.size();

    }
    //添加边
    /**
     *
     * @param v1    表示点的下标
     * @param v2    第二个点的下标
     * @param weight    表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


}


