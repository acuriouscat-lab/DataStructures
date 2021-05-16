package guigu.tenalgorithm;

import java.awt.*;
import java.util.ArrayList;

public class horse {

    static int X;//列数
    static int Y;//行数
    //创建一个数组标记期房的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性，标记是否棋盘的所有位置都被访问
    private static boolean finished;
     public static void main(String[] args) {
         System.out.println("骑士周游算法，开始运行");
         X=8;
         Y=8;
         int row =1;
         int column = 1;
         int[][] chessboard = new int[X][Y];
         visited = new boolean[X * Y];
         long start = System.currentTimeMillis();
         traversalChessboard(chessboard, row - 1, column - 1, 1);
         long end = System.currentTimeMillis();
         System.out.println("一共耗时：" + (end - start));
         
         for(int[] rows:chessboard){
             for(int step:rows){
                 System.out.print(step + "\t");
             }
             System.out.println();
         }
    }

    /**
     *  骑士周游算法
     * @param chessboard    棋盘
     * @param row   当前的位置的行
     * @param column    列
     * @param step  第几步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true;//标记该位置已经被访问
        ArrayList<Point> ps = next(new Point(column, row));//当前位置下一步能走的位置的集合
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            //如果没有被访问过  则递归
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
            //判断是否全部走完
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row*Y+column] = false;
        }else{
            finished =true;
        }
    }

    /**
     *  根据当前位置，计算马儿还能走那些位置，并放入一个集合中，最多有8个位置
     * @param curPoint  当前位置
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> p = new ArrayList<>();

        Point p1 = new Point();
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            p.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            p.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            p.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            p.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            p.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            p.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >=0) {
            p.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            p.add(new Point(p1));
        }

        return p;
    }

    public static void sort(ArrayList<Point> ps) {
        ps.sort((o1, o2) -> next(o1).size()-next(o2).size());
        /*ps.guigu.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size()-next(o2).size();
            }
        });*/
    }


}
