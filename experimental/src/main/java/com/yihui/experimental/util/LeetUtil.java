package com.yihui.experimental.util;

public final class LeetUtil {
    public static void assertTrue(boolean b, String label) {
        if (!b) {
            Log.e("assert", "assertTrue failed! label:" + label);
        }
    }
}
