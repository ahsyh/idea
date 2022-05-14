package com.yihui.f.questions;

import com.yihui.f.util.Log;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QSort {
    private static QSort s = new QSort();

    public static void test() {
        testOne(new int[]{3,2,5,1,4,9,6,7,8});
    }

    private static void testOne(int[] nums) {
        s.qsort(nums);
        Log.e("result is: " + Arrays.stream(nums).boxed().collect(Collectors.toList()));

    }

    public void qsort(int[] nums) {
        q(nums, 0, nums.length);
    }

    private void q(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pos = partition(nums, left, right);
        q(nums, left, pos);
        q(nums, pos + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        if (right - left <= 1) {
            return left;
        }

        int mid = nums[left];

        int pos = left + 1;
        for (int i = left + 1; i < right; i++) {
            if (nums[i] < mid) {
                swap(nums, pos, i);
                pos++;
            }
        }
        swap(nums, pos - 1, left);
        return pos - 1;
    }

    private void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
