package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

public class Case200b {
    public static void test(String[] args) {
//        [["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]]
        char[][] g = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'},
        };
        testOne(g);
    }

    private static Solution s = new Solution();

    private static void testOne(char[][] grid) {
        int r = s.numIslands(grid);
        Log.e("", "result is: " + r);
    }

    private static class Solution {

        private void bfs(char[][] grid, int x, int y) {
            if (x < 0 || x >= m || y < 0 || y >= n) {
                return;
            }

            if (grid[x][y] != '1') {
                return;
            }
            grid[x][y] = '2';
            bfs(grid, x - 1, y);
            bfs(grid, x + 1, y);
            bfs(grid,  x, y - 1);
            bfs(grid,  x, y + 1);
        }

        private int m;
        private int n;

        public int numIslands(char[][] grid) {
            m = grid.length;
            if (m <= 0) {
                return 0;
            }
            n = grid[0].length;
            if (n <= 0) {
                return 0;
            }

            int r = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        r++;
                        bfs(grid, i, j);
                    }
                }
            }

            return r;
        }
    }
}