package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

public class Case209 {
    public static void test(String[] args) {
        testOne(15, new int[]{1,2,3,4,5}, "case0");
        testOne(7, new int[]{2,3,1,2,4,3}, "case1");
        testOne(4, new int[]{1,4,4}, "case2");
        testOne(11, new int[]{1,1,1,1,1,1,1,1}, "case3");
    }

    private static Solution s = new Solution();

    private static void testOne(int target, int[] nums, String tag) {
        int r = s.minSubArrayLen(target, nums);
        Log.e(tag, "result is: " + r);
    }

    private static class Solution {
        // search k index in list, so list[k] >= target but list[k-1] < target
        // list is increasing
        private int findK(int[] list, int left, int right, int target) {
            if (left > right) {
                return -1;
            } else if (list[left] >= target) {
                return left;
            } else if (list[right] == target) {
                return right;
            } else if (list[right] < target) {
                return -1;
            }

            if (left == (right - 1)) {
                return right;
            }

            int mid = (left + right) / 2;

            if (list[mid - 1] < target && list[mid] >= target) {
                return mid;
            }

            if (list[mid] > target) {
                return findK(list, left, mid, target);
            } else {
                return findK(list, mid, right, target);
            }

        }

        public int minSubArrayLen(int target, int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            int[] accumulate = new int[nums.length + 1];
            accumulate[0] = 0;
            for (int i = 1; i < nums.length + 1; i++) {
                accumulate[i] = accumulate[i - 1] + nums[i - 1];
            }

            if (accumulate[accumulate.length - 1] < target) {
                return 0;
            }

            int r = Integer.MAX_VALUE;

            for (int i = 0; i < accumulate.length; i++) {
                int search = accumulate[i] + target;
                int pos = findK(accumulate, i, accumulate.length - 1, search);
                if (pos > 0) {
                    int range = pos - i;
                    if (range < r) {
                        r = range;
                    }
                }
            }

            return (r == Integer.MAX_VALUE) ? 0 : r;
        }
    }
}
