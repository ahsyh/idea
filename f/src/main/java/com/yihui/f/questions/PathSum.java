package com.yihui.f.questions;

import com.yihui.f.util.Log;
import com.yihui.f.util.TreeOp.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static com.yihui.f.util.TreeOp.deserialize;
import static com.yihui.f.util.TreeOp.serialize;

public class PathSum {

    public static void test() {
        PathSum c = new PathSum();
        TreeNode<Integer> r = deserialize("{5,4,8,11,#,13,4,7,2,#,#,5,1}");
        Log.e("Verify tree: " + serialize(r));
        List<List<Integer>> result = c.pathSum(r, 22);
        Log.e("Result is: " + result);
    }

    private void tryPath(TreeNode<Integer> root, List<Integer> path, List<List<Integer>> allPaths, int sum) {
        if (root.getVal() == sum && root.isLeaf()) {
            path.add(root.getVal());
            allPaths.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        int i = root.getVal();
        int left =  sum - i;

        if (left >= 0) {
            path.add(i);
            if (root.getLeft() != null) {
                tryPath(root.getLeft(), path, allPaths, left);
            }
            if (root.getRight() != null) {
                tryPath(root.getRight(), path, allPaths, left);
            }
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> pathSum(TreeNode<Integer> root, int sum) {
        List<List<Integer>> allPaths  = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<>();

        if (root == null) {
            return allPaths;
        }

        tryPath(root, path, allPaths, sum);

        return allPaths;
    }
}
