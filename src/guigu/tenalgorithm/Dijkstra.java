/*
package guigu.tenalgorithm;

import java.util.Arrays;

public class Dijkstra {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = new int[vertex.length][vertex.length];
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        //创建Graph对象
        Graph guigu.graph = new Graph(vertex, matrix);

        guigu.graph.showGraph();
    }


}

class Graph {

    private char[] vertex;  //顶点数组
    private int[][] matrix; //领接矩阵
    private VisitedVertex vv ; //已经访问过结点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //显示图
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

*
     *
     * @param idnex 表示出发顶点对应的下标


    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);

    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱结点
    public void update(int index){
        int len = 0;
        for (int i = 0; i < matrix[index].length; i++) {
            //len的含义是：出发顶点到index顶点的距离 +  从index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][i];
            //如果j没有被访问过并且小于出发定点到j定点的距离 就需要更新
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updateDis(i,index);  //更新i定点的前驱节点为index
                vv.updateDis(i, len);//更新出发定点到i定点的距离
            }
        }
    }



}

//已经访问顶点集合
class VisitedVertex {
    //记录每个顶点是否访问过 1表示有
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标
    public int[] pre_visited;
    //记录出发顶点到各个顶点的距离
    public int[] dis;

*
     * @param length 顶点的个数
     * @param index  出发顶点对应的下标


    public VisitedVertex(int length, int index) {
        //初始化
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //dis里除了当前顶点外其余结点的距离为最大值
        Arrays.fill(dis, 65535);
        this.dis[index] = 0;
    }

*
     *  判断index顶点是否被访问过
     * @param index
     * @return  访问过返回true


    public boolean in(int index) {
        return already_arr[index] == 1;
    }

*
     *  更新出发顶点到index顶点的距离
     * @param index
     * @param len


    public void updateDis(int index, int len) {
        dis[len] = len;
    }

*
     *  更新pre这个顶点的前驱顶点为index顶点
     * @param pre
     * @param index


    public void updatePre(int pre, int index) {
        pre_visited[index]= pre;
    }

*
     * 返回出发顶点到Index顶点的距离
     * @param index
     * @return


    public int getDis(int index) {
        return dis[index];
    }

    //继续选择并返回新的访问节点
    public int updateArr(){
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index =i ;
            }
        }
        already_arr[index] = 1;
        return index;
    }
}
*/
