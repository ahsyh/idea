package com.yihui.experimental.leetcode.set10;

import java.util.ArrayList;
import java.util.List;
import static com.yihui.experimental.util.TreeOp.TreeNode;

public class Case199 {
    public static void test(String[] args) {
        testOne();
    }

    private static Solution s = new Solution();

    private static void testOne() {

    }

    private static class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<TreeNode> bfs = new ArrayList<>();
            List<Integer> result = new ArrayList<>();

            if (root == null) {
                return result;
            }

            bfs.add(root);
            int i = 0;

            while (i < bfs.size()) {
                int end = bfs.size();
                result.add(bfs.get(end-1).val);

                for (; i < end; i++) {
                    TreeNode n = bfs.get(i);
                    if (n.left != null) {
                        bfs.add(n.left);
                    }
                    if (n.right != null) {
                        bfs.add(n.right);
                    }
                }

            }

            return result;
        }
    }
}

