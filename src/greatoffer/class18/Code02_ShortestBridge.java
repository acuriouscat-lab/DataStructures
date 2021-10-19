package greatoffer.class18;

import java.util.Scanner;

/**
 * @author Administrator
 * @Description https://leetcode.com/problems/shortest-bridge/
 * @create 2021-07-19 18:47
 */
public class Code02_ShortestBridge {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while(n > 0){
            int m = sc.nextInt();
            int num = process(m);
            System.out.println(num);
            n--;
        }
        sc.close();
    }

    public static int process(int n){
        if(n == 1){
            return 0;
        }
        if(n == 2){
            return 1;
        }
        if(n == 3){
            return 2;
        }
        int s1 = 1;
        int s2 = 2;
        int cur = 0;
        for(int i = 4; i <= n; i++){
            cur = s2 + s1;
            s1 = s2;
            s2 = cur;
        }
        return cur;
    }


    public int shortestBridge(int[][] grid) {
        int N = grid.length;
        int M = grid[0].length;
        int all = N * M;
        // 用来实现队列
        int[] cur = new int[all];
        int[] nexts = new int[all];
        int[][] record = new int[2][all];
        int index = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) { // 当前位置发现了1！
                    // 把这一片的1，都变成2，同时，抓上来了，这一片1组成的初始队列
                    // curs, 把这一片的1到自己的距离，都设置成1了，records
                    int queueSize = infect(grid, i, j, N, M, cur, 0, record[index]);
                    int V = 1;
                    while (queueSize != 0) {
                        V++;
                        // curs里面的点，上下左右，records[点]==0， nexts
                        queueSize = bfs(N, M, all, V, cur, queueSize, nexts, record[index]);
                        int[] tmp = cur;
                        cur = nexts;
                        nexts = tmp;
                    }
                    index++;
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < all; i++) {
            min = Math.min(min, record[0][i] + record[1][i]);
        }
        return min - 3;
    }


    public static int infect(int[][] m, int i, int j, int N, int M, int[] curs, int index, int[] record) {
        if (i < 0 || i == N || j < 0 || j == M || m[i][j] != 1) {
            return index;
        }
        // m[i][j] 不越界，且m[i][j] == 1
        m[i][j] = 2;
        int p = i * M + j;
        record[p] = 1;
        // 收集到不同的1
        curs[index++] = p;
        index = infect(m, i - 1, j, N, M, curs, index, record);
        index = infect(m, i + 1, j, N, M, curs, index, record);
        index = infect(m, i, j - 1, N, M, curs, index, record);
        index = infect(m, i, j + 1, N, M, curs, index, record);
        return index;
    }

    public static int bfs(int N, int M, int all, int V,
                          int[] curs, int size, int[] nexts, int[] record) {
        int nexti = 0; // 我要生成的下一层队列成长到哪了？
        for (int i = 0; i < size; i++) {
            // curs[i] -> 一个位置
            int up = curs[i] < M ? -1 : curs[i] - M;
            int down = curs[i] + M >= all ? -1 : curs[i] + M;
            int left = curs[i] % M == 0 ? -1 : curs[i] - 1;
            int right = curs[i] % M == M - 1 ? -1 : curs[i] + 1;
            if (up != -1 && record[up] == 0) {
                record[up] = V;
                nexts[nexti++] = up;
            }
            if (down != -1 && record[down] == 0) {
                record[down] = V;
                nexts[nexti++] = down;
            }
            if (left != -1 && record[left] == 0) {
                record[left] = V;
                nexts[nexti++] = left;
            }
            if (right != -1 && record[right] == 0) {
                record[right] = V;
                nexts[nexti++] = right;
            }
        }
        return nexti;
    }


}
