package com.yihui.lintcode.util;

public class Utils {
    public static void assertTrue(boolean b, String label) {
        if (!b) {
            Log.e("assertTrue failed! label:" + label);
        }
    }
    public static void assertSameString(String a, String b, String label) {
        if (!a.equals(b)) {
            Log.e("assertTrue failed! label:" + label);
        }
    }
}
