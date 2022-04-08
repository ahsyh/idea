package com.yihui.experimental.threads;

import com.yihui.experimental.util.CommonUtil;
import com.yihui.experimental.util.Log;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Case001 {
    private Case001() {

    }

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 10,
            60, TimeUnit.SECONDS,
            new SynchronousQueue<>(true));

    public static ThreadLocal<String> str = new ThreadLocal<>();

    @RequiredArgsConstructor
    private static class R implements Runnable {
        final String name;

        @Override
        public void run() {
            Log.e(name, "thread started");
            str.set(name + "_str");
            CommonUtil.sleep(3000);
            Log.e(name, "thread var: " + str.get());
        }
    }

    public static void test() {
        for (int i = 0; i < 5; i++) {
            executor.submit(new R("T" + i));
        }
        executor.shutdown();
        while(!executor.isTerminated()) {
            CommonUtil.sleep(200);
        }

    }
}
