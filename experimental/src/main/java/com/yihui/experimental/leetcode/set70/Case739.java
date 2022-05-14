package com.yihui.experimental.leetcode.set70;

import java.util.Stack;

public class Case739 {
    public static void test(String[] args) {
        testOne();
    }

    private static Solution s = new Solution();

    private static void testOne() {

    }

    private static class Solution {
        int[] dailyTemperatures(int[] T) {
            int[] res = new int[T.length];
            Stack<Integer> st = new Stack<>();

            for (int i = T.length - 1; i >= 0; i--) {
                while (!st.isEmpty() && T[st.peek()] <= T[i]) {
                    st.pop();
                }

                res[i] = st.isEmpty() ? 0 : st.peek() - i;

                st.push(i);
            }

            return res;
        }
    }
}

