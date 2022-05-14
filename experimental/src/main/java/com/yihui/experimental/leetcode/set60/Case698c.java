package com.yihui.experimental.leetcode.set60;

import com.yihui.experimental.util.Log;

import java.util.Arrays;

public class Case698c {
    public static void test(String[] args) {
        int n = 0;
        testOne(new int[]{4,3,2,3,5,2,1}, 4, n++);
        testOne(new int[]{4,4,6,2,3,8,10,2,10,7}, 4, n++);
        testOne(new int[]{7628,3147,7137,2578,7742,2746,4264,7704,9532,9679,8963,3223,2133,7792,5911,3979}, 6, n++);
        testOne(new int[]{10,1,10,9,6,1,9,5,9,10,7,8,5,2,10,8}, 11, n++);
    }

    private static Solution s = new Solution();

    private static void testOne(int[] nums, int k, int n) {
        boolean r = s.canPartitionKSubsets(nums, k);
        Log.e("case " + n, ", result is: " + r);
    }

    private static class Solution {
        private int bucketNum;
        private long bucketSize;
        private int[] buckets;

        private boolean traverse(int[] nums, int index) {
            if (index >= nums.length) {
                return true;
            }

            for (int i = 0; i < bucketNum; i++) {
                if (buckets[i] + nums[index] > bucketSize) {
                    continue;
                }
                buckets[i] += nums[index];
                if (traverse(nums, index + 1)) {
                    return true;
                }
                buckets[i] -= nums[index];
            }

            return false;
        }

        public boolean canPartitionKSubsets(int[] nums, int k) {
            bucketNum = k;
            long sum = 0;
            for (int n : nums) {
                sum += n;
            }
            if (k > nums.length) {
                return false;
            }
            if (sum % k != 0) {
                return false;
            }
            bucketSize = sum / k;
            buckets = new int[k];

            Arrays.sort(nums);
            for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }

            return traverse(nums, 0);
        }
    }
}
