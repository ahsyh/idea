package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.LeetUtil;

public class Case162 {
    private static final String TAG = "162";
    private Case162() {
    }
    private static Solution s = new Solution();

    public static void test() {
        LeetUtil.assertTrue(s.findPeakElement(new int[]{1,5,4,3,2,1})==1, "1");
    }

    static class Solution {
        private int[] nums;

        private boolean isPeak(int index) {
            if (index > 0 && index < (nums.length - 1)) {
                return nums[index] > nums[index+1] && nums[index] > nums[index-1];
            } else if (index <= 0) {
                return nums[0] > nums[1];
            } else /* index >= (nums.length - 1) */ {
                return nums[nums.length-2]<nums[nums.length-1];
            }
        }

        public int findPeakElement(int[] nums) {
            this.nums = nums;
            if (nums.length == 0) {
                return -1;
            } else if (nums.length == 1) {
                return 0;
            }

            int left = 0;
            int right = nums.length - 1;

            while (left < right) {
                int mid = (left + right) / 2;

                if (isPeak(left)) {
                    return left;
                } else if (isPeak(right)) {
                    return right;
                } else if (isPeak(mid)) {
                    return mid;
                }

                if (nums[mid] > nums[mid+1]) {
                    right = mid;
                } else {
                    left = mid+1;
                }

            }

            return -1;
        }
    }
}
