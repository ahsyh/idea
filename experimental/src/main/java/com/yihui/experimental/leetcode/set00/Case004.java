package com.yihui.experimental.leetcode.set00;

import com.yihui.experimental.util.LeetUtil;

import static java.lang.Math.abs;

public final class Case004 {
    private static final String TAG = "004";
    private Case004() {
    }
    private static Solution s = new Solution();

    public static void test() {
        LeetUtil.assertTrue(abs(s.findMedianSortedArrays(new int[]{1,3}, new int[]{2})-2.000) < 0.00001, "1");
        LeetUtil.assertTrue(abs(s.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})-2.500) < 0.00001, "2");
    }

    static class Solution {
        private static final int SMALL_ENOUGH = 16;

//        private int getKthFromArrays(
//                int[] nums1, int left1, int right1,
//                int[] nums2, int left2, int right2,
//                int k) {
//            int r = 0;
//            if (SMALL_ENOUGH >= k) {
//                return getKthFromArraysSlow(nums1, left1, right1, nums2, left2, right2, k);
//            }
//
//            int middle1 = (left1 + right1)/2;
//            int middle2 = (left2 + right2)/2;
//            int size1 = right1 - left1;
//            int size2 = right2 - left2;
//            if (size1 >= size2) {
//                if (nums1[middle1] > nums2[middle2]) {
//                    if (k > )
//                    return getKthFromArrays(nums1, left1, middle1, nums2, left2, right2, k);
//                } else {
//
//                }
//            }
//
//            return r;
//        }

        private int getKthFromArraysSlow(
                int[] nums1, int left1, int right1,
                int[] nums2, int left2, int right2,
                int k) {
            int i1 = left1;
            int i2 = left2;
            for(;;k--) {
                if (k == 0) {
                    if (i1 >= right1) {
                        return nums2[i2];
                    } else if (i2 >= right2) {
                        return nums1[i1];
                    } else if (nums1[i1] < nums2[i2]) {
                        return nums1[i1];
                    } else {
                        return nums2[i2];
                    }
                }

                if (i1 >= right1) {
                    i2++;
                } else if (i2 >= right2) {
                    i1++;
                } else if (nums1[i1] < nums2[i2]) {
                    i1++;
                } else {
                    i2++;
                }
            }
        }

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int size1 = nums1.length;
            int size2 = nums2.length;
            if ((size1 + size2) % 2 == 1) {
                return getKthFromArraysSlow(nums1, 0, nums1.length, nums2, 0, nums2.length, (size1 + size2) / 2);
            } else {
                int m1 = (size1 + size2) / 2;
                int m2 = m1 - 1;
                return (((double) getKthFromArraysSlow(nums1, 0, nums1.length, nums2, 0, nums2.length, m1))
                        + ((double)getKthFromArraysSlow(nums1, 0, nums1.length, nums2, 0, nums2.length, m2))) / 2;
            }
        }
    }
}
