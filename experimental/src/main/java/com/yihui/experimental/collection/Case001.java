package com.yihui.experimental.collection;

import com.yihui.experimental.util.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Case001 {
    private static final String TAG = "Collectioin";
    private Case001() {
    }

    private static Set<Integer> set = new HashSet<>();


    public static void test() {
        for ( int i = 0; i < 100; i++) {
            set.add(i);
        }
        final Set<Integer> result = set.stream().filter(id -> id > 90).collect(Collectors.toSet());
        Log.e(TAG, "result is: " +  result);
        Log.e(TAG, "result isEmpty: " +  result.isEmpty());
    }
}
