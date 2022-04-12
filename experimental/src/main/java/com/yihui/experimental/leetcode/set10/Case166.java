package com.yihui.experimental.leetcode.set10;

import com.yihui.experimental.util.LeetUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Case166 {
    private static final String TAG = "166";
    private Case166() {
    }
    private static Solution s = new Solution();

    public static void test() {
        LeetUtil.assertTrue("0.0000000004656612873077392578125".equals(s.fractionToDecimal(1, 2147483648L)), "0");
        LeetUtil.assertTrue("2".equals(s.fractionToDecimal(2, 1)), "1");
        LeetUtil.assertTrue("0.5".equals(s.fractionToDecimal(1, 2)), "2");
        LeetUtil.assertTrue("0.(2)".equals(s.fractionToDecimal(2, 9)), "3");
        LeetUtil.assertTrue("0.(0024)".equals(s.fractionToDecimal(24, 9999)), "4");
        LeetUtil.assertTrue("0.1(6)".equals(s.fractionToDecimal(1, 6)), "5");
        LeetUtil.assertTrue("-6.25".equals(s.fractionToDecimal(-50, 8)), "6");
    }

    static class Solution {
        private static class Data {
            public long remain;
            public String result;

            public Data(long remain, String result) {
                this.remain = remain;
                this.result = result;
            }
        }

        public String fractionToDecimal(int nInput, long dInput) {
            long numerator = nInput;
            long denominator = dInput;

            boolean negative = false;
            if (numerator < 0) {
                numerator *= -1;
                negative = true;
            }
            if (denominator < 0) {
                denominator *= -1;
                negative = !negative;
            }

            String inte = (numerator / denominator) + "";
            long remain = numerator % denominator;
            Set<Long> remainsSet = new HashSet<>();
            List<Data> remains = new ArrayList<>();

            StringBuilder sb = new StringBuilder();
            remainsSet.add(remain);

            boolean foundLoop = false;

            while (remain != 0) {
                Data data = new Data(remain, "");
                sb.setLength(0);
                remain *= 10;
                while (remain < denominator) {
                    remain *= 10;
                    sb.append("0");
                }
                sb.append((remain / denominator)+"");
                data.result = sb.toString();
                remains.add(data);
                remain %= denominator;

                if (remainsSet.contains(remain)) {
                    foundLoop = true;
                    break;
                }
                remainsSet.add(remain);
            }

            StringBuilder re = new StringBuilder();
            for (Data d : remains) {
                if (foundLoop && d.remain == remain) {
                    re.append("(" + d.result);
                } else {
                    re.append(d.result);
                }
            }
            if (foundLoop) {
                re.append(")");
            }

            String r = inte + (remains.isEmpty() ? "" : ("." + re));
            if ("0".equals(r)) {
                negative = false;
            }
            return (negative ? "-" : "") + r;
        }
    }
}
