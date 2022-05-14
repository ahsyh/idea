package com.yihui.experimental.leetcode.set60;

import com.yihui.experimental.util.Log;

public class Case698 {
    public static void test(String[] args) {
        int n = 0;
        testOne(new int[]{4,3,2,3,5,2,1}, 4, n++);
        testOne(new int[]{4,4,6,2,3,8,10,2,10,7}, 4, n++);
        testOne(new int[]{7628,3147,7137,2578,7742,2746,4264,7704,9532,9679,8963,3223,2133,7792,5911,3979}, 6, n++);
    }

    private static Solution s = new Solution();

    private static void testOne(int[] nums, int k, int n) {
        boolean r = s.canPartitionKSubsets(nums, k);
        Log.e("case " + n, ", result is: " + r);
    }

    private static class Solution {
        long eachBucket;
        public boolean canPartitionKSubsets(int[] nums, int k) {
            long sum = 0;
            for (int i : nums) {
                sum += i;
            }
            if (sum % k != 0) {
                return false;
            }
            eachBucket = sum / k;

            int[] buckets = new int[k];

            return traverse(nums, k, buckets, 0);
        }

        private boolean traverse(int[] nums, int k, int[] buckets, int index) {
            if (index >= nums.length) {
                return true;
            }

            for (int j = 0; j < k; j++) {
                if (nums[index] + buckets[j] > eachBucket) {
                    continue;
                }
                buckets[j] = buckets[j] + nums[index];
                if (traverse(nums, k, buckets, index + 1)) {
                    return true;
                }
                buckets[j] = buckets[j] - nums[index];
            }
            return false;
        }
    }
}

