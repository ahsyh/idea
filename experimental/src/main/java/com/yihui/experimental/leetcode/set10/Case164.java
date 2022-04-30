package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.LeetUtil;

import java.util.ArrayList;

public class Case164 {
    private static final String TAG = "164";
    private Case164() {
    }
    private static Solution s = new Solution();

    public static void test() {
        //LeetUtil.assertTrue(s.maximumGap(new int[]{3,6,9,1})==3, "1");
        LeetUtil.assertTrue(s.maximumGap(new int[]{100,3,2,1})==97, "2");
    }

    static class Solution {
        private static class Bucket {
            public int max;
            public int min;
            
            public Bucket(int max, int min) {
                this.max = max;
                this.min = min;
            }
        }

        private ArrayList<Bucket> buckets = new ArrayList<>();
        private double range;
        private int bucketCount = 0;
        private int listMax;
        private int listMin;

        public int maximumGap(int[] nums) {
            if (nums.length <= 1) {
                return 0;
            }

            bucketCount = nums.length - 1;
            buckets.clear();

            for (int i = 0; i < bucketCount; i++) {
                buckets.add(new Bucket(-1, -1));
            }

            listMax = nums[0];
            listMin = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (listMax < nums[i]) {
                    listMax = nums[i];
                }
                if (listMin > nums[i]) {
                    listMin = nums[i];
                }
            }
            range = ((double)(listMax - listMin)) / bucketCount;

            for (int i = 0; i < nums.length; i++) {
                int n = nums[i];
                int bucket = Double.valueOf((double)(n - listMin) / range).intValue();
                if (bucket == bucketCount) {
                    bucket--;
                }

                if (buckets.get(bucket).min != -1) {
                    if (buckets.get(bucket).min > n) {
                        buckets.get(bucket).min = n;
                    }
                    if (buckets.get(bucket).max < n) {
                        buckets.get(bucket).max = n;
                    }
                } else {
                    buckets.get(bucket).min = n;
                    buckets.get(bucket).max = n;
                }
            }

            int r = 0;
            int last = 0;
            boolean lastValid = false;
            for (int i = 0; i < bucketCount; i++) {
                Bucket b = buckets.get(i);
                if (r < (b.max - b.min)) {
                    r = b.max - b.min;
                }

                if (lastValid && b.min != -1) {
                    if (r < (b.min - last)) {
                        r = b.min - last;
                    }
                    last = b.max;
                }

                if(b.max != -1) {
                    lastValid = true;
                    last = b.max;
                }
            }

            return r;
        }
    }

}
