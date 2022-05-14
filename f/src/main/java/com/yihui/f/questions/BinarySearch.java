package com.yihui.f.questions;

import com.yihui.f.util.Log;

public class BinarySearch {
    private static BinarySearch s = new BinarySearch();

    public static void test() {
        testOne(new int[] {1,2,2,2,2,3,4,6}, 5);
    }

    private static void testOne(int[] nums, int target) {
        int r = s.search(nums, target);
        Log.e("search " + target + " in list: " + nums);
        Log.e("result is: " + r);
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;

//            if (nums[mid] < target) {
//                left = mid + 1;
//            } else if (nums[mid] == target) {
//                right = mid;
//            } else {
//                right = mid;
//            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left - 1;
    }

}
