package com.yihui.experimental.leetcode.set10;

import static com.yihui.experimental.util.TreeOp.TreeNode;

public class Case124 {
    public static void test(String[] args) {
        testOne();
    }

    private static Solution s = new Solution();

    private static void testOne() {

    }

    private static class Solution {
        private int maxSum;

        private int oneSideMax(TreeNode n) {
            if (n == null) {
                return 0;
            }

            if (n.left == null && n.right == null) {
                maxSum = Math.max(maxSum, n.val);
                return n.val;
            }

            int left = Math.max(0, oneSideMax(n.left));
            int right = Math.max(0, oneSideMax(n.right));

            maxSum = Math.max(maxSum, left + right + n.val);
            return Math.max(left, right) + n.val;
        }

        public int maxPathSum(TreeNode root) {
            maxSum = Integer.MIN_VALUE;
            oneSideMax(root);
            return maxSum;
        }
    }
}
