package com.yihui.f.questions;

import com.yihui.f.util.Log;

public class ShortestSuperstring {

    public String shortestSuperstring(String[] A) {
        final int N = A.length;

        int[][] overlap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int k = Math.min(A[i].length(), A[j].length());

                for (;k > 0; k--) {
                    if (A[i].endsWith(A[j].substring(0,k))) {
                        break;
                    }
                }
                overlap[i][j] = k;
            }
        }

        int[][] dp = new int[1<<N][N];
        int[][] prev = new int[1<<N][N];

        for (int mask = 1; mask < 1 << N; mask++) {
            for (int pos = 0; pos < N; pos++) {

                if (!isBitSet(mask, pos)) {
                    continue;
                }

                int pmask = removeBit(mask, pos);
                if (pmask == 0 && isBitSet(mask, pos)) {
                    dp[mask][pos] = A[pos].length();
                    prev[mask][pos] = pos;
                } else if (pmask > 0) {
                    int shortest = Integer.MAX_VALUE;

                    for (int pPos = 0; pPos < N; pPos++) {
                        if (isBitSet(pmask, pPos) && pPos != pos) {
                            int val = dp[pmask][pPos] + A[pos].length() - overlap[pPos][pos];
                            if (val < shortest) {
                                shortest = val;
                                dp[mask][pos] = val;
                                prev[mask][pos] = pPos;
                            }
                        }
                    }
                }
            }
        }

        int minLength = Integer.MAX_VALUE;
        int pos = 0;
        for (int i = 0; i < N; i++) {
            if (dp[(1 << N) - 1][i] < minLength) {
                minLength = dp[(1 << N) - 1][i];
                pos = i;
            }
        }

        int[] seq = new int[N];
        int mask = (1<<N) - 1;
        seq[N - 1] = pos;
        for (int i = N - 2; i >= 0; i--) {
            seq[i] = prev[mask][seq[i+1]];
            mask = removeBit(mask, seq[i+1]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(A[seq[0]]);
        for (int k = 1; k < N; k++) {
            int i = seq[k - 1];
            int j = seq[k];
            sb.append(A[j].substring(overlap[i][j]));
        }

        String res = sb.toString();
        if (res.length() != minLength) {
            Log.e("The program execution met with problem!");
        } else {
            Log.e("The program execution seems to be correct!");
        }

        return res;
    }

    private int removeBit(int mask, int position) {
        return mask & (~(1 << position));
    }

    private boolean isBitSet(int mask, int position) {
        return (mask & (1 << position)) > 0;
    }

    public static void test() {
        ShortestSuperstring s = new ShortestSuperstring();
        String str1 = s.shortestSuperstring(new String[]{"catg","ctaagt","gcta","ttca","atgcatc"});
        Log.e("Output: " + str1);
        String str2 = s.shortestSuperstring(new String[]{"alex","loves","lintcode"});
        Log.e("Output: " + str2);
    }
}
