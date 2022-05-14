package com.yihui.experimental.leetcode.set60;

import com.yihui.experimental.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Case698d {
    public static void test(String[] args) {
        int n = 0;
        testOne(new int[]{4,3,2,3,5,2,1}, 4, ++n);
        testOne(new int[]{4,4,6,2,3,8,10,2,10,7}, 4, ++n);
        testOne(new int[]{7628,3147,7137,2578,7742,2746,4264,7704,9532,9679,8963,3223,2133,7792,5911,3979}, 6, ++n);
        testOne(new int[]{10,1,10,9,6,1,9,5,9,10,7,8,5,2,10,8}, 11, ++n);
    }

    private static Solution s = new Solution();

    private static void testOne(int[] nums, int k, int n) {
        boolean r = s.canPartitionKSubsets(nums, k);
        Log.e("case " + n, ", result is: " + r);
    }

    private static class Solution {
        private int bucketNum;
        private long bucketSize;
        private int used;
        private Map<Integer, Boolean> memo = new HashMap<>();

        private boolean traverse(int[] nums, int currBucket, int currBucketSize, int index) {
            if (currBucket >= bucketNum) {
                return true;
            }
            if (currBucketSize == bucketSize) {
                boolean r = traverse(nums, currBucket + 1, 0, 0);
                memo.put(used, r);
                return r;
            }

            if (memo.containsKey(used)) {
                return memo.get(used);
            }

            for (int i = index; i < nums.length; i++) {
                if (((used >> i) & 1) == 1) {
                    continue;
                }
                if ((currBucketSize + nums[i]) > bucketSize) {
                    continue;
                }
                used |= 1 << i;
                if (traverse(nums, currBucket, currBucketSize + nums[i], i + 1)) {
                    return true;
                }
                used ^= 1 << i;
            }

            return false;
        }

        public boolean canPartitionKSubsets(int[] nums, int k) {
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
            bucketNum = k;
            bucketSize = sum / k;
            used = 0;
            memo.clear();

            return traverse(nums, 0, 0, 0);
        }
    }
}