package com.yihui.experimental.util;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Tree operation class.
 */
public final class TreeOp {
    private TreeOp() {
    }

    /**
     * TreeNode.
     */
    @Data
    public static class TreeNode {
        public Integer val;
        public TreeNode left;
        public TreeNode right;

        /**
         * construct.
         * @param t ..
         */
        public TreeNode(final Integer t) {
            val = t;
            left = null;
            right = null;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }
    }

    /**
     * serialize.
     * @param root ...
     * @return ...
     */
    public static String serialize(final TreeNode root) {
        StringBuilder sb = new StringBuilder();

        if (root == null) {
            return "{}";
        }

        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        for (int i = 0; i < list.size(); i++) {
            TreeNode n = list.get(i);
            if (n != null) {
                list.add(n.getLeft());
                list.add(n.getRight());
            }
        }

        sb.append("{").append(list.get(0).getVal());
        for (int i = 1; i < list.size(); i++) {
            TreeNode n = list.get(i);
            if (n == null) {
                sb.append(",#");
            } else {
                sb.append(",").append(n.getVal());
            }
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * deserialize tree.
     * @param str ..
     * @return ...
     */
    public static TreeNode deserialize(final String str) {
        if (str.equals("{}")) {
            return null;
        }

        String[] val = str.substring(1, str.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(val[0]));
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        boolean isLeftChild = true;
        for (int i = 1; i < val.length; i++) {
            if (!"#".equals(val[i])) {
                TreeNode n = new TreeNode(Integer.parseInt(val[i]));
                if (isLeftChild) {
                    q.peek().left = n;
                } else {
                    q.peek().right = n;
                }
                q.add(n);
            }
            if (!isLeftChild) {
                q.poll();
            }
            isLeftChild = !isLeftChild;
        }

        return root;
    }


    /**
     * get depth.
     * @param root ..
     * @return ..
     */
    public static int treeDepth(final TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * test method.
     */
    public static void test() {
        String source = "{3,9,20,#,#,15,7}";
        TreeNode n = deserialize(source);
        String str = serialize(n);
        Log.e("", "reuslt is: " + str);
        Log.e("", "depth of root: " + treeDepth(n));
        Log.e("", "depth of left: " + treeDepth(n.left));
        Log.e("", "depth of right: " + treeDepth(n.right));
    }
}

