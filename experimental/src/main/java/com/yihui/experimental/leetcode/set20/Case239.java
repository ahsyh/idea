package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

import java.util.Arrays;
import java.util.LinkedList;

public class Case239 {
    public static void test(String[] args) {
        testOne(new int[]{1,3,-1,-3,5,3,6,7}, 3);
    }

    private static Solution s = new Solution();

    private static void testOne(int[] nums, int k) {
        int[] i = s.maxSlidingWindow(nums, k);
        Log.e("", "result is: " + Arrays.toString(i));
    }

    private static class Solution {

        private static class MonoQueue {
            private LinkedList<Integer> list = new LinkedList<>();
            private int max = 0;

            public void add(int i) {
                while (!list.isEmpty() && list.getLast() < i) {
                    list.removeLast();
                }
                list.addLast(i);
            }

            public int max() {
                return list.getFirst();
            }

            public void pop(int i) {
                if (list.getFirst() == i) {
                    list.removeFirst();
                }
            }

        }
        int[] maxSlidingWindow(int[] nums, int k) {
            int[] res = new int[nums.length - k + 1];
            MonoQueue q = new MonoQueue();

            for (int i = 0; i < nums.length; i++) {
                if (i < k) {
                    q.add(nums[i]);
                } else {
                    q.add(nums[i]);
                    res[i - k] = q.max();
                    q.pop(nums[i - k]);
                }
            }

            res[nums.length - k] = q.max();

            return res;
        }

    }
}

