package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Case167 {
    private static final String TAG = "167";
    private Case167() {
    }
    private static Solution2 s = new Solution2();

    public static void test() {
        testOne(new int[] {2, 7, 11, 15}, 9);
        testOne(new int[] {2, 3, 4}, 6);
        testOne(new int[] {2, 3, 3, 5}, 6);
    }

    public static void testOne(int[] list, int target) {
        int[] r1 = s.twoSum(list, target);
        Log.e("", "result: " + Arrays.toString(r1));
    }

    private static class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int[] r = new int[2];
            Map<Integer, List<Integer>> map = new HashMap<>();

            for (int i = 0; i < numbers.length; i++) {
                int val = numbers[i];

                if (map.containsKey(val)) {
                    map.get(val).add(i + 1);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(i + 1);
                    map.put(val, list);
                }
            }

            for (int i = 0; i < numbers.length; i++) {
                int val = numbers[i];
                int left = target - val;

                if ((left != val) && map.containsKey(left)) {
                    int j = map.get(val).get(0);
                    int k = map.get(left).get(0);
                    r[0] = (j < k) ? j : k;
                    r[1] = (j < k) ? k : j;
                    return r;
                } else if ((left == val) && map.get(val).size() > 1) {
                    int j = map.get(val).get(0);
                    int k = map.get(val).get(1);
                    r[0] = (j < k) ? j : k;
                    r[1] = (j < k) ? k : j;
                    return r;
                }
            }

            return r;
        }
    }

    private static class Solution2 {

        public int findK(int[] numbers, int left, int right, int target) {
            if (left > right) {
                return -1;
            } else if ((left == right) || (left == (right - 1))) {
                if (numbers[left] == target) {
                    return left;
                } else if (numbers[right] == target) {
                    return right;
                } else {
                    return -1;
                }
            }

            int mid = (left + right) / 2;
            if (numbers[mid] == target) {
                return mid;
            } else if (numbers[mid] > target) {
                return findK(numbers, left, mid, target);
            } else {
                return findK(numbers, mid, right, target);
            }
        }

        public int[] twoSum(int[] numbers, int target) {
            int[] r = new int[2];

            for (int i = 0; i < numbers.length; i++) {
                int val = numbers[i];
                int remain = target - val;

                if (remain == val) {
                    if (numbers[i + 1] == val) {
                        r[0] = i + 1;
                        r[1] = i + 2;
                        return r;
                    }
                } else {
                    int j = findK(numbers, 0, numbers.length - 1, remain);
                    if (j > 0) {
                        r[0] = Math.min(i+1, j+1);
                        r[1] = Math.max(i+1, j+1);
                        return r;
                    }
                }

            }
            return r;
        }
    }

}
