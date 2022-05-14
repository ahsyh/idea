package com.yihui.experimental.leetcode.set60;

import com.yihui.experimental.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Case698b {
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
        int bucketCount;
        int used;
        Map<Integer, Boolean> checked = new HashMap<>();
        public boolean canPartitionKSubsets(int[] nums, int k) {
            long sum = 0;
            for (int i : nums) {
                sum += i;
            }
            if (sum % k != 0) {
                return false;
            }
            eachBucket = sum / k;
            bucketCount = k;
            checked.clear();
            used = 0;

            return traverse(nums, 0, 0, 0, used);
        }

        private boolean traverse(int[] nums, int completedBucket, int currBucketSize, int currNumIndex, int used) {
            if (completedBucket >= bucketCount) {
                return true;
            }
            if (currBucketSize == eachBucket) {
                boolean r = traverse(nums, completedBucket + 1, 0, 0, used);
                checked.put(used, r);
                return r;
            }

            if (checked.containsKey(used)) {
                return checked.get(used);
            }

            for (int i = currNumIndex; i < nums.length; i++) {
                if (((used >> i) & 1) == 1) {
                    continue;
                }

                if (nums[i] + currBucketSize > eachBucket) {
                    continue;
                }

                used |= 1 << i;
                if (traverse(nums, completedBucket, currBucketSize + nums[i], i + 1, used)) {
                    return true;
                }
                used ^= 1 << i;
            }

            return false;
        }
    }
}

