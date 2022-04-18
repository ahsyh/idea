package com.yihui.lintcode.g;

import static com.yihui.lintcode.util.Utils.assertTrue;

public class Case1042 {
    private Case1042() {
    }

    private static Solution s = new Solution();

    public static void test() {
        int id = 1;
        int a[][]={{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        assertTrue(s.isToeplitzMatrix(a), "Case"+ id++);
        int b[][]={{2,2,3,4},{5,1,2,3},{9,5,1,2}};
        assertTrue(!s.isToeplitzMatrix(b), "Case"+ id++);
        int c[][]={{43,18,89,65,28,91,71,7,51,49,96,51,41,10,16,16},{5,43,18,89,65,28,91,71,7,51,49,96,51,41,10,16},{40,5,43,18,89,65,28,91,71,7,51,49,96,51,41,10},{57,40,5,43,18,89,65,28,91,71,7,51,49,96,51,41},{6,57,40,5,43,18,89,65,28,91,71,7,51,49,96,51},{39,6,57,40,5,43,18,89,65,28,91,71,7,51,49,96},{34,39,6,57,40,5,43,18,89,65,28,91,71,7,51,49},{80,34,39,6,57,40,5,43,18,89,65,28,91,71,7,51},{65,80,34,39,6,57,40,5,43,18,89,65,28,91,71,7},{29,65,80,34,39,6,57,40,5,43,18,89,65,28,91,71},{74,29,65,80,34,39,6,57,40,5,43,18,89,65,28,91},{31,74,29,65,80,34,39,6,57,40,5,43,18,89,65,28},{72,31,74,29,65,80,34,39,6,57,40,5,43,18,89,65},{53,72,31,74,29,65,80,34,39,6,57,40,5,43,18,89},{22,53,72,31,74,29,65,80,34,39,6,57,40,5,43,18},{54,22,53,72,31,74,29,65,80,34,39,6,57,40,5,43},{12,54,22,53,72,31,74,29,65,80,34,39,6,57,40,5},{16,12,54,22,53,72,31,74,29,65,80,34,39,6,57,40},{79,16,12,54,22,53,72,31,74,29,65,80,34,39,6,57},{35,79,16,12,54,22,53,72,31,74,29,65,80,34,39,6}};
        assertTrue(s.isToeplitzMatrix(c), "Case"+ id++);
    }

    static class Solution {
        public boolean isToeplitzMatrix(int[][] matrix) {
            // Write your code here
            // matrix[n][m]
            int n = matrix.length;
            int m = matrix[0].length;
            int min = Math.min(n, m);

            // check main
            {
                int b = matrix[0][0];
                for (int i = 1; i < min; i++) {
                    if (matrix[i][i] != b) {
                        return false;
                    }
                }
            }

            for (int j = 1; j < n; j++) {
                int b = matrix[j][0];
                for (int i = 1; i < min; i++) {
                    if ((j+i) < n && i < m && matrix[j+i][i] != b) {
                        return false;
                    }
                }
            }

            for (int j = 1; j < m; j++) {
                int b = matrix[0][j];
                for (int i = 1; i < min; i++) {
                    if ((j+i) < m && i < n && matrix[i][i+j] != b) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
