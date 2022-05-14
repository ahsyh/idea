package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Case046 {
    public static void test(String[] args) {
        int n = 0;
        testOne(new int[]{1,2,3}, n++);
        testOne(new int[]{1,2,3,4}, n++);
    }

    private static Solution s = new Solution();

    private static void testOne(int[] nums, int n) {
        List<List<Integer>> r = s.permute(nums);
        Log.e("Case" + n, "count: " + r.size() + ", permute is: " + r);
    }

    private static class Solution {
        List<List<Integer>> res;
        public List<List<Integer>> permute(int[] nums) {
            res = new ArrayList<>();
            boolean[] used = new boolean[nums.length];
            List<Integer> path = new ArrayList<>();

            traverse(nums, path, used, 0);

            return res;
        }

        private void traverse(int[] nums, List<Integer> path, boolean[] used, int depth) {
            if (depth == nums.length) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }

                used[i] = true;
                path.add(nums[i]);
                traverse(nums, path, used, depth + 1);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }

    }
}

