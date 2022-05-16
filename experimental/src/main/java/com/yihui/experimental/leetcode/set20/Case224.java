package com.yihui.experimental.leetcode.set20;

import com.yihui.experimental.util.Log;

import java.util.Stack;

public class Case224 {
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

        private void re(String s, int[] buffer) {
            int start = buffer[0];
            Stack<Integer> st = new Stack<>();
            char calSign = '+';
            while (start < s.length()) {
                int par = 0;
                while (start < s.length() && s.charAt(start) >= '0' && s.charAt(start) <= '9') {
                    par = 10 * par + s.charAt(start) - '0';
                    start++;
                }
                if (start < s.length() && s.charAt(start) == '(') {
                    int[] b = new int[2];
                    b[0] = start + 1;
                    re(s, b);
                    par = b[1];
                    start = b[0];
                }

                int pre;
                int res;
                switch (calSign) {
                    case '-':
                        st.push(-1 * par);
                        break;
                    case '+':
                        st.push(par);
                        break;
                    case '*':
                        pre = st.peek();
                        res = pre * par;
                        st.pop();
                        st.push(res);
                        break;
                    case '/':
                        pre = st.peek();
                        res = pre / par;
                        st.pop();
                        st.push(res);
                        break;

                    default:
                        break;
                }

                if (start >= s.length() || s.charAt(start) == ')') {
                    buffer[0] = start + 1;
                    break;
                } else {
                    calSign = s.charAt(start);
                    start++;
                }
            }
            int res = 0;
            while(!st.isEmpty()) {
                res += st.pop();
            }
            buffer[1] = res;
        }

        public int calculate(String s) {
            String nStr = s.replaceAll(" +", "");
            int[] buffer = new int[2];
            re(nStr, buffer);
            return buffer[1];
        }
    }
}
