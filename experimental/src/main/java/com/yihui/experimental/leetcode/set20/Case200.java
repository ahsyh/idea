package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Case200 {
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

    private Case200() {
    }

    private static Solution s = new Solution();

    private static void testOne(char[][] grid) {
        int r = s.numIslands(grid);
        Log.e("", "result is: " + r);
    }

    private static class Solution {
        private int getNextSeed(char[][] grid, Pixel p) {
            int m = grid.length;
            int n = grid[0].length;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        p.x = i;
                        p.y = j;
                        return 1;
                    }
                }
            }

            return -1;
        }

        private static class Pixel {
            public int x;
            public int y;

            public Pixel(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        private boolean isValid(char[][] grid, Pixel p) {
            int x = p.x;
            int y = p.y;

            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1') {
                return true;
            }

            return false;
        }

        private void setVisited(char[][] grid, Pixel p) {
            grid[p.x][p.y] = '2';
        }

        private void fillIsland(char[][] grid, Pixel seed) {
            List<Pixel> q = new ArrayList<>();
            q.add(seed);
            setVisited(grid, seed);
            int i = 0;

            while (i < q.size()) {
                Pixel p = q.get(i);
                i++;

                int x = p.x;
                int y = p.y;

                Pixel p1 = new Pixel(x - 1, y);
                Pixel p2 = new Pixel(x + 1, y);
                Pixel p3 = new Pixel(x, y - 1);
                Pixel p4 = new Pixel(x, y + 1);

                if (isValid(grid, p1)) {
                    q.add(p1);
                    setVisited(grid, p1);
                }
                if (isValid(grid, p2)) {
                    q.add(p2);
                    setVisited(grid, p2);
                }
                if (isValid(grid, p3)) {
                    q.add(p3);
                    setVisited(grid, p3);
                }
                if (isValid(grid, p4)) {
                    q.add(p4);
                    setVisited(grid, p4);
                }
            }
        }

        public int numIslands(char[][] grid) {
            int m = grid.length;
            if (m <= 0) {
                return 0;
            }
            int n = grid[0].length;

            Pixel seed = new Pixel(0, 0);

            int result = 0;

            while (getNextSeed(grid, seed) > 0) {
                result++;

                fillIsland(grid, seed);
            }

            return result;
        }
    }
}
