package com.yihui.experimental.leetcode;

import com.yihui.experimental.util.Log;

public class Case013 {

    private static class Solution {
        public int romanToInt(String s) {
            int result = 0;

            for (int i = 0; i < s.length(); i++) {
                if (i < s.length()-1) {
                    final char c1 = s.charAt(i);
                    final char c2 = s.charAt(i+1);
                    boolean succ = false;
                    {
                        if (c1 == 'I' && c2 == 'V') {
                            result += 4;
                            succ = true;
                        } else if (c1 == 'I' && c2 == 'X') {
                            result += 9;
                            succ = true;
                        } else if (c1 == 'X' && c2 == 'L') {
                            result += 40;
                            succ = true;
                        } else if (c1 == 'X' && c2 == 'C') {
                            result += 90;
                            succ = true;
                        } else if (c1 == 'C' && c2 == 'D') {
                            result += 400;
                            succ = true;
                        } else if (c1 == 'C' && c2 == 'M') {
                            result += 900;
                            succ = true;
                        }
                    }
                    if (succ) {
                        i++;
                        continue;
                    }
                }
                final char c1 = s.charAt(i);
                switch (c1) {
                    case 'I':
                        result += 1;
                        break;
                    case 'V':
                        result += 5;
                        break;
                    case 'X':
                        result += 10;
                        break;
                    case 'L':
                        result += 50;
                        break;
                    case 'C':
                        result += 100;
                        break;
                    case 'D':
                        result += 500;
                        break;
                    case 'M':
                        result += 1000;
                        break;
                }

            }

            return result;
        }
    }

    public static void test() {
        Solution s = new Solution();

        if(s.romanToInt("III")!=3) Log.e("T", "1 wrong");
        if(s.romanToInt("LVIII")!=58) Log.e("T", "2 wrong");
        if(s.romanToInt("MCMXCIV")!=1994) Log.e("T", "3 wrong");
    }
}
