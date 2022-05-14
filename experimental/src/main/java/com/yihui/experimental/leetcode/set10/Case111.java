package com.yihui.experimental.leetcode.set10;


import java.util.ArrayDeque;
import java.util.Queue;

import static com.yihui.experimental.util.TreeOp.TreeNode;

public class Case111 {
    public static void test(String[] args) {
        testOne();
    }

    private static Solution s = new Solution();

    private static void testOne() {

    }

    private static class Solution {
        public int minDepth(TreeNode root) {
            int depth = 0;
            Queue<TreeNode> q = new ArrayDeque<>();

            q.offer(root);
            depth = 1;
            while (!q.isEmpty()) {
                int end = q.size();
                for (int i = 0; i < end; i++) {
                    TreeNode n = q.poll();
                    if (n.left == null && n.right == null) {
                        return depth;
                    }
                    if (n.left != null) {
                        q.offer(n.left);
                    }
                    if (n.right != null) {
                        q.offer(n.right);
                    }
                }
                depth++;
            }
            return depth;
        }

    }
}
