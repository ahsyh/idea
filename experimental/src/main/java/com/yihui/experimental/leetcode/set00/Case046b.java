package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Case046b {
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
        private List<List<Integer>> res;

        public List<List<Integer>> permute(int[] nums) {
            res = new ArrayList<>();

            traverse(nums, 0);

            return res;
        }

        private void traverse(int[] nums, int position) {
            if (position >= nums.length) {
                res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
                return;
            }

            for (int i = position; i < nums.length; i++) {
                swap(nums, i, position);
                traverse(nums, position + 1);
                swap(nums, i, position);
            }
        }

        private void swap(int[] nums, int a, int b) {
            if (a >= nums.length || a < 0) {
                return;
            }
            if (b >= nums.length || b < 0) {
                return;
            }
            if (a == b) {
                return;
            }
            int tmp = nums[a];
            nums[a] = nums[b];
            nums[b] = tmp;
        }
    }
}
