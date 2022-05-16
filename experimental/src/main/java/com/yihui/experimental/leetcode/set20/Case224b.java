package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

import java.util.Stack;

public class Case224b {

    public static void test(String[] args) {
        int i = 0;
        testOne(++i, "1 + 2 * 3 - (12 - (3 - 4) * 2)");
    }

    private static Solution s = new Solution();

    private static void testOne(int n, String str) {
        int r = s.calculate(str);
        Log.e("case" + n, str + " = [" + r + "]");
    }

    private static class Solution {

        private boolean isDigital(char ch) {
            return ch <= '9' && ch >= '0';
        }

        private void re(String s, int[] buffer) {
            int start = buffer[0];
            Stack<Integer> st = new Stack<>();
            char signal = '+';

            while (start < s.length()) {
                int num = 0;
                while (start < s.length() && isDigital(s.charAt(start))) {
                    num = 10 * num + s.charAt(start) - '0';
                    start++;
                }

                if (start < s.length() && s.charAt(start) == '(') {
                    int[] b = new int[2];
                    b[0] = start + 1;
                    re(s, b);
                    start = b[0];
                    num = b[1];
                }

                int pre;
                switch (signal) {
                    case '+':
                        st.push(num);
                        break;
                    case '-':
                        st.push(-1 * num);
                        break;
                    case '*':
                        pre = st.peek();
                        st.pop();
                        st.push(pre * num);
                        break;
                    case '/':
                        pre = st.peek();
                        st.pop();
                        st.push(pre / num);
                        break;
                    default:
                        break;
                }

                if (start < s.length() && s.charAt(start) == ')') {
                    buffer[0] = start + 1;
                    break;
                } else if (start < s.length()) {
                    signal = s.charAt(start);
                    start++;
                }
            }

            int i = 0;
            while (!st.isEmpty()) {
                i += st.peek();
                st.pop();
            }
            buffer[1] = i;
        }

        public int calculate(String s) {
            String str = s.replaceAll(" +", "");

            int[] buffer = new int[2];
            re(str, buffer);
            return buffer[1];
        }
    }
}

