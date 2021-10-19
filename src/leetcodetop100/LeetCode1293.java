package leetcodetop100;

import java.util.Arrays;

/**
 * @author Administrator
 * @Description
 * @create 2021-08-10 18:27
 */
public class LeetCode1293 {

    public static void main(String[] args) {
        System.out.println(1 << 0);
    }


}

class Solution {

    int n;
    int m;
    int k;
    int[][][] cache;

    public int shortestPath(int[][] grid, int k_) {
        n = grid.length;
        m = grid[0].length;
        k = k_;
        cache = new int[n][m][k + 1];
        int res = process(grid, 0, 0, k_);
        if (res == Integer.MAX_VALUE) {
            return -1;
        } else {
            return res;
        }
    }

    public int process(int[][] grid, int i, int j, int rest) {

        if (i < 0 || j < 0 || i == n || j == n) {
            return Integer.MAX_VALUE;
        }

        if (cache[i][j][rest] != 0) {
            return cache[i][j][rest];
        }

        if (i == n - 1 && j == m - 1) {
            return 1;
        }

        int all = Integer.MAX_VALUE;

        if (rest == 0) {
            all = Math.min(process(grid, i - 1, j, rest), all);
            all = Math.min(process(grid, i + 1, j, rest), all);
            all = Math.min(process(grid, i, j + 1, rest), all);
            all = Math.min(process(grid, i, j - 1, rest), all);
        }

        if (grid[i][j] == 1 && rest != 0) {
            all = Math.min(process(grid, i - 1, j, rest - 1), all);
            all = Math.min(process(grid, i + 1, j, rest - 1), all);
            all = Math.min(process(grid, i, j + 1, rest - 1), all);
            all = Math.min(process(grid, i, j - 1, rest - 1), all);
        }

        all = all == Integer.MAX_VALUE ? all : (all + 1);
        cache[i][j][rest] = all;
        return all;


    }


}


